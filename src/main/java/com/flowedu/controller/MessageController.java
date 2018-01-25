package com.flowedu.controller;

import com.flowedu.dto.EmailSendReservationDto;
import com.flowedu.rabbitmq.Message;
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

    @RequestMapping(value = "/save_email" , method = RequestMethod.POST)
    public ResponseEntity saveEmailAddress(@RequestBody List<EmailSendReservationDto>emailSendReservationDtoList){
        //logger.info(" >>>>>>>>>>>>>>>>>> " + emailSendReservationDtoList);
        messageService.saveEmailAddress(emailSendReservationDtoList);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/test" , method = RequestMethod.POST)
    public ResponseEntity test(){
        messageService.updateEmailSendReservationStatus(1L, true);
        return new ResponseEntity("OK", HttpStatus.OK);
    }




}
