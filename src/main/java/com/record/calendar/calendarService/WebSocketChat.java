package com.record.calendar.calendarService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value = "/chat")
@Slf4j
public class WebSocketChat extends TextWebSocketHandler {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session){
        log.info("open session : " + session.toString());
        if(!clients.contains(session)){
            clients.add(session);
            log.info("session open : " + session);
        }else{
            log.info("이미 연결된 session 입니다.");
        }
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        log.info("Receive message : " + msg);
        for(Session s : clients){
            log.info("send data : " + msg);
            s.getBasicRemote().sendText(msg);
        }
    }

    @OnClose
    public void onClose(Session s){
        log.info("session close : " + s);
        clients.remove(s);
    }


}
