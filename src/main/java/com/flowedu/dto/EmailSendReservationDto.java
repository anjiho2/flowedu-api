package com.flowedu.dto;

import lombok.Data;

/**
 * Created by WONEUNJEONG on 2018-01-24.
 */
@Data
public class EmailSendReservationDto {

    private Long idx;

    private String emailAddress;

    private String name;

    private String phoneNumber;

    private String authKey;

    private boolean sendYn;

    private String createDate;

}
