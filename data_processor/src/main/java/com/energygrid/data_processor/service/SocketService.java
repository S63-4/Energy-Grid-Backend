package com.energygrid.data_processor.service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ServerEndpoint("/server/")
public class SocketService extends WebSocketBase {
    private static List<Session> sessions = new ArrayList<>();
    private static SocketService socketService;

    public static List<Session> getSessions() {
        return sessions;
    }

    public static SocketService getInstance() {
        if (socketService == null){
            socketService = new SocketService();
        }
        return socketService;
    }

    @OnOpen
    public void onConnect(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println(message);
        String sessionId = session.getId();
        EncapsulatedMessage msg = getGson().fromJson(message, EncapsulatedMessage.class);
        getProcessor().processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        Logger.getLogger(cause.toString());
    }

    public void sendTo(String sessionId, Object object)
    {
        String msg = new EncapsulatingMessageGenerator().generateMessageString(object);
        sendToClient(getSessionFromId(sessionId), msg);
        System.out.println(msg);
    }

    @Override
    public void stop() {
        //START AND STOP ARE HANDLED ELSEWHERE
    }

    @Override
    public void start() {
        //START AND STOP ARE HANDLED ELSEWHERE
    }

    public Session getSessionFromId(String sessionId)
    {
        for(Session s : sessions)
        {
            if(s.getId().equals(sessionId))
                return s;
        }
        return null;
    }

    public void broadcast(Object object)
    {
        for(Session session : sessions) {
            sendTo(session.getId(), object);
        }
    }

    public void sendToOthers(String sessionId, Object object)
    {
        Session session = getSessionFromId(sessionId);
        for(Session ses : sessions) {
            if(ses.getId().equals(session.getId()))
                sendTo(ses.getId(), object);
            System.out.println(object);
        }
    }

    private void sendToClient(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            Logger.getLogger(e.toString());
        }
    }
}