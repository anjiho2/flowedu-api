package com.flowedu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class EmailSendQuartz extends QuartzJobBean implements StatefulJob {

    private final static Logger logger = LoggerFactory.getLogger(EmailSendQuartz.class);

    private ApplicationContext ctx;

    @Override
    protected void executeInternal(JobExecutionContext ex)
            throws JobExecutionException {
        // TODO Auto-generated method stub
        ctx = (ApplicationContext) ex.getJobDetail().getJobDataMap().get("applicationContext");
        try {
            executeJob(ex);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
    private void executeJob(JobExecutionContext ex) throws Exception {
        //TODO 회원가입 이메일 발송기능 로직 추가.
    }
}
