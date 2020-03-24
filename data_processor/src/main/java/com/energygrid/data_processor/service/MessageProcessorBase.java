package com.energygrid.data_processor.service;

import com.energygrid.data_processor.service.domain.IMain;
import com.google.gson.Gson;

public abstract class MessageProcessorBase implements IServerMessageProcessor {

    private IMain main;
    private IMessageHandlerFactory messageHandlerFactory;

    public IMessageHandlerFactory getMessageHandlerFactory() {
        return messageHandlerFactory;
    }

    public abstract void processMessage(String sessionId, String type, String data);

    private Gson gson;

    public MessageProcessorBase(IMessageHandlerFactory messageHandlerFactory)
    {
        this.messageHandlerFactory = messageHandlerFactory;
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }

    public IMain getMain()
    {
        return main;
    }
}
