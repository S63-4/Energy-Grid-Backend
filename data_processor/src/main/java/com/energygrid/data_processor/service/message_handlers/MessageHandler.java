package com.energygrid.data_processor.service.message_handlers;

import com.energygrid.data_processor.service.IMessageHandler;
import com.energygrid.data_processor.service.domain.IMain;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandler<T> implements IMessageHandler {

    private IMain main;
    private Gson gson;

    public MessageHandler(IMain main)
    {
        this.main = main;
        gson = new Gson();
    }

    public void handleMessage(String data, String sessionId)
    {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T msg = gson.fromJson(data, type);
        handleMessageInternal(msg, sessionId);
    }

    public abstract void handleMessageInternal(T message, String sessionId);

    public IMain getMain()
    {
        return main;
    }
}