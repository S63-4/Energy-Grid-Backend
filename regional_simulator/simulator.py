import pandas as pd
import datetime
from math import trunc
import time

class Simulator:
    mock_data = None
    formatted_data = None

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
        lookup_hour = self.calculate_lookup_hour()
        consumption = self.mock_data.loc[self.mock_data["hour"] == lookup_hour]
        return consumption["winter_value"].values[0]

    def main(self):
        self.mock_data = pd.read_excel("household_consumption_winter_mock_data.xlsx")
        consumption_now = self.calculate_consumption()
        print(consumption_now)
