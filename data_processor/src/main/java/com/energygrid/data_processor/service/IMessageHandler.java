package com.energygrid.data_processor.service;

public interface IMessageHandler {
    void handleMessage(String message, String sessionId);
}