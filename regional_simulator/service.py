from websocket_producer import WebSocketProducer
from simulator import Simulator
from websocket_server import WebSocketServer
import py_eureka_client.eureka_client as eureka_client
import asyncio

if __name__ == "__main__":

    host = "localhost"
    port = 9200
    # The flowing code will register your server to eureka server and also start to send heartbeat every 30 seconds
    eureka_client.init(eureka_server="http://localhost:8761/eureka",
                       app_name="regional-simulator",
                       instance_port=port)

    # starting a websocket server on a different thread
    websocket_server = WebSocketServer(host, port)
    websocket_server.start()

    # create consumer class to pass to simulator
    websocket_producer = WebSocketProducer(host, port)
    simulator = Simulator(websocket_producer)

    # run the simulator
    simulator.main()
