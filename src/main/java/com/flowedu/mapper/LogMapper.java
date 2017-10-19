package com.flowedu.mapper;

import com.flowedu.dto.LecturePaymentLogDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
public interface LogMapper {

    void insertLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto);

    void insertMemberLoginLog(@Param("memberId") Long memberId, @Param("memberName") String memberName);

    void deleteMemberLoginLog(@Param("paymentId") Long paymentId);

    void updateMemberLoginLog(LecturePaymentLogDto lecturePaymentLogDto);

    List<LecturePaymentLogDto> paymentMemberList();
}
