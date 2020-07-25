package by.jrr.mailbot;

import by.jrr.mailbot.engine.MailEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.mail.MessagingException;

@SpringBootApplication
public class MailbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailbotApplication.class, args);

    }

}
