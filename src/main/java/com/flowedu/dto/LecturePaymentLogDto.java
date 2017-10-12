package com.flowedu.dto;

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

    public LecturePaymentLogDto() {}

    public LecturePaymentLogDto(String lectureName, int paymentPrice, String studentName, String memberName) {
        this.lectureName = lectureName;
        this.paymentPrice = paymentPrice;
        this.studentName = studentName;
        this.memberName = memberName;
    }
}
