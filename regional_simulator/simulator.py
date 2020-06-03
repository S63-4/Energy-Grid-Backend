import pandas as pd
import weatherService as weather
import datetime
import time
import asyncio
import schedule
from event import *

class Simulator:
    _mock_data = None
    _enduris_data = None
    _message_producer = None
    _mock_data_windturbines = None

    def __init__(self, event_producer):
        self._message_producer = event_producer

    try:
        data = weather.get_weather()
    except ConnectionAbortedError as error:
        print(error.args)

    def get_windspeed(self):
        return self.data["wind"]["speed"]

    def myround(self, windspeed, rounded_windspeed):
        base = .5
        myround = base * round(windspeed / base)

        print(windspeed, float(rounded_windspeed), myround)

        if myround == float(rounded_windspeed):
            if windspeed < rounded_windspeed:
                myround = myround - .5
            elif windspeed > rounded_windspeed:
                myround += .5
        print(windspeed, float(rounded_windspeed), myround)
        return myround

    def interpolate_production(self, between_value, smallest_number, biggest_number, smallest_production,
                               biggest_production):
        percentual_difference = round((between_value - smallest_number) / (biggest_number - smallest_number), 1)
        print(float(percentual_difference))
        print(smallest_production, biggest_production)
        production = smallest_production + percentual_difference * (
                biggest_production - smallest_production)
        return production

    def calculate_windturbines_production(self):
        # find the speed of the wind
        windspeed = self.get_windspeed()
        production = 0

        # find the production at that speed
        if 2.5 < windspeed < 26:
            production_row = self._mock_data.loc[self._mock_data["Windsnelheid"] == windspeed]
            if windspeed.is_integer() or production_row is float:
                production = production_row["kW/h"].array[0]
            elif production_row is not float:
                # get values of row above and row beneath
                nearest_whole = int(round(windspeed))
                nearest_half = self.myround(windspeed, nearest_whole)
                production_row_nearest_half = self._mock_data.loc[self._mock_data["Windsnelheid"] == nearest_half]
                production_row_nearest_whole = self._mock_data.loc[self._mock_data["Windsnelheid"] == nearest_whole]
                production_nearest_half = production_row_nearest_half["kW/h"].array[0]
                production_nearest_whole = production_row_nearest_whole["kW/h"].array[0]
                if nearest_half < nearest_whole:
                    production = self.interpolate_production(windspeed, nearest_half, nearest_whole,
                                                             production_nearest_half, production_nearest_whole)
                elif nearest_whole < nearest_half:
                    production = self.interpolate_production(windspeed, nearest_whole, nearest_half,
                                                             production_nearest_whole, production_nearest_half)
        production = production * self._amount_windturbines
        return production

    def calculate_lookup_hour(self, date):
        """
        This method calculates the hour of the week where the consumption data should be lookup up from.
        The minutes are added as a decimal value.
        :return:
        hour_of_week: the hour of the week
        """
        the_day_of_week = date.weekday()
        hours = date.time().hour
        minutes = date.minute
        if 0 <= minutes <= 14:
            minutes = 0
        elif 15 <= minutes <= 29:
            minutes = .25
        elif 30 <= minutes <= 44:
            minutes = .50
        elif 45 <= minutes <= 59:
            minutes = .75
        hour_of_week = (the_day_of_week*24) + hours + minutes
        return hour_of_week

    def calculate_lookup_month(self, date):
        return date.strftime("%B").lower()

    def calculate_current_minute_value(self, lookup_hour, lookup_month):
        row_current_quarter = self._mock_data.loc[self._mock_data["hour"] == lookup_hour]
        # divide current quarter value by 15 to mock a minute
        return row_current_quarter[lookup_month].values[0] / 15

    def calculate_current_minute_household_consumption(self, current_minute_value, lookup_month):
        """
        This method calculates the consumption for the current minute.
        step 1: look at the total of all the quarters of the month combined
        step 2: look at how much the current month takes up from the yearly total
        step 3: look at the yearly consumption data for current household from the util. comp. sheet
        step 4: calculate the amount of util. comp. data is used in current month
        step 5: calculate the ratio between total monthly value in mock data util. comp. data
        step 6: calculate current minute value multiplied by ratio to form actual data
        step 7: build ConsumerGroup object
        step 8: return ConsumerGroup object
        """
        # find the row with the sum of all quarter hour (step 1)
        row_totals = self._mock_data.loc[self._mock_data["hour"] == "total_month"]
        total_month_value = row_totals[lookup_month].array[0]

        # find month is percentage of year value (step 2)
        row_month_percentage_of_year = self._mock_data.loc[self._mock_data["hour"] == "percentage_of_year"]
        month_percentage_of_year = row_month_percentage_of_year[lookup_month].array[0]

        # steps 3, 4, 5 & 6 are taken for every row in the util company sheet
        consumer_group = ConsumerGroup()
        consumer_group.consumers.clear()
        total_consumption = 0
        total_consumers = 0
        list_consumers = []
        for index, row in self._enduris_data.iterrows():
            if row["PRODUCTSOORT"] == "ELK":
                # find yearly total from households in util company sheet (step 3)
                household_consumption_year_total = row["SJV_GEMIDDELD"]

                # calculate how much of yearly total is used in {lookup_month} (step 4)
                total_month_consumption = month_percentage_of_year * household_consumption_year_total

                # calculate ratio between mock data and real data (step 5)
                ratio = total_month_consumption / total_month_value

                # calculate consumption from ratio and current minute value
                consumption = ratio * current_minute_value

                # build consumer group object
                new_consumer = HouseholdConsumer()
                new_consumer.name = row["POSTCODE_VAN"]
                new_consumer.num_connections = row["AANSLUITINGEN_AANTAL"]
                new_consumer.consumption = consumption
                total_consumption += consumption
                total_consumers += 1
                list_consumers.append(new_consumer)

        consumer_group.total_consumption = total_consumption
        consumer_group.num_consumers = total_consumers
        consumer_group.consumers = list_consumers
        return consumer_group  # step 8

    async def calculate_household_consumption(self, lookup_hour, lookup_month):
        current_minute_value = self.calculate_current_minute_value(lookup_hour, lookup_month)
        return self.calculate_current_minute_household_consumption(current_minute_value, lookup_month)

    async def calculate_big_consumer_consumption(self):
        await asyncio.sleep(0)

    async def calculate_industry_consumption(self):
        await asyncio.sleep(0)

    async def run_simulator(self):
        date = datetime.datetime.now()
        # add 1 minute to simulation time to make sure simulation of next minute is done at the start of the minute
        date = date.replace(minute=(date.minute+1))
        date_iso = date.replace(microsecond=0).isoformat()
        print(f"Simulating for time in ISO 8601: {date_iso}")
        event = Event(date)
        # event.consumption.households.consumers.clear()
        # event.consumption.big_consumers.consumers.clear()
        # event.consumption.industries.consumers.clear()
        # event.production.wind_farms.producers.clear()
        # event.production.solar_farms.producers.clear()
        # event.production.power_plants.producers.clear()
        # event.production.households.producers.clear()

        start = time.perf_counter()
        lookup_hour = self.calculate_lookup_hour(date)
        lookup_month = self.calculate_lookup_month(date)

        # Send reference to corresponding ConsumerGroup as third parameter so method can fill it in.
        household_consumption_task = asyncio.create_task(
            self.calculate_household_consumption(lookup_hour, lookup_month))

        big_consumer_consumption_task = asyncio.create_task(
            self.calculate_big_consumer_consumption())

        industry_consumption_task = asyncio.create_task(
            self.calculate_industry_consumption())

        windfarms_production_task = asyncio.create_task(
            self.calculate_windturbines_production())

        event.consumption.households = await household_consumption_task
        # event.consumption.big_consumers = await big_consumer_consumption_task
        # event.consumption.industries = await industry_consumption_task
        event.production.wind_farms = await windfarms_production_task

        json_string = event.toJSON()
        end = time.perf_counter()
        print(f"Calculations done in: {end-start}")
        self._message_producer.send(json_string)

    def create_event_loop(self):
        loop = asyncio.get_event_loop()
        loop.run_until_complete(self.run_simulator())

    def writeToFile(self, message):
        """
        mainly used for DEBUG purposes
        """
        f = open("event.json", "x")
        f.write(message)
        f.close()

    def main(self):
        self._mock_data = pd.read_excel("household_consumption_mock_data.xlsx")
        self._enduris_data = pd.read_excel("enduris_2019.xlsx")
        _mock_data_windturbines = pd.read_excel("windturbines_mock_data.xlsx")
        schedule.every().minute.at(":00").do(self.create_event_loop)
        while True:
            schedule.run_pending()
            time.sleep(1)

        # DEBUG CODE, prints faster than production code above
        # while True:
        #     self.create_event_loop()
        #     time.sleep(60)
