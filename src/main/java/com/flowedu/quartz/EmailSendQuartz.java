package com.flowedu.quartz;

import com.flowedu.dto.EmailSendReservationDto;
import com.flowedu.service.MessageService;
import com.flowedu.service.impl.MessageServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;


@Component
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

        MessageServiceImpl messageServiceImpl = (MessageServiceImpl) ctx.getBean("messageServiceImpl");

        List<EmailSendReservationDto> Arr = messageServiceImpl.getEmailSendReservationList();
        //이메일발송
        if(Arr.size() > 0) {
            boolean isSuccess = messageServiceImpl.sendEmail(Arr);
            if (isSuccess) {
                for(EmailSendReservationDto dto : Arr) {
                    messageServiceImpl.updateEmailSendReservationStatus(dto.getIdx(), true);
                }
            }
        }
    }
}
