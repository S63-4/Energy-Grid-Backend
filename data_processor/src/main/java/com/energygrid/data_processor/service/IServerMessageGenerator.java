package com.energygrid.data_processor.service;

import java.util.List;

public interface IServerMessageGenerator {
    void notifyConnectionMade(String sessionId, String text);
}
