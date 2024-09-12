package com.test.nginxtestbe.service;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Builder
@Getter
public class UserSession {
    private WebSocketSession webSocketSession;
    private String nickname;

    public boolean isNickname(String nickname) {
        return this.nickname.equals(nickname);
    }

    public void sendMessage(String sender, String message) throws IOException {
        JsonObject jsonMessage = new JsonObject();
        jsonMessage.addProperty("id", "receiveMessage");
        jsonMessage.addProperty("sender", sender);
        jsonMessage.addProperty("message", message);

        webSocketSession.sendMessage(new TextMessage(jsonMessage.toString()));
    }

}
