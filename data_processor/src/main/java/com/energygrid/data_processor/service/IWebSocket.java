package com.energygrid.data_processor.service;

public interface IWebSocket {
    void setMessageProcessor(IServerMessageProcessor handler);
}