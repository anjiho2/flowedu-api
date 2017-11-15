package com.flowedu.service.impl;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
import com.flowedu.error.FlowEduErrorCode;
import com.flowedu.error.FlowEduException;
import com.flowedu.mapper.LogMapper;
import com.flowedu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Service
public class LogServiceImpl implements LogService {

@Autowired
    private LogMapper logMapper;

    /**
     * <PRE>
     * 1. Comment : 카드결제로그정보 저장
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .15
     * </PRE>
     * @param lecturePaymentLogDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto) {
        if (lecturePaymentLogDto == null) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        logMapper.insertLecturePaymentLog(lecturePaymentLogDto);
    }

    /**
     * <PRE>
     * 1. Comment : 로그인 로그 저장
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .15
     * </PRE>
     * @param loginLogDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLoginLog(LoginLogDto loginLogDto) {
        if(loginLogDto == null) return;
        logMapper.saveLoginLog(loginLogDto);
    }

    /**
     * <PRE>
     * 1. Comment : 카드결제정보 리스트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .15
     * </PRE>
     * @param lectureRelId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<LecturePaymentLogDto> receiptList(Long lectureRelId) {
        List<LecturePaymentLogDto> Arr = logMapper.receiptList(lectureRelId);
        return Arr;
    }



}





