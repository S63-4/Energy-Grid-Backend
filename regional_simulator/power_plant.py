import pandas as pd
from event import *


def calculate_current_powerplant_production():
    power_plants = pd.read_excel("power_plants_2019.xlsx")
    producers = []
    total_power_production = 0

    for index, row in power_plants.iterrows():
        nominal_power_power_generation = row["NOMINAL_POWER_GENERATION"]
        current_capacity = row["CURRENT_CAPACITY"]
        current_power_generation = nominal_power_power_generation / 100 * current_capacity
        total_power_production += current_power_generation
        producer = Producer()
        producer.name = row["NAME"]
        producer.production = current_power_generation
        producers.append(producer)

    producer_group = ProducerGroup()
    producer_group.num_producers = power_plants.index
    producer_group.total_production = total_power_production
    producer_group.producers = producers

    print(total_power_production)

    return producer_group
