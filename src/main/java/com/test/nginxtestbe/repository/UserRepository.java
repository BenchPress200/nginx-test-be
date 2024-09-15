package com.test.nginxtestbe.repository;

import com.test.nginxtestbe.dto.ActiveUserResponse;
import com.test.nginxtestbe.dto.LoginRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {
    private final HashMap<String, String> activeUsers;

    public UserRepository() {
        this.activeUsers = new HashMap<>();
    }

    public void createNewUser(LoginRequest loginRequest) {
        activeUsers.put(loginRequest.getNickname(), loginRequest.getRoomName());
    }

    public void removeUser(String nickname) {
        activeUsers.remove(nickname);
    }

    public List<ActiveUserResponse> findActiveUsers() {
        List<ActiveUserResponse> users = new ArrayList<>();

        for (String s : activeUsers.keySet()) {
            users.add(ActiveUserResponse.builder()
                    .nickname(s)
                    .roomName(activeUsers.get(s))
                    .build()
            );
        }

        return users;
    }
}
