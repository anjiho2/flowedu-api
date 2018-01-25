package com.flowedu.controller;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
import com.flowedu.error.FlowEduErrorCode;
import com.flowedu.error.FlowEduException;
import com.flowedu.service.LogService;
import com.flowedu.util.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Controller
@RequestMapping(value = "log")
public class LogController {

    protected static final Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    /**
     * <PRE>
     * 1. Comment : 카드결제정보 저장
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .15
     * </PRE>
     * @param lecturePaymentLogDto
     * @return
     * @throws Exception
     */
   @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity savePayment(@RequestBody LecturePaymentLogDto lecturePaymentLogDto) throws Exception{
        if (lecturePaymentLogDto == null) {
            throw new FlowEduException(FlowEduErrorCode.CUSTOM_PAYMENT_LOG_NULL);
        }
        logService.saveLecturePaymentLog(lecturePaymentLogDto);
        return new ResponseEntity("OK", HttpStatus.OK);
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
    @RequestMapping(value = "/payment/receipt_list/{lectureRelId}", method = RequestMethod.GET)
    public ResponseEntity paymentList(@PathVariable Long lectureRelId) {
        List receipList = logService.receiptList(lectureRelId);
        return new ResponseEntity(receipList, HttpStatus.OK);
    }


    /**
     * <PRE>
     * 1. Comment : 카드결제정보 단일리스트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .20
     * </PRE>
     * @param lecturePaymentLogId
     * @return
     */
    @RequestMapping(value = "/payment/receipt_list_payment_log_id/{lecturePaymentLogId}", method = RequestMethod.GET)
    public ResponseEntity paymentInfo(@PathVariable Long lecturePaymentLogId){
        List receipList = logService.receiptListOne(lecturePaymentLogId);
        return new ResponseEntity(receipList, HttpStatus.OK);
    }

    /**
     * <PRE>
     * 1. Comment : 로그인 로그 저장
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .15
     * </PRE>
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login_log", method = RequestMethod.POST)
    public ResponseEntity saveLoginLog(@RequestBody String jsonStr) throws Exception{
        JsonParser parser = new JsonParser(jsonStr);
        Long memberId = (Long) parser.val("flowMemberId");
        String memberName = (String)parser.val("memberName");
        String connectIp = (String)parser.val("connectIp");

        LoginLogDto dto = new LoginLogDto(memberId, memberName, connectIp);
        logService.saveLoginLog(dto);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     *
     *
     * <PRE>
     * 1. Comment : 카드취소 로그 업데이트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2017. 11 .20
     * </PRE>
     * @param lecturePaymentLogId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/payment/cancel/{lecturePaymentLogId}", method = RequestMethod.PUT)
    public ResponseEntity cancelPaymentLog(@PathVariable Long lecturePaymentLogId) throws Exception{
        logService.cancelPaymentLog(lecturePaymentLogId);
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}



