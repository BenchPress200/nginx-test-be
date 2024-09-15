package com.test.nginxtestbe.service;

import com.test.nginxtestbe.dto.ActiveUserResponse;
import com.test.nginxtestbe.dto.LoginRequest;
import com.test.nginxtestbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerAndLogin(LoginRequest loginRequest) {
        userRepository.createNewUser(loginRequest);
    }

    public void logout(String nickname) {
        userRepository.removeUser(nickname);
    }

    public List<ActiveUserResponse> findActiveUsers() {
        return userRepository.findActiveUsers();
    }

}
