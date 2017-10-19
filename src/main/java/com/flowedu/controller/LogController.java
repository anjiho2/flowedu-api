package com.flowedu.controller;

import com.flowedu.dto.LecturePaymentLogDto;
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

    @RequestMapping(value = "/pyment", method = RequestMethod.POST)
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

    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.PUT)
    public ResponseEntity updatePayment(@PathVariable Long paymentId, @RequestBody String jsonStr) throws Exception{
        logger.info("paymentId ------------> " + paymentId);

        JsonParser parser = new JsonParser(jsonStr);
        String lectureName = (String)parser.val("lectureName");
        Long price = (Long)parser.val("paymentPrice");
        String studentName = (String)parser.val("studentName");
        String memberName =  (String)parser.val("memberName");

        LecturePaymentLogDto dto = new LecturePaymentLogDto(paymentId, lectureName, price.intValue(), studentName, memberName);
        logService.updateMemberLoginLog(dto);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/{paymentId}", method = RequestMethod.DELETE)
    public ResponseEntity deletePayment(@PathVariable Long paymentId) throws Exception{
        logger.info("delete_paymentId ------------> " + paymentId);

        logService.deleteMemberLoginLog(paymentId);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/member_login", method = RequestMethod.GET)
    public ResponseEntity saveMemberLogin(@RequestBody String jsonStr) throws Exception {
        JsonParser parser = new JsonParser(jsonStr);
        Long memberId = (Long)parser.val("memberId");
        String memberName = (String)parser.val("memberName");
        logService.saveMemberLoginLog(memberId, memberName);

        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/payment/payment_list", method = RequestMethod.GET)
    public ResponseEntity paymentMemberList() throws Exception {
        List<LecturePaymentLogDto> Arr = logService.paymentMemberList();
        logger.info("--------------------->" + Arr);
        return new ResponseEntity(Arr, HttpStatus.OK);
    }

}
