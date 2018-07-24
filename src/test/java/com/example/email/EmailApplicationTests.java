package com.example.email;

import com.example.email.test.Builder;
import com.example.email.test.CarBuilder;
import com.example.email.test.Director;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTests {
    Logger logger=Logger.getLogger(EmailApplicationTests.class);
    @Autowired
    private JavaMailSenderImpl mailSender;


    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数


    @Test
    public void sendMessage() {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(Sender);
        mailMessage.setTo("2786846885@qq.com");
        mailMessage.setSubject("Test");
        mailMessage.setText("zhe这是一个测试邮件");
        mailSender.send(mailMessage);
    }

    @Test
    public void sendMultipartMessageg ()throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setFrom(Sender);
        mimeMessageHelper.setTo("2786846885@qq.com");
        mimeMessageHelper.setSubject("Test1");
        FileSystemResource resource=new FileSystemResource(new File("C:/Users/fate/Desktop/timg.jpg"));
        FileSystemResource resource1=new FileSystemResource(new File("C:/Users/fate/Desktop/接口.docx"));
        mimeMessageHelper.addInline("image.jpg",resource);
        mimeMessageHelper.addAttachment("小文档.docx",resource1);
        mailSender.send(mimeMessage);

    }
    @Test
    public void test1(){
        logger.info("test1");
        logger.debug("debug");
        logger.error("error");
        Builder builder=new CarBuilder();
        Director director=new Director(builder);
        director.build();

    }

}
