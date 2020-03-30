import pandas as pd
import datetime
from websocket_server import WebSocketServer
import time
from pydispatch import dispatcher
import asyncio
import websockets

class Simulator:
    _mock_data = None
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

    def calculate_consumption(self, lookup_hour):
        consumption = self._mock_data.loc[self._mock_data["hour"] == lookup_hour]
        return consumption["january"].values[0]

    def main(self):
        self._mock_data = pd.read_excel("household_consumption_mock_data.xlsx")
        lookup_hour = self.calculate_lookup_hour()
        consumption_now = self.calculate_consumption(lookup_hour)

        while 1:
            self._event_producer.send(f"SIM: {consumption_now}")
            time.sleep(2)
