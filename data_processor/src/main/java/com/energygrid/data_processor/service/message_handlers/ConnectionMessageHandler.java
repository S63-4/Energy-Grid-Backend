package com.energygrid.data_processor.service.message_handlers;

import com.energygrid.data_processor.service.domain.IMain;
import com.energygrid.data_processor.service.domain.Message;

public class ConnectionMessageHandler extends MessageHandler<Message> {

    public ConnectionMessageHandler(IMain main) { super(main);}

    @Override
    public void handleMessageInternal(Message message, String sessionId) {
        getMain().connectionMade(sessionId);
    }
}