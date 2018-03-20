package com.flowedu.mapper;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
import com.flowedu.dto.SmsSendLogDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
public interface LogMapper {

    void insertLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto);

    void insertSmsSendLog(SmsSendLogDto smsSendLogDto);

    void saveLoginLog(LoginLogDto loginLogDto);

    List<LecturePaymentLogDto> receiptList(Long lectureRelId);

    List<LecturePaymentLogDto> receiptListOne(Long lecturePaymentLogId);

    String getAuthType(Long lecturePaymentLogId);

    void cancelPaymentLog(Long lecturePaymentLogId);

}
