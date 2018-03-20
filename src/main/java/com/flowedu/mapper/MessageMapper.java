package com.flowedu.mapper;

import com.flowedu.dto.EmailSendReservationDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by WONEUNJEONG on 2018-01-23.
 */
public interface MessageMapper {

    void saveEmailAddress(@Param("emailList") List<EmailSendReservationDto>emailSendReservationDtoList);

    List<EmailSendReservationDto> getEmailSendReservationList(@Param("sendYn") Boolean sendYn);

    void updateEmailSendReservationStatus(@Param("sendYn") Boolean sendYn, @Param("idx") Long idx);

    void deleteEmailList(@Param("sendYn") Boolean sendYn);
}
