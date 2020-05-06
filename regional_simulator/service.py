from message_producer import MessageProducer
from simulator import Simulator
import py_eureka_client.eureka_client as eureka_client

if __name__ == "__main__":

    host = "localhost"
    port = 5672
    # The flowing code will register your server to eureka server and also start to send heartbeat every 30 seconds
    eureka_client.init(eureka_server="http://localhost:8761/eureka",
                       app_name="regional-simulator",
                       instance_port=port)

    # create consumer class to pass to simulator
    message_producer = MessageProducer(host, port)
    simulator = Simulator(message_producer)

    # run the simulator
    simulator.main()
