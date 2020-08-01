package by.jrr.mailbot.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailEngine {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    SpringTemplateEngine templateEngine;
    @Value("classpath:/JG.png")
    Resource resourceFile;


    public void sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("*@mail.ru");
        message.setSubject("Daniil Hi");
        message.setText("Hello world from mailbot");
        mailSender.send(message);
    }
    public void sendHTMLTestEmail(String htmlText) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("*@mail.ru");
        helper.setSubject("Daniil Hi");
        helper.setText(htmlText, true);
        mailSender.send(message);
    }

    public void sendHTMLTestEmailWithLogo() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("*@mail.ru");
        helper.setSubject("Daniil Hi");
        helper.setText("<img src=\"cid:attachment.png\" /><H1>hello</h1><h3>world</h3>", true);
        helper.addInline("attachment.png", resourceFile);
        mailSender.send(message);
    }
    public void sendHTMLTestEmailWithInlineImages(String text, byte[] imgFile, String fileName) throws MessagingException, IOException {
        File tempFile = File.createTempFile("prefix", "suffix", null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(imgFile);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("*@mail.ru");
        helper.setSubject("Daniil Hi");
        helper.setText(text, true);
        helper.addInline(fileName, tempFile);
        mailSender.send(message);
    }

    public void sendThymeleafTestEmailWithLogo() throws MessagingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(new HashMap<String, Object>());
        String htmlBody = templateEngine.process("testEmail", thymeleafContext);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo("*@mail.ru");
        helper.setSubject("Daniil Hi");
        helper.setText(htmlBody, true);
        helper.addInline("attachment.png", resourceFile);
        mailSender.send(message);
    }
}
