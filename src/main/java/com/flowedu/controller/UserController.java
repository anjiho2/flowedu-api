package com.flowedu.controller;

import com.flowedu.domain.Student;
import com.flowedu.dto.StudentDto;
import com.flowedu.error.FlowEduErrorCode;
import com.flowedu.error.FlowEduException;
import com.flowedu.front.service.UserService;
import com.flowedu.util.JsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * <PRE>
     * 1. Comment : 사용자가 로그인하기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 03 .21
     * </PRE>
     * @param userId
     * @param userPass
     * @param userType
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<Student> authStudent(@RequestParam("user_id") String userId,
                                      @RequestParam("user_pass") String userPass,
                                      @RequestParam("user_type") String userType) throws Exception {
        if ("".equals(userId) && "".equals(userPass)) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        Student student = userService.authStudent(userId, userPass, userType);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    /**
     * <PRE>
     * 1. Comment : 사용자의 상세 정보
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 03 .21
     * </PRE>
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/info/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Student> studentInfo(@PathVariable Long studentId) {
        if (studentId == null) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        Student student = userService.getStudentInfo(studentId);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    /**
     * <PRE>
     * 1. Comment : 사용자 정보 수정
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 03 .21
     * </PRE>
     * @param studentId
     * @param studentDto
     * @return
     */
    @RequestMapping(value = "/modify/{studentId}", method = RequestMethod.POST)
    public @ResponseBody String modifyStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        if (studentId == null) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        userService.modifyStudentInfo(studentId, studentDto);
        return new JsonBuilder().add("result", Long.toString(studentId)).build();
    }


}
