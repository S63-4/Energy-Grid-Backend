import websockets
import asyncio
import pika

class MessageProducer:
    _host = None
    _port = None

    def __init__(self, host, port):
        self._host = host
        self._port = port

    async def send_to_server(self, message):
        uri = f"ws://{self._host}:{self._port}"
        async with websockets.connect(uri) as websocket:
            await websocket.send(message)
            return_message = await websocket.recv()
            print(return_message)

    def connect(self):
        connection = pika.BlockingConnection(pika.ConnectionParameters(host=self._host))
        channel = connection.channel()
        channel.queue_declare(queue="hello")
        channel.basic_publish(exchange="",
                              routing_key="hello",
                              body="Hello World!")
        print("Message sent!")
        connection.close()
