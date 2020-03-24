package com.energygrid.data_processor.service;

import java.util.List;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private SocketService serverSocket;
    private static ServerMessageGenerator serverMessageGenerator;

    public ServerMessageGenerator(SocketService serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static ServerMessageGenerator getInstance() {
        if (serverMessageGenerator == null){
            serverMessageGenerator = new ServerMessageGenerator(SocketService.getInstance());
        }
        return serverMessageGenerator;
    }

    @Override
    public void notifyConnectionMade(String sessionId, String text) {
        String msg = text;
        serverSocket.sendTo(sessionId, msg);
    }
}