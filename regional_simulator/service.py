from regional_simulator.event_producer import EventProducer
from regional_simulator.simulator import Simulator
from regional_simulator.broadcaster import Broadcaster

if __name__ == "__main__":
    host = "localhost"
    port = 8765
    broadcaster = Broadcaster(host, port)
    event_producer = EventProducer(host, port)
    sim = Simulator(event_producer)
    broadcaster.start()
    sim.main()
