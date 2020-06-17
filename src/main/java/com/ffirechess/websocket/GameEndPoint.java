package com.ffirechess.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/game/{gameId}")
public class GameEndPoint {
    private Session session;



    @OnOpen
    public void onOpen(Session session) throws IOException {
        // Get session and WebSocket connection
    }


    @OnMessage
    public void onMessage(Session session, String moveMessage) throws IOException {
        // Handle new messages
    }


    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
