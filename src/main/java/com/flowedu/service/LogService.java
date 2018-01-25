package com.flowedu.service;



import com.flowedu.domain.LecturePaymentLog;
import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
public interface LogService {

    void saveLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto);

    List<LecturePaymentLogDto> receiptList(Long lectureRelId);

    List<LecturePaymentLogDto> receiptListOne(Long lecturePaymentLogId);

    void saveLoginLog(LoginLogDto loginLogDto);

    void cancelPaymentLog(Long lecturePaymentLogId);
}
