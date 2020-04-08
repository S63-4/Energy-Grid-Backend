class Event:
    date = None
    consumption = None
    production = None

    def __init__(self, date):
        self.date = date
        self.consumption = Consumption()
        self.production = Production()


class Consumption:
    households = None
    big_consumers = None
    industries = None

    def __init__(self):
        self.households = Consumer()
        self.big_consumers = Consumer()
        self.industries = Consumer()

class Production:
    wind_farms = None
    solar_farms = None
    power_plants = None

    def __init__(self):
        self.wind_farms = Producer()
        self.solar_farms = Producer()
        self.power_plants = Producer()

class Consumer:
    num_consumers = 0
    total_consumption = 0
    consumers = []


class Producer:
    num_producers = 0
    total_production = 0
    producers = []
