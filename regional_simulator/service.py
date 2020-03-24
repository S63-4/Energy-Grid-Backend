from websocket_producer import WebSocketProducer
from simulator import Simulator
from websocket_server import WebSocketServer

if __name__ == "__main__":
    host = "localhost"
    port = 8765
    websocket_server = WebSocketServer(host, port)
    websocket_producer = WebSocketProducer(host, port)
    sim = Simulator(websocket_producer)
    websocket_server.start()
    sim.main()
