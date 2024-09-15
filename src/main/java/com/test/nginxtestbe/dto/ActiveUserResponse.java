package com.test.nginxtestbe.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ActiveUserResponse {
    private String nickname;
    private String roomName;
}
