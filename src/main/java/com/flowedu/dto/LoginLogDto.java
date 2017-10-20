package com.flowedu.dto;

import lombok.Data;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Data
public class LoginLogDto {

    private Long memberLoginLogId;

    private Long memberId;

    private String memberName;

    private String connectIp;

    private String createDate;

    public LoginLogDto() {}

    public LoginLogDto(Long memberId, String memberName, String connectIp) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.connectIp = connectIp;
    }
}
