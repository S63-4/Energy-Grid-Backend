package com.energygrid.data_processor.service;

public interface IEncapsulatingMessageGenerator {
    EncapsulatedMessage generateMessage(Object content);
    String generateMessageString(Object content);
}