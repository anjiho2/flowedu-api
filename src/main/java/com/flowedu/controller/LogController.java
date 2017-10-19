package com.flowedu.controller;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
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

   @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity savePayment(@RequestBody String jsonStr) throws Exception{
        JsonParser parser = new JsonParser(jsonStr);
        String lectureName = (String)parser.val("lectureName");
        Long price = (Long)parser.val("paymentPrice");
        String studentName = (String)parser.val("studentName");
        String memberName = (String)parser.val("memberName");

        LecturePaymentLogDto dto = new LecturePaymentLogDto(lectureName, price.intValue(), studentName, memberName);
        logService.saveLecturePaymentLog(dto);

        return new ResponseEntity("OK", HttpStatus.OK);
    }


    @RequestMapping(value = "/payment/payment_list", method = RequestMethod.GET)
    public ResponseEntity paymentMemberList() throws Exception {
        List<LecturePaymentLogDto> Arr = logService.paymentMemberList();
        logger.info("--------------------->" + Arr);
        return new ResponseEntity(Arr, HttpStatus.OK);
    }

    /* 로그인 로그저장 */
    @RequestMapping(value = "/login_log", method = RequestMethod.POST)
    public ResponseEntity saveLoginLog(@RequestBody String jsonStr) throws Exception{
        JsonParser parser = new JsonParser(jsonStr);
        Long memberId = (Long) parser.val("memberId");
        String memberName = (String)parser.val("memberName");

        LoginLogDto dto = new LoginLogDto(memberId, memberName);
        logService.saveLoginLog(dto);

        return new ResponseEntity("loginlog save success", HttpStatus.OK);
    }
}
