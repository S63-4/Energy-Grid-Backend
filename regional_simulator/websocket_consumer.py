from threading import Thread
import websockets
from websockets import WebSocketClientProtocol
import asyncio
import datetime
import os
import pika

class WebSocketConsumer(Thread):
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
            date = datetime.datetime.now()
            new_file = open(os.path.join("json", f"{date.hour}-{date.minute}-00.json"), "x")
            new_file.write(message)
            new_file.close()


if __name__ == "__main__":
    host = "localhost"
    port = 9200
    websocket_consumer = WebSocketConsumer(host, port)
    websocket_consumer.start()
