package com.energygrid.data_processor.service;

import com.google.gson.Gson;

public class EncapsulatingMessageGenerator implements IEncapsulatingMessageGenerator {

    private Gson gson = new Gson();

    public EncapsulatedMessage generateMessage(Object content)
    {
        String messageForServerJson = gson.toJson(content);
        String type = content.getClass().getSimpleName();
        System.out.println(type);
        return new EncapsulatedMessage(type, messageForServerJson);
    }

    public String generateMessageString(Object content)
    {
        EncapsulatedMessage msg = generateMessage(content);
        return gson.toJson(msg);
    }
}