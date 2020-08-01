package by.jrr.mailbot.controller;

import by.jrr.mailbot.engine.MailEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
    public String createAndSend(@RequestParam Optional<String> text, @RequestParam Optional<MultipartFile> file) throws MessagingException, IOException {
//    public String createAndSend(@RequestParam Map<String,String> allParams) throws MessagingException, IOException {
        if(file.isPresent() && text.isPresent()) {
//            mailEngine.sendHTMLTestEmailWithInlineImages(text.get(), file.get().getBytes(), file.get().getOriginalFilename());
        }

//
        return "redirect:/editor";
    }
}
