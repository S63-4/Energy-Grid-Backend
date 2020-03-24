import websockets
from websockets import WebSocketClientProtocol
import asyncio

class EventProducer:
    _host = None
    _port = None

    def __init__(self, host, port):
        super().__init__()
        self._host = host
        self._port = port

    async def send_to_server(self, message):
        uri = f"ws://{self._host}:{self._port}"
        async with websockets.connect(uri) as websocket:
            await websocket.send(message)
            return_message = await websocket.recv()
            print(return_message)

    def send(self, message):
        asyncio.run(self.send_to_server(message))
