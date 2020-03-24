package com.energygrid.data_processor.service;


public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory) {
        super(messageHandlerFactory);
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.gtrrr")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getMain());
        handler.handleMessage(data, sessionId);
    }
}