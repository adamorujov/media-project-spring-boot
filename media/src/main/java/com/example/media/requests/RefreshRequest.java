package com.example.media.requests;

import lombok.Data;

@Data
public class RefreshRequest {

    Long userId;
    String refreshToken;


}
