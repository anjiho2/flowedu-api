package com.flowedu.mapper;

import com.flowedu.dto.LecturePaymentLogDto;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jihoan on 2017. 10. 12..
 */
public interface LogMapper {

    void insertLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto);
}
