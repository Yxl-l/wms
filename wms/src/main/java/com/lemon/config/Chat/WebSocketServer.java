package com.lemon.config.Chat;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint("/ws/{sid}")
public class WebSocketServer {

    private static Map<String, Session> sessionMap = new HashMap<>();

    /**
     * 连接建立时触发
     * @param session
     * @param sid
     */
    @OnOpen
    public void onOpen(Session session , @PathParam("sid") String sid){
        log.info("有客户端连接到了服务器 , {}", sid);
        sessionMap.put(sid, session);
    }

    /**
     * 服务端接收到消息时触发
     * @param session
     * @param message
     * @param sid
     */
    @OnMessage
    public void onMessage(Session session , String message, @PathParam("sid") String sid){
        log.info("接收到了客户端 {} 发来的消息 : {}", sid ,message);
        // 解析消息格式: "目标用户ID:消息内容"
        if (message.contains(":")) {
            String[] parts = message.split(":", 2);
            String targetSid = parts[0];
            String msgContent = parts[1];
            sendMessageToUser(targetSid,"用户"+ sid + "说: " + msgContent);
        } else {
            // 如果没有指定目标用户，则广播消息
            broadcastMessage("用户"+sid + "广播: " + message);
        }
    }

    /**
     * 连接关闭时触发
     * @param session
     * @param sid
     */
    @OnClose
    public void onClose(Session session , @PathParam("sid") String sid){
        System.out.println("连接断开:" + sid);
        sessionMap.remove(sid);
    }

    /**
     * 通信发生错误时触发
     * @param session
     * @param sid
     * @param throwable
     */
    @OnError
    public void onError(Session session , @PathParam("sid") String sid , Throwable throwable){
        System.out.println("出现错误:" + sid);
        throwable.printStackTrace();
    }

    /**
     * 发送消息给指定用户
     * @param sid 用户ID
     * @param message 消息内容
     */
    public void sendMessageToUser(String sid, String message) {
        Session session = sessionMap.get(sid);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 广播消息
     * @param message
     */
    public void broadcastMessage(String message) {
        Collection<Session> sessions = sessionMap.values();
        if(!CollectionUtils.isEmpty(sessions)){
            for (Session session : sessions) {
                if (session.isOpen()) {
                    try {
                        //服务器向客户端发送消息
                        session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}