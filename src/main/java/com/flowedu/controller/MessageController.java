package com.flowedu.controller;

import com.flowedu.api.service.SmsService;
import com.flowedu.domain.RequestApi;
import com.flowedu.domain.SmsReceiveInfo;
import com.flowedu.dto.EmailSendReservationDto;
import com.flowedu.service.LogService;
import com.flowedu.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * Created by WONEUNJEONG on 2018-01-23.
 */

@Controller
@RequestMapping(value = "message")
public class MessageController {

    protected static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private LogService logService;

    /**
     * <PRE>
     * 1. Comment : 이메일예약발송 저장
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2018. 01. 26
     * </PRE>
     * @param emailSendReservationDtoList
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save_email" , method = RequestMethod.POST)
    public ResponseEntity saveEmailAddress(@RequestBody List<EmailSendReservationDto>emailSendReservationDtoList){
        messageService.saveEmailAddress(emailSendReservationDtoList);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /**
     * <PRE>
     * 1. Comment : SMS발송
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 01. 31
     * </PRE>
     * @param receiveInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/send_sms" , method = RequestMethod.POST)
    public ResponseEntity sendSms(@RequestBody SmsReceiveInfo receiveInfo) throws Exception {
        RequestApi requestApi = smsService.sendSms(receiveInfo.getReceiver(), receiveInfo.getSmsSendCode());
        if (requestApi.getHttpStatusCode() == 200) {
            logService.saveSmsSendLog(requestApi.getBody());
        }
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/send_sms_mass" , method = RequestMethod.POST)
    public ResponseEntity sendSmsMass(@RequestBody List<SmsReceiveInfo>receiveInfoList) throws Exception {
        RequestApi requestApi = smsService.sendSmsMass(receiveInfoList);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

}
