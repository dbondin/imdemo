package ru.stm.imdemo.server.controller;

import ru.stm.imdemo.server.domain.Message;
import ru.stm.imdemo.server.domain.User;
import ru.stm.imdemo.server.repos.MessageRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    //Приветствие
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    /*Возвращает главную страницу и все сообщения на ней
      Так же здесь реализован метод "filter" т.е. поиск по тегу
     */
    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Message> messages = messageRepo.findAll();
//FIXME
//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepo.findByTag(filter);
//        } else {
            messages = messageRepo.findAll();
//        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    //Метод добавления сообщений
    @PostMapping("/main")
    public String add(  @AuthenticationPrincipal User user,
                        @RequestParam String text,
                        @RequestParam String tag, Map<String, Object> model){
        Message message = new Message();
        message.setFromUser(user);
        message.setText(text);
        //FIXME message.setToUser(user);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }
}
