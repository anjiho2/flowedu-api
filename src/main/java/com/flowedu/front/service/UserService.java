package com.flowedu.front.service;

import com.flowedu.domain.Student;
import com.flowedu.dto.StudentDto;

public interface UserService {

    Student authStudent(String userId, String userPass, String userType) throws Exception;

    Student getStudentInfo(Long studentId);

    void modifyStudentInfo(Long studentId, StudentDto studentDto);
}
