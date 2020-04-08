import asyncio
import websockets
from websockets import WebSocketServerProtocol
from threading import Thread

class WebSocketServer(Thread):

    host = None
    port = None
    clients = set()

    def __init__(self, host, port):
        super().__init__()
        self.host = host
        self.port = port

    def run(self):
        loop = asyncio.new_event_loop()
        asyncio.set_event_loop(loop)
        start_server = websockets.serve(self.websocket_handler, host=self.host, port=self.port)
        loop.run_until_complete(start_server)
        loop.run_forever()

    async def register(self, websocket: WebSocketServerProtocol):
        self.clients.add(websocket)
        await websocket.send(f"Connection established with {websocket.host}\n")
        print(f"Total connections: {len(self.clients)}")

    async def unregister(self, websocket: WebSocketServerProtocol):
        self.clients.remove(websocket)

    async def distribute(self, websocket: WebSocketServerProtocol):
        async for message in websocket:
            if message.startswith("SIM"):
                await self.broadcast(message)

    async def broadcast(self, message):
        if self.clients:
            await asyncio.wait([websocket.send(message) for websocket in self.clients])

    async def websocket_handler(self, websocket: WebSocketServerProtocol, message):
        await self.register(websocket)
        try:
            await self.distribute(websocket)
        finally:
            await self.unregister(websocket)
