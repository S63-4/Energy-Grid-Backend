import websockets
import asyncio
import pika

class MessageProducer:
    _host = None
    _port = None

    def __init__(self, host, port):
        self._host = host
        self._port = port

    def send(self, message):
        connection = pika.BlockingConnection(pika.ConnectionParameters(host=self._host))
        channel = connection.channel()
        channel.queue_declare(queue="regional_simulator")
        channel.basic_publish(exchange="",
                              routing_key="regional_simulator",
                              body=message)
        print("Message sent!")
        connection.close()
