import pandas as pd
import datetime
from math import trunc
import time

class Simulator:
    mock_data = None
    formatted_data = None

    def format_time(self):
        """
        This method creates a new dataframe where the column with hour of the week
        is replaced by a day of the week column and a time column
        :return:
        """
        self.formatted_data = pd.DataFrame({"day": [], "time": [], "winter_value": []})
        if self.mock_data is None:
            return None
        for index, row in self.mock_data.iterrows():
            index = row[1]  # column 1 contains the hour value
            days = trunc(index / 24)
            hours_with_minutes = (index % 24)
            hours = trunc(hours_with_minutes)
            minutes = int((hours_with_minutes - hours) * 60)
            time = datetime.time(hour=hours, minute=minutes)
            self.formatted_data.loc[index] = [days] + [time] + [row[2]]  # column 2 contains the value

    def calculate_lookup_date(self):
        """
        This method calculates the day of the week and the current quarter hour for which the consumption should be
        looked up
        :return:
        the_day_of_week: current day of the week as integer
        the_time: current quarter hour of the day as datetime.time
        """
        the_day_of_week = datetime.datetime.now().weekday()
        hours = datetime.datetime.now().time().hour
        minutes = datetime.datetime.now().minute
        if 0 <= minutes <= 14:
            minutes = 0
        elif 15 <= minutes <= 29:
            minutes = 15
        elif 30 <= minutes <= 44:
            minutes = 30
        elif 45 <= minutes <= 59:
            minutes = 45
        the_time = datetime.time(hour=hours, minute=minutes)
        return the_day_of_week, the_time

    def calculate_lookup_hour(self):
        """
        This method calculates the hour of the week where the consumption data should be lookup up from
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

    def calculate_consumption(self):
        lookup_day, lookup_time = self.calculate_lookup_date()
        for index, row in self.formatted_data.iterrows():
            if int(row[0]) is lookup_day and row[1].hour is lookup_time.hour and row[1].minute is lookup_time.minute:
                return row[2]

    def calculate_consumption_alt(self):
        lookup_hour = self.calculate_lookup_hour()
        consumption = self.mock_data.loc[self.mock_data["hour"] == lookup_hour]
        return consumption["winter_value"].values[0]


    def main(self):
        self.mock_data = pd.read_excel("household_consumption_winter_mock_data.xlsx")
        begin = time.perf_counter()
        consumption_now = self.calculate_consumption_alt()
        print(consumption_now)
        end = time.perf_counter()
        print(f"Total time: {end-begin}")
