package com.flowedu.api.service.impl;

import com.flowedu.api.ApiService;
import com.flowedu.api.service.SmsService;
import com.flowedu.config.ConfigHolder;
import com.flowedu.define.datasource.SmsSendData;
import com.flowedu.domain.RequestApi;
import com.flowedu.domain.SmsReceiveInfo;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsServiceImpl extends ApiService implements SmsService {

    private String SMS_SEND_URL = "https://apis.aligo.in/send/";

    private String SMS_SEND_MASS_URL = "https://apis.aligo.in/send_mass/";

    private String USER_ID = "flowedu";

    /**
     * <PRE>
     * 1. Comment : 알리고API에 SMS발송하기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2018. 01. 31
     * </PRE>
     * @param receiver
     * @param smsSendCode
     * @return
     * @throws Exception
     */
    @Override
    public RequestApi sendSms(String receiver, String smsSendCode) throws Exception {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("key", ConfigHolder.getSmsAPiKey()));
        pairs.add(new BasicNameValuePair("userid", USER_ID));
        pairs.add(new BasicNameValuePair("sender", ConfigHolder.getSmsSendNumber()));
        pairs.add(new BasicNameValuePair("receiver", receiver));
        pairs.add(new BasicNameValuePair("msg", SmsSendData.getSmsSendContent(smsSendCode)));

        return sendPost(SMS_SEND_URL, pairs);
    }

    @Override
    public RequestApi sendSmsMass(List<SmsReceiveInfo> smsReceiveInfoList) throws Exception {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("key", ConfigHolder.getSmsAPiKey()));
        pairs.add(new BasicNameValuePair("userid", USER_ID));
        pairs.add(new BasicNameValuePair("sender", ConfigHolder.getSmsSendNumber()));

        int cnt = smsReceiveInfoList.size();
        int size = 1;
        String msg = "[플로우교육]회원님 출석알림 메세지";
        for (SmsReceiveInfo info : smsReceiveInfoList) {
            pairs.add(new BasicNameValuePair("rec_" + String.valueOf(size), info.getReceiver()));
            pairs.add(new BasicNameValuePair("msg_" + String.valueOf(size), msg.replaceFirst("회원님", "회원" + String.valueOf(size))));
            size++;
        }
        pairs.add(new BasicNameValuePair("cnt", String.valueOf(cnt)));
        pairs.add(new BasicNameValuePair("msg_type", "SMS"));

        return sendPost(SMS_SEND_MASS_URL, pairs);
    }
}
