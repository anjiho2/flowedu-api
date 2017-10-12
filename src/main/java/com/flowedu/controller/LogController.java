package com.flowedu.controller;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.service.LogService;
import com.flowedu.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Controller
@RequestMapping(value = "log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ResponseEntity savePayment(@RequestBody String jsonStr) throws Exception{
        JsonParser parser = new JsonParser(jsonStr);
        String lectureName = (String)parser.val("lecture_name");
        int price = Integer.parseInt((String)(parser.val("payment_price")));
        String studentName = (String)parser.val("student_name");
        String memberName = (String)parser.val("member_name");

        LecturePaymentLogDto dto = new LecturePaymentLogDto(lectureName, price, studentName, memberName);
        logService.saveLecturePaymentLog(dto);

        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
