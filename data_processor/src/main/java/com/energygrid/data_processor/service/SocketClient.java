package com.energygrid.data_processor.service;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.util.logging.Logger;

import com.energygrid.data_processor.service.domain.IMain;
import com.energygrid.data_processor.service.domain.Main;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class SocketClient {

    private static final int PORT = 8085;

    public static void main(String[] args) {

        IMessageHandlerFactory factory = new ServerMessageHandlerFactory();
        IServerMessageProcessor messageProcessor = new ServerMessageProcessor(factory);
        final SocketService socket = new SocketService();
        socket.setMessageProcessor(messageProcessor);

        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(socket);

        IMain main = new Main(messageGenerator);
        //messageProcessor.processMessage(main);

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add WebSocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();
            wscontainer.addEndpoint(config);
            webSocketServer.start();
            webSocketServer.join();

        } catch (Exception ex) {
            Logger.getLogger(ex.toString());
        }
    }
}