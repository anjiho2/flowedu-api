package com.flowedu.quartz;
import com.flowedu.dto.EmailSendReservationDto;
import com.flowedu.service.impl.MessageServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by WONEUNJEONG on 2018-01-26.
 * 이메일 발송내역 삭제
 */
@Component
public class EmailDeleteQuartz extends QuartzJobBean implements StatefulJob{
    private final static Logger logger = LoggerFactory.getLogger(EmailDeleteQuartz.class);

    private ApplicationContext ctx;

    @Override
    protected void executeInternal(JobExecutionContext ex) throws JobExecutionException {
        ctx = (ApplicationContext) ex.getJobDetail().getJobDataMap().get("applicationContext");
        try {
            executeJob(ex);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
    private void executeJob(JobExecutionContext ex) throws Exception{
        logger.info("이메일 삭제 쿼츠");

        MessageServiceImpl messageServiceImpl = (MessageServiceImpl) ctx.getBean("messageServiceImpl");
        List<EmailSendReservationDto> Arr = messageServiceImpl.getEmailSendReservationList(true);
        if(Arr.size() > 0){
            messageServiceImpl.deleteEmailList(true);
        }
    }
}
