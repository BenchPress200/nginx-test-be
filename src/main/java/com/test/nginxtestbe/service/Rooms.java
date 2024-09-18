package com.test.nginxtestbe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class Rooms {
    private final ConcurrentHashMap<String, List<UserSession>> rooms = new ConcurrentHashMap<>();

    public void addUser(
            String roomName,
            String nickname,
            WebSocketSession webSocketSession
    ) {
        if(!has(roomName)) {
            rooms.put(roomName, new ArrayList<>());
        }

        rooms.get(roomName).add(
                UserSession.builder()
                        .webSocketSession(webSocketSession)
                        .nickname(nickname)
                        .build()
        );
    }

    public void removeUser(
            String roomName,
            String nickname
    ) {
        rooms.get(roomName).removeIf(
                userSession -> userSession.isNickname(nickname)
        );
    }

    public void sendMessage(String roomName, String nickname, String message) throws IOException {
        List<UserSession> userSessions = rooms.get(roomName);

        for (UserSession userSession : userSessions) {
            userSession.sendMessage(nickname, message);
        }
    }

    private boolean has(String roomName) {
        return rooms.containsKey(roomName);
    }
}
