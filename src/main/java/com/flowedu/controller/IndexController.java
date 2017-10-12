package com.flowedu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Controller
@RequestMapping(value = "index")
public class IndexController {

    @RequestMapping(value = "/test")
    public ResponseEntity test() {
        System.out.print("===================================");
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
