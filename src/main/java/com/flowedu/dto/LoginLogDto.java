package com.flowedu.dto;

import lombok.Data;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Data
public class LoginLogDto {

    private Long memberId;

    private String memberName;

    public LoginLogDto() {}

    public LoginLogDto(Long memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
