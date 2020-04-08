import pandas as pd
import datetime
import time
import asyncio
import schedule
from event import Event
import json

class Simulator:
    _mock_data = None
    _enduris_data = None
    _event_producer = None

    def __init__(self, event_producer):
        self._event_producer = event_producer

    def calculate_lookup_hour(self):
        """
        This method calculates the hour of the week where the consumption data should be lookup up from.
        The minutes are added as a decimal value.
        :return:
        hour_of_week: the hour of the week
        """
        the_day_of_week = datetime.datetime.now().weekday()
        hours = datetime.datetime.now().time().hour
        minutes = datetime.datetime.now().minute
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

    def calculate_lookup_month(self):
        return datetime.datetime.now().strftime("%B").lower()

    def calculate_current_minute_value(self, lookup_hour, lookup_month):
        row_current_quarter = self._mock_data.loc[self._mock_data["hour"] == lookup_hour]
        # divide current quarter value by 15 to mock a minute
        return row_current_quarter[lookup_month].values[0] / 15

    def calculate_current_minute_consumption(self, current_minute_value, lookup_month):
        """
        This method calculates the consumption for the current minute.
        step 1: look at the total of all the quarters of the month combined
        step 2: look at how much the current month takes up from the yearly total
        step 3: look at the yearly consumption data from the util. comp. sheet
        step 4: calculate the amount of util. comp. data is used in current month
        step 5: calculate the ratio between total monthly value in mock data util. comp. data
        step 6: return current minute value multiplied by ratio to form actual data
        """
        # find the row with the sum of all quarter hour (step 1)
        row_totals = self._mock_data.loc[self._mock_data["hour"] == "total_month"]
        total_month_value = row_totals[lookup_month].array[0]

        # find month is percentage of year value (step 2)
        row_month_percentage_of_year = self._mock_data.loc[self._mock_data["hour"] == "percentage_of_year"]
        month_percentage_of_year = row_month_percentage_of_year[lookup_month].array[0]

        # find yearly total from util company (step 3)
        household_consumption_year_total = self._enduris_data["SJV_GEMIDDELD"][0]

        # calculate how much of yearly total is used in {lookup_month} (step 4)
        total_month_consumption = month_percentage_of_year * household_consumption_year_total

        # calculate ratio between mock data and real data (step 5)
        ratio = total_month_consumption / total_month_value
        return current_minute_value * ratio  # (step 6)

    async def calculate_household_consumption(self, lookup_hour, lookup_month):
        current_minute_value = self.calculate_current_minute_value(lookup_hour, lookup_month)
        consumption_now = self.calculate_current_minute_consumption(current_minute_value, lookup_month)
        return consumption_now

    async def calculate_big_consumer_consumption(self):
        await asyncio.sleep(0)

    async def calculate_industry_consumption(self):
        await asyncio.sleep(0)

    def create_json(self, event):
        event.consumption.households = json.dumps(event.consumption.households.__dict__)
        event.consumption.big_consumers = json.dumps(event.consumption.big_consumers.__dict__)
        event.consumption.industries = json.dumps(event.consumption.industries.__dict__)
        event.consumption = json.dumps(event.consumption.__dict__)
        event.production.wind_farms = json.dumps(event.production.wind_farms.__dict__)
        event.production.solar_farms = json.dumps(event.production.solar_farms.__dict__)
        event.production.power_plants = json.dumps(event.production.power_plants.__dict__)
        event.production = json.dumps(event.production.__dict__)
        json_string = json.dumps(event.__dict__)
        return json_string

    async def run_simulator(self):
        date = datetime.datetime.now()
        date = date.replace(microsecond=0).isoformat()
        print(f"Start time in ISO 8601: {date}")
        event = Event(date)

        start = time.perf_counter()
        lookup_hour = self.calculate_lookup_hour()
        lookup_month = self.calculate_lookup_month()

        household_consumption_task = asyncio.create_task(
            self.calculate_household_consumption(lookup_hour, lookup_month))
        big_consumer_consumption_task = asyncio.create_task(self.calculate_big_consumer_consumption())
        industry_consumption_task = asyncio.create_task(self.calculate_industry_consumption())

        household_consumption = await household_consumption_task
        big_consumer_consumption = await big_consumer_consumption_task
        industry_consumption = await industry_consumption_task

        event.consumption.households.total_consumption = household_consumption
        json_string = self.create_json(event)
        print(json_string)
        end = time.perf_counter()
        print(f"Calculations done in: {end-start}")
        await self._event_producer.send_to_server(f"{household_consumption}")
        await asyncio.sleep(2)

    def create_event_loop(self):
        loop = asyncio.get_event_loop()
        loop.run_until_complete(self.run_simulator())

    async def main(self):
        self._mock_data = pd.read_excel("household_consumption_mock_data.xlsx")
        self._enduris_data = pd.read_excel("enduris_2019.xlsx")
        # schedule.every().minute.at(":00").do(self.create_event_loop)
        # while True:
        #     schedule.run_pending()
        #     time.sleep(1)

        # DEBUG CODE, prints faster than production code above
        while True:
            await self._event_producer.send_to_server(f"VALUE")
            time.sleep(10)
