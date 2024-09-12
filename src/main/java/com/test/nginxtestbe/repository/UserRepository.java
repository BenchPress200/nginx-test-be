package com.test.nginxtestbe.repository;

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

    public List<String> findActiveUsers() {
        return new ArrayList<>(activeUsers.keySet());
    }
}
