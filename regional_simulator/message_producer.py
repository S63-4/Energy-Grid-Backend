import pika

class MessageProducer:
    _host = None
    _port = None

    def __init__(self, host, port):
        self._host=host
        self._port=port

    def send(self, message):
        print("host:" + self._host)
        connection = pika.BlockingConnection(pika.ConnectionParameters(host="10.93.15.85"))
        # DEBUG CODE
        # connection = pika.BlockingConnection(pika.ConnectionParameters(host="localhost"))

        channel = connection.channel()
        channel.exchange_declare(exchange="simulator-exchange", exchange_type="direct", durable=True)
        channel.basic_publish(exchange="simulator-exchange",
                              routing_key="regional",
                              body=message)
        print("Message sent!")
        connection.close()

