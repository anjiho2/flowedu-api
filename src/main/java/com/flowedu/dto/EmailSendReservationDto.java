package com.flowedu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by WONEUNJEONG on 2018-01-24.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailSendReservationDto {

    private Long idx;

    private String emailAddress;

    private String memberName;

    private String phoneNumber;

    private String authKey;

    private boolean sendYn;

    private String createDate;

}
