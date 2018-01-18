package com.flowedu.domain;

import lombok.Data;

@Data
public class GoogleCredential {

    private String accessToken;

    private String refreshToken;

    private Long expireTokenSeconds;

    public GoogleCredential(String accessToken, String refreshToken, Long expireTokenSeconds) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireTokenSeconds = expireTokenSeconds;
    }
}
