from websocket_producer import WebSocketProducer
from simulator import Simulator
from websocket_server import WebSocketServer
import py_eureka_client.eureka_client as eureka_client

if __name__ == "__main__":

    host = "localhost"
    port = 9200
    # The flowing code will register your server to eureka server and also start to send heartbeat every 30 seconds
    eureka_client.init(eureka_server="http://localhost:8761/eureka",
                       app_name="regional-simulator",
                       instance_port=port)
    websocket_server = WebSocketServer(host, port)
    websocket_producer = WebSocketProducer(host, port)
    sim = Simulator(websocket_producer)
    websocket_server.start()
    sim.main()
