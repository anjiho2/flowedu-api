package com.flowedu.front.service.impl;

import com.flowedu.define.datasource.DataSource;
import com.flowedu.define.datasource.DataSourceType;
import com.flowedu.domain.Student;
import com.flowedu.dto.StudentDto;
import com.flowedu.front.service.UserService;
import com.flowedu.mapper.UserMapper;
import com.flowedu.util.Aes256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * <PRE>
     * 1. Comment : 사용자가 있는지 확인하기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 03 .21
     * </PRE>
     * @param userId
     * @param userPass
     * @param userType
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    @DataSource(DataSourceType.ADMIN)
    public Student authStudent(String userId, String userPass, String userType) throws Exception {
        Long studentId = userMapper.selectUserCount(userId, Aes256.encrypt(userPass), userType);
        if (studentId != null) {
            return userMapper.selectStudentInfo(studentId);
        }
        return null;
    }

    /**
     * <PRE>
     * 1. Comment : 사용자 상세 정보 가져오기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 03 .21
     * </PRE>
     * @param studentId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    @DataSource(DataSourceType.ADMIN)
    public Student getStudentInfo(Long studentId) {
        return userMapper.selectStudentInfo(studentId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @DataSource(DataSourceType.ADMIN)
    public void modifyStudentInfo(Long studentId, StudentDto studentDto) {
        studentDto.setStudentId(studentId);
        userMapper.updateStudentInfo(studentDto);
    }
}
