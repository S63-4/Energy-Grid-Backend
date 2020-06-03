import weatherService as weather
from event import *

try:
    data = weather.get_weather()

except ConnectionAbortedError as error:
    print(error.args)


def get_clouds():
    return data["clouds"]["all"]


def get_sunrise():
    return data["sys"]["sunrise"]


def get_sunset():
    return data["sys"]["sunset"]


def get_current_time():
    return data["dt"]


def calculate_solar_production(panels_amount):
    # predicting output:
    # The maximum performance of a solar panel is called Wattpiek(Wp).
    # Because of the dutch weather we use a factor of 0,85.
    # For example a single panel of 300 Wp results in 255 kWh per year (300 X 0,85 = 255).

    # Affect of clouds on solar panels.
    # The impacts of clouds on a solar panel may make it far less efficient in certain parts of the world.
    # And at certain seasons.
    #
    # But the effects of clouds on a solar panel can also be unexpectedly great.
    # Incredibly, your solar panels will put out their supreme amount of peak power during cloudy weather.

    # As the sun moves into a hole between the clouds, your panels will see something wonderful.
    # They will see complete direct sunlight “plus” reflected light from the clouds!
    # They will drink in more energy than they could on a cloudless day!
    # A solar panel might then produce peaks at or above 50 percent more than its direct-sun output!

    # In this simulation we will assume all the solar panels are 300 Wp.
    # There are +- 525950 minutes in a year

    # the amount of solar panels in Zeeland is unknown but estimated to have passed the 1 million.

    time_factor = 0
    cloud_factor = 0

    clouds = get_clouds()

    # Check if the sun is currently up or not.

    if get_current_time() < get_sunrise() or get_current_time() > get_sunset():

        time_factor = 0
    else:
        time_factor = 1

    # Check the cloud density. Foe the reasons stated above clouds can be beneficial, for the solar panel.
    # Because there is no known exact formula . We will not simulate it exactly but we will compensate for it.
    # By reducing the impact of clouds in general by dividing it by 1.5

    cloud_factor = (100 - (clouds / 1.5)) / 100



    #Calculate
    # Amount of solar panels * average production * cloud factor * time factor / minutes in a year

    production_per_minute = panels_amount * 300 * cloud_factor * time_factor / 525950

    producer = Producer()
    producer.name = "solar panels"
    producer.production = production_per_minute

    producers = []
    producers.append(producer)

    result = ProducerGroup()
    result.num_producers = panels_amount
    result.total_production = production_per_minute
    result.producers = producers

    return result







