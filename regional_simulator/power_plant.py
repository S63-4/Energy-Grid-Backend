class PowerPlant:
    name = None
    fuel_type = None
    nominal_power_generation = 0
    current_power_generation_percentage = 0
    total_production = 0

    def __init__(self, name, fuel_type, nominal_power_generation):
        self.name = name
        self.fuel_type = fuel_type
        self.nominal_power_generation = nominal_power_generation

    def setCurrentCapacity(self, current_power_generation_percentage):
        self.current_power_generation_percentage = current_power_generation_percentage

    def addTotalProduction(self, production):
        self.total_production += production
