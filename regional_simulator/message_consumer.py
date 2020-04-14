import pika

class MessageConsumer:
    _host = None
    _port = None

    def __init__(self, host, port):
        super().__init__()
        self._host = host
        self._port = port

    def start(self):
        connection = pika.BlockingConnection(pika.ConnectionParameters(host=self._host))
        channel = connection.channel()
        channel.queue_declare("regional_simulator")
        channel.basic_consume(queue="regional_simulator",
                              auto_ack=True,
                              on_message_callback=self.message_handler)
        print("Waiting for messages...")
        channel.start_consuming()

    def message_handler(self, ch, method, properties, body):
        print(f"Message received: {body.decode()}")


if __name__ == "__main__":
    host = "localhost"
    port = 5672
    websocket_consumer = MessageConsumer(host, port)
    websocket_consumer.start()
