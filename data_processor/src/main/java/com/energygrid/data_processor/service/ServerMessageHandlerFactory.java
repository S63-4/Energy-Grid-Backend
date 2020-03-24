package com.energygrid.data_processor.service;

import com.energygrid.data_processor.service.domain.IMain;
import com.energygrid.data_processor.service.message_handlers.ConnectionMessageHandler;

import java.util.logging.Logger;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {
    @Override
    public IMessageHandler getHandler(String simpleType, Object object) {
        IMain iMain = (IMain) object;
        switch(simpleType)
        {
            case "ConnectionMessage":
                return new ConnectionMessageHandler(iMain);
            default:
                Logger.getLogger("Message doesn't have a case");
                return null;
        }
    }
}
