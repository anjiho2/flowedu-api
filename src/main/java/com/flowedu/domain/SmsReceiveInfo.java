package com.flowedu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsReceiveInfo {

    private String receiver;

    private String smsSendCode;
}
