package com.flowedu.service;



import com.flowedu.dto.LecturePaymentLogDto;
import com.flowedu.dto.LoginLogDto;

import java.util.List;

/**
 * Created by jihoan on 2017. 10. 12..
 */
public interface LogService {

    List<LecturePaymentLogDto> paymentMemberList();

    void saveLoginLog(LoginLogDto loginLogDto);

}
