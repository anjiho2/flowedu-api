package com.flowedu.service.impl;

import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;
import com.flowedu.error.FlowEduErrorCode;
import com.flowedu.error.FlowEduException;
import com.flowedu.mapper.LogMapper;
import com.flowedu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void saveLecturePaymentLog(LecturePaymentLogDto lecturePaymentLogDto) {
        if (lecturePaymentLogDto == null) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        logMapper.insertLecturePaymentLog(lecturePaymentLogDto);
    }


    @Override
    public List<LecturePaymentLogDto> paymentMemberList() {
        List<LecturePaymentLogDto> Arr = logMapper.paymentMemberList();
        return Arr;
    }

    @Override
    public void saveLoginLog(LoginLogDto loginLogDto) {
        if(loginLogDto == null) return;
        logMapper.saveLoginLog(loginLogDto);
    }

}





