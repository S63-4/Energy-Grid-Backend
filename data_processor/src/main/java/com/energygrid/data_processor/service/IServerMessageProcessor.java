package com.energygrid.data_processor.service;

public interface IServerMessageProcessor {
    void processMessage(String sessionId, String type, String data);
}