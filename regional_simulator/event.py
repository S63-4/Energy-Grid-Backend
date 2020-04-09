class Event:
    date = None
    consumption = None
    production = None

    def __init__(self, date):
        self.date = date
        self.consumption = Consumption()
        self.production = Production()

    def toJSON(self):
        json = "{"
        json += f"\"date\" : \"{self.date}\", " \
                f"\"consumption\" : {self.consumption.toJSON()}, " \
                f"\"production\" : {self.production.toJSON()}"
        json += "}"
        return json


class Consumption:
    households = None
    big_consumers = None
    industries = None

    def __init__(self):
        self.households = ConsumerGroup()
        self.big_consumers = ConsumerGroup()
        self.industries = ConsumerGroup()

    def toJSON(self):
        json = "{"
        json += f"\"households\" : {self.households.toJSON()}, " \
                f"\"big_consumers\" : {self.big_consumers.toJSON()}, " \
                f"\"industries\" : {self.industries.toJSON()}"
        json += "}"
        return json

class Production:
    wind_farms = None
    solar_farms = None
    power_plants = None

    def __init__(self):
        self.wind_farms = ProducerGroup()
        self.solar_farms = ProducerGroup()
        self.power_plants = ProducerGroup()

    def toJSON(self):
        json = "{"
        json += f"\"wind_farms\" : {self.wind_farms.toJSON()}, " \
                f"\"solar_farms\" : {self.solar_farms.toJSON()}, " \
                f"\"power_plants\" : {self.power_plants.toJSON()}"
        json += "}"
        return json

class ConsumerGroup:
    num_consumers = 0
    total_consumption = 0
    consumers = []

    def toJSON(self):
        json = "{"
        json += f" \"num_consumers\" : {self.num_consumers},"
        json += f" \"total_consumption\" : {self.total_consumption}, " \
                f"\"consumers\" : [ "
        for consumer in self.consumers:
            json += consumer.toJSON()
            json += ","
        # remove last comma because list has ended
        json = json[:-1]
        json += "]}"
        return json

class ProducerGroup:
    num_producers = 0
    total_production = 0
    producers = []

    def toJSON(self):
        json = "{"
        json += f" \"num_producers\" : {self.num_producers},"
        json += f" \"total_production\" : {self.total_production}, " \
                f"\"producers\" : [ "
        for producer in self.producers:
            json += producer.toJSON()
            json += ","
        # remove last comma because list has ended
        json = json[:-1]
        json += "]}"
        return json

class Consumer:
    name = ""
    consumption = 0

    def toJSON(self):
        json = "{"
        json += f"\"name\" : {self.name}, " \
                f"\"consumption\" : {self.consumption}"
        json += "}"
        return json

class Household(Consumer):
    num_connection = 0

    def toJSON(self):
        json = "{"
        json += f"\"name\" : {self.name}, " \
                f"\"num_connection\" : {self.num_connection}, " \
                f"\"consumption\" : {self.consumption}"
        json += "}"
        return json

class Producer:
    name = ""
    production = 0

    def toJSON(self):
        json = "{"
        json += f"\"name\" : {self.name}, " \
                f"\"production\" : {self.production}"
        json += "}"
        return json
