import weatherService as weather
import pandas as pd
import time
import schedule


class Windturbines:
    _mock_data = None
    _amount_windturbines = 325

    try:
        data = weather.get_weather()
    except ConnectionAbortedError as error:
        print(error.args)

    def __init__(self):
        pass

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

    def get_production(self):
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

    def main(self):
        self._mock_data = pd.read_excel("windturbines_mock_data.xlsx")
        print(self._mock_data.head(42))
        self.get_production()
        schedule.every().minute.at(":00").do(self.get_production())
        while True:
            schedule.run_pending()
            time.sleep(1)



if __name__ == "__main__":
    sim = Windturbines()
    sim.main()
