package com.flowedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsSendLogDto {

    private Long smsSendLogIdx;

    private int result_code;

    private String message;

    private int msg_id;

    private int success_cnt;

    private int error_cnt;

    private String msg_type;

   // private String createDate;

    public SmsSendLogDto() {}
    /*
    public SmsSendLogDto(int resultCode, String message, int msgId, int successCnt, int errorCnt, String msgType) {
        this.resultCode = resultCode;
        this.message = message;
        this.msgId = msgId;
        this.successCnt = successCnt;
        this.errorCnt = errorCnt;
        this.msgType = msgType;
    }
    */
}
