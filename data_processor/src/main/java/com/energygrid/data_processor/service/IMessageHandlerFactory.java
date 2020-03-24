package com.energygrid.data_processor.service;

public interface IMessageHandlerFactory {
    IMessageHandler getHandler(String simpleType, Object object);
}
