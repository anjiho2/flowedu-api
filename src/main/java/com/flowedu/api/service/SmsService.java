package com.flowedu.api.service;

import com.flowedu.domain.RequestApi;
import com.flowedu.domain.SmsReceiveInfo;

import java.util.List;

public interface SmsService {

    RequestApi sendSms(String receiver, String smsSendCode) throws Exception;

    RequestApi sendSmsMass(List<SmsReceiveInfo>smsReceiveInfoList) throws Exception;
}
