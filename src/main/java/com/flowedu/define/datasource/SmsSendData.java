package com.flowedu.define.datasource;

public enum SmsSendData {

    LECTURE_ATTEND("자녀분이 출석하였습니다."),
    LECTURE_DISMISS("자녀분이 하원하였습니다.")
    ;

    String smsContent;

    SmsSendData(String smsContent) {
        this.smsContent = smsContent;
    }

    public static String getSmsSendContent(String smsSendCode) {
        for (SmsSendData smsSendData : SmsSendData.values()) {
            if (smsSendData.toString().equals(smsSendCode)) {
                return smsSendData.smsContent.toString();
            }
        }
        return null;
    }

    public String getContent() {
        return this.smsContent;
    }

}
