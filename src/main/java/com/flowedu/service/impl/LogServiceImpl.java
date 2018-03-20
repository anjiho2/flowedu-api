package com.flowedu.service.impl;

import com.flowedu.api.ApiService;
import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
import com.flowedu.dto.SmsSendLogDto;
import com.flowedu.error.FlowEduErrorCode;
import com.flowedu.error.FlowEduException;
import com.flowedu.mapper.LogMapper;
import com.flowedu.service.LogService;
import com.flowedu.util.GsonJsonUtil;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

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

    /**
     * <PRE>
     * 1. Comment : 단일 카드결제정보 리스트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .20
     * </PRE>
     * @param lecturePaymentLogId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<LecturePaymentLogDto> receiptListOne(Long lecturePaymentLogId){
        List<LecturePaymentLogDto> Arr = logMapper.receiptListOne(lecturePaymentLogId);
        return Arr;
    }

    /**
     * <PRE>
     * 1. Comment : 카드취소 업데이트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .20
     * </PRE>
     * @param lecturePaymentLogId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelPaymentLog(Long lecturePaymentLogId){
        if(lecturePaymentLogId == null) return;
        String getAuthType = logMapper.getAuthType(lecturePaymentLogId);
        if(getAuthType.equals("D2") || getAuthType.equals("CR")){
            throw new FlowEduException(FlowEduErrorCode.CUSTOM_PAYMENT_CANCEL_ERROR);
        }
        logMapper.cancelPaymentLog(lecturePaymentLogId);
    }

    /**
     * <PRE>
     * 1. Comment : SMS발송 내역 로그 저장하기
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2018. 01 .31
     * </PRE>
     * @param logInfo
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSmsSendLog(String logInfo) {
        if ("".equals(logInfo)) return;
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(logInfo);
        JsonObject object = element.getAsJsonObject();

        Gson gson = new Gson();
        SmsSendLogDto smsSendLogDto = gson.fromJson(object, SmsSendLogDto.class);
        logMapper.insertSmsSendLog(smsSendLogDto);
    }
}





