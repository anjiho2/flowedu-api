package com.flowedu.mapper;

import com.flowedu.domain.Student;
import com.flowedu.dto.StudentDto;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /** SELECT **/
    Long selectUserCount(@Param("userId") String userId, @Param("userPass") String userPass, @Param("userType") String userType);

    Student selectStudentInfo(@Param("studentId") Long studentId);

    /** UPDATE **/
    void updateStudentInfo(StudentDto studentDto);
}
