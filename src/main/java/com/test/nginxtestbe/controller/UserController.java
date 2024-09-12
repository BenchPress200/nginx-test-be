package com.test.nginxtestbe.controller;

import com.test.nginxtestbe.dto.LoginRequest;
import com.test.nginxtestbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerAndLogin(
            @RequestBody LoginRequest loginRequest
    ) {
        userService.registerAndLogin(loginRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestParam("nickname") String nickname
    ) {
        userService.logout(nickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<?> findActiveUsers() {
        return new ResponseEntity<>(userService.findActiveUsers(), HttpStatus.OK);
    }
}
