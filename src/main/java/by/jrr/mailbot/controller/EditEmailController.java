package by.jrr.mailbot.controller;

import by.jrr.mailbot.engine.MailEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
public class EditEmailController {
    @Autowired
    MailEngine mailEngine;

    @GetMapping("/editor")
    public ModelAndView openEditor() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("site/emailEditor");
        return modelAndView;
    }

    @PostMapping ("/editor")
    public String createAndSend(@RequestParam String text) throws MessagingException {
        mailEngine.sendHTMLTestEmail(text);
        return "redirect:/editor";
    }
}
