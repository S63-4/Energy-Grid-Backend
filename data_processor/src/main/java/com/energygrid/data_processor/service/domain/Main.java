package com.energygrid.data_processor.service.domain;

import com.energygrid.data_processor.service.IServerMessageGenerator;
import com.energygrid.data_processor.service.ServerMessageGenerator;

public class Main implements IMain {
    private static Main main;
    private IServerMessageGenerator messageGenerator;

    public Main(IServerMessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public static Main getInstance() {
        if (main == null) {
            main = new Main(ServerMessageGenerator.getInstance());
        }
        return main;
    }

    @Override
    public void connectionMade(String sessionId) {
        messageGenerator.notifyConnectionMade(sessionId, "A connection was established.");
    }
}