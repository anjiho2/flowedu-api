package com.flowedu.service;

/**
 * Created by WONEUNJEONG on 2018-01-23.
 */

import com.flowedu.dto.EmailSendReservationDto;

import java.util.List;

public interface MessageService {

   void saveEmailAddress(List<EmailSendReservationDto>emailSendReservationDtoList);

   List<EmailSendReservationDto> getEmailSendReservationList(boolean sendYn);

   void updateEmailSendReservationStatus(Long idx, boolean sendYn);

   boolean sendEmail(List<EmailSendReservationDto>emailSendDtoList);

   void deleteEmailList(boolean sendYn);
}
