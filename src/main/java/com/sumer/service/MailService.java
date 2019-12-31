package com.sumer.service;

import com.sumer.entity.MailInBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Component
public class MailService {

    @Autowired
    JavaMailSender mailSender; // 自动注入的Bean

    @Value("${spring.mail.username}")
    private String Sender; // 读取配置文件中的参数

    /**
     * 发送文本
     */
    public void sendText(String to, String zt, String tt){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Sender);
        message.setTo(to); // 设置发送方
        message.setSubject(zt);
        message.setText(tt);
        mailSender.send(message);
    }

    /**
     * 发送图片
     */
    public void sendPicture(MailInBean mib) throws MessagingException {

        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mm,true);

        mmh.setFrom(Sender);
        mmh.setTo(mib.getTo());
        mmh.setSubject(mib.getSbj());
        mmh.setText(mib.getHtml(),true);

        for (int i = 0; i <mib.getList().size() ; i++) {
            String [] strs = mib.getList().get(i).split("@@");
            FileSystemResource fsr = new FileSystemResource(new File(strs[0]));
            mmh.addInline(strs[1],fsr);
        }

        mailSender.send(mm);

        System.out.println(mib.getSbj()+"---发送完成");
    }
}
