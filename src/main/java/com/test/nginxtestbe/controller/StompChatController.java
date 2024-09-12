package com.test.nginxtestbe.controller;

import com.test.nginxtestbe.dto.ChatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    @SendTo("/sub/{roomName}") // 여기로 구독한 사람들에게 보냄
    @MessageMapping("/{roomName}/chats")  // pub/~~ 으로 메시지 전송
    public ChatDTO sendMessage(
            ChatDTO chatDTO
    ) {
        return chatDTO;
    }
}

