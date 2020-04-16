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
        channel.exchange_declare(exchange="simulator", exchange_type="direct")
        channel.basic_publish(exchange="simulator",
                              routing_key="regional",
                              body=message)
        print("Message sent!")
        connection.close()
