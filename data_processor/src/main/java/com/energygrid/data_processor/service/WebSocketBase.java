package com.energygrid.data_processor.service;

import com.google.gson.Gson;

public abstract class WebSocketBase implements IWebSocket {

    private IServerMessageProcessor processor;

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();

    private Gson gson;

    public WebSocketBase()
    {
        gson = new Gson();
    }

    public void setMessageProcessor(IServerMessageProcessor processor)
    {
        this.processor = processor;
    }

    public abstract void start();

    public abstract void stop();

    public IServerMessageProcessor getProcessor() {
        return processor;
    }

    public Gson getGson() {
        return gson;
    }
}