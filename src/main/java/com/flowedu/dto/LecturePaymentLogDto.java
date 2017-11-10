package com.flowedu.dto;

import com.flowedu.domain.KisPosOcx;
import lombok.Data;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Data
public class LecturePaymentLogDto {

    private Long lecturePaymentLogId;

    private String lectureName;

    private int paymentPrice;

    private String studentName;

    private String memberName;

    private String createDate;

    private KisPosOcx kisPosOcx;

    public LecturePaymentLogDto() {}

    public LecturePaymentLogDto(String lectureName, int paymentPrice, String studentName, String memberName) {
        this.lectureName = lectureName;
        this.paymentPrice = paymentPrice;
        this.studentName = studentName;
        this.memberName = memberName;

    }

    public LecturePaymentLogDto(Long lecturePaymentLogId, String lectureName, int paymentPrice, String studentName, String memberName){
        this.lecturePaymentLogId = lecturePaymentLogId;
        this.lectureName = lectureName;
        this.paymentPrice = paymentPrice;
        this.studentName = studentName;
        this.memberName = memberName;
    }
}
