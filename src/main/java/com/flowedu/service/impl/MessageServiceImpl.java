package com.flowedu.service.impl;

import com.flowedu.controller.MessageController;
import com.flowedu.dto.EmailSendReservationDto;
import com.flowedu.error.FlowEduErrorCode;
import com.flowedu.error.FlowEduException;
import com.flowedu.mapper.MessageMapper;
import com.flowedu.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by WONEUNJEONG on 2018-01-23.
 */
@Service
public class MessageServiceImpl implements MessageService{

    protected static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    protected JavaMailSender mailSender;


    /**
     * <PRE>
     * 1. Comment : 이메일예약발송 저장
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2018. 01. 26
     * </PRE>
     * @param emailSendReservationDtoList
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveEmailAddress(List<EmailSendReservationDto>emailSendReservationDtoList){
        if(emailSendReservationDtoList.size() == 0) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        messageMapper.saveEmailAddress(emailSendReservationDtoList);
    }

    /**
     * <PRE>
     * 1. Comment : 이메일 발송안된 FALSE값 리스트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2018. 01. 26
     * </PRE>
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmailSendReservationDto> getEmailSendReservationList() {
       List<EmailSendReservationDto>Arr = messageMapper.getEmailSendReservationList();
       return Arr;
    }

    /**
     * <PRE>
     * 1. Comment : 이메일 발송완료 업데이트
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2018. 01. 26
     * </PRE>
     * @param idx
     * @param sendYn
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEmailSendReservationStatus(Long idx, boolean sendYn) {
        if (idx == null) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        messageMapper.updateEmailSendReservationStatus(sendYn, idx);
    }

    /**
     * <PRE>
     * 1. Comment : 이메일 발송
     * 2. 작성자 : 원은정
     * 3. 작성일 : 2018. 01. 26
     * </PRE>
     * @param emailSendDtoList
     * @return
     */
    @Override
    public boolean sendEmail(List<EmailSendReservationDto>emailSendDtoList){
        if(emailSendDtoList.size() == 0) {
            throw new FlowEduException(FlowEduErrorCode.BAD_REQUEST);
        }
        logger.info("이메일 발송 시작");
        MimeMessage msg = mailSender.createMimeMessage();
         for(EmailSendReservationDto emailSendDto : emailSendDtoList){
             logger.info(">>>>>>>>>>>>>>>>>>" + emailSendDto.getEmailAddress());
             try {
                 String message = "<div style=\"border:solid 1px;width:700px;overflow: hidden;background: #fff;\">";
                 message += "<h2 style=\"border-bottom:solid 1px;width:100%;padding:20px;\">플로우교육</h2>";
                 message += "<div style=\"width:400px;margin:50px 150px;\">";
                 message += "<h3>" + emailSendDto.getMemberName() + "님</h3>";
                 message += "<h4>회원 아이디는 다음과 같습니다.</h4><br>";
                 message += "<table border=\"1\" style=\"border-collapse: collapse;\">";
                 message += "<tr>";
                 message += "<td>이름</td>";
                 message += "<td>" + emailSendDto.getMemberName() + "</td>";
                 message += "</tr>";
                 message += "<tr>";
                 message += "<td rowspan=\"3\">아이디</td>";
                 message += "<td>등록번호 : " + emailSendDto.getAuthKey() + "</td>";
                 message += "</tr>";
                 message += "<tr>";
                 message += "<td>휴대혼번호 : " + emailSendDto.getPhoneNumber() + "</td>";
                 message += "</tr>";
                 message += "<tr>";
                 message += "<td>이메일 주소 : " + emailSendDto.getEmailAddress() + "</td>";
                 message += "</tr>";
                 message += "</table><br>";
                 message += "<p>플로우교육은 쉽고 간편한 로그인을 위해<br>등록번호, 휴대폰번호, 이메일 주소 중 하나로<br>로그인이 가능하도록 서비스를 제공하고 있습니다.</p><br>";
                 message += "<button style=\"background: #ccc;\"><a href=\"http://169.56.71.251:8080/flowEdu/\" style=\"text-decoration: none;color:#000;\">플로우교육 AMS 바로가기</a></button>";
                 message += "<p style=\"margin:20px 0;\">본 메일은 발신전용 메일 입니다.<br>관련문의는 고객센터(031-698-3403)를 이용해 주세요.</p>";
                 message += "<div style=\"border-top:1px dashed;padding-top:20px;\">\n" +
                         "                플로우교육<br>경기도 성남시 분당구 수내동 16-6 N타워빌딩<br>COPYRIGTH&copy; FLOWEDU All rights reserved.\n" +
                         "            </div>";
                 message += " </div>\n" +
                         "    </div>";

                 msg.setSubject("플로우교육 교육 아이디 안내 이메일");
                 //msg.setText(emailSendDto.getContent());
                 msg.setContent(message, "text/html; charset=UTF-8");
                 msg.setRecipients(
                         MimeMessage.RecipientType.TO,
                         InternetAddress.parse(emailSendDto.getEmailAddress())
                 );
                 //참조자 입력
                 /*if (emailSendDtoList.getToCc() != null) {
                     InternetAddress[] address = new InternetAddress[emailSendDtoList.getToCc().size()];
                     int cnt = 0;
                     for (String ccUser : emailSendDtoList.getToCc()) {
                         address[cnt] = new InternetAddress(ccUser);
                         cnt++;
                     }
                     msg.setRecipients(MimeMessage.RecipientType.CC, address);
                 }*/
             } catch (MessagingException e) {
                 // TODO: handle exception
                 logger.info("MessagingException");
                 e.printStackTrace();
             }
             try {
                 mailSender.send(msg);
             } catch (MailException e) {
                 // TODO: handle exception
                 logger.info("MailException발생");
                 e.printStackTrace();
             }
             logger.info("이메일 발송 끝");
         }
         return true;
    }

}
