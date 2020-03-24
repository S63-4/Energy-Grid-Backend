from threading import Thread
import websockets
from websockets import WebSocketClientProtocol
import asyncio

class EventHandler(Thread):

    _host = None
    _port = None

    def __init__(self, host, port):
        super().__init__()
        self._host = host
        self._port = port

    def run(self):
        loop = asyncio.new_event_loop()
        asyncio.set_event_loop(loop)
        loop.run_until_complete(self.handler())
        loop.run_forever()

    async def handler(self):
        uri = f"ws://{self._host}:{self._port}"
        async with websockets.connect(uri) as websocket:
            await self.message_handler(websocket)

    async def message_handler(self, websocket: WebSocketClientProtocol):
        async for message in websocket:
            print(message)

if __name__ == "__main__":
    host = "localhost"
    port = 8765
    eh = EventHandler(host, port)
    eh.start()
