import requests

token = open("apiToken.txt","r").read()

# City code for Middelburg.
City_code = 2750896


def get_weather():
    response = requests.get(f"http://api.openweathermap.org/data/2.5/weather?id={City_code}&appid={token}")

    if response.status_code == 200:

        data = response.json()
        return data

    else:
        raise ConnectionAbortedError('failed to retrieve weather data.')



