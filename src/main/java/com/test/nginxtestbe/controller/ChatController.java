package com.test.nginxtestbe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.nginxtestbe.service.Rooms;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatController extends TextWebSocketHandler {

    private final Gson gson;
    private final Rooms rooms;

    @Override
    public void handleTextMessage(
            WebSocketSession webSocketSession,
            TextMessage textMessage
    ) throws IOException {
        JsonObject jsonMessage = gson.fromJson(textMessage.getPayload(), JsonObject.class);
        System.out.println(("메시지 도착: "+ jsonMessage));

        String id = jsonMessage.get("id").getAsString();

        switch(id) {
            case"join":
                join(jsonMessage, webSocketSession);
                break;
            case "leave":
                leave(jsonMessage, webSocketSession);
                break;
            case "sendMessage":
                sendMessage(jsonMessage, webSocketSession);
                break;
            default:
        }
    }
    private void join(JsonObject jsonMessage, WebSocketSession webSocketSession) {
        String roomName = jsonMessage.get("roomName").getAsString();
        String nickname = jsonMessage.get("nickname").getAsString();
        rooms.addUser(roomName, nickname, webSocketSession);
    }

    private void leave(JsonObject jsonMessage, WebSocketSession webSocketSession) {
        String roomName = jsonMessage.get("roomName").getAsString();
        String nickname = jsonMessage.get("nickname").getAsString();
        rooms.removeUser(roomName, nickname);
    }

    private void sendMessage(JsonObject jsonMessage, WebSocketSession webSocketSession) throws IOException {
        String roomName = jsonMessage.get("roomName").getAsString();
        String nickname = jsonMessage.get("nickname").getAsString();
        String message = jsonMessage.get("message").getAsString();
        rooms.sendMessage(
                roomName,
                nickname,
                message
        );
    }
}


