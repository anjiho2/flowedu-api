package com.flowedu.mapper;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
public interface LogMapper {

    void insertLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto);

    List<LecturePaymentLogDto> paymentMemberList();

    void saveLoginLog(LoginLogDto loginLogDto);
}
