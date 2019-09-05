package ru.stm.imdemo.server.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ru.stm.imdemo.server.domain.Message;
import ru.stm.imdemo.server.domain.User;
import ru.stm.imdemo.server.dto.SendMessageBody;
import ru.stm.imdemo.server.repository.MessageRepository;
import ru.stm.imdemo.server.views.Views;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
    private MessageRepository messageRepository;

	@PostMapping
        public Message send(Principal principal, @RequestBody SendMessageBody body){
	    Message m = new Message();
	    m.setText(body.getText());
	    m.setToUser(body.getUser_id());
	    User u = (User) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
	    m.setFromUser(u);
	    return messageRepository.save(m);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepository.findAll();
    }
    @GetMapping("{id}")
    public Message getOnebyId(@PathVariable("id") Message message){
        return message;
    }



//
//    @PostMapping("/")
//    public Message create(@RequestBody Message message){
//    	// FIXME
//        //message.setCreationDate(LocalDateTime.now());
//        return messageRepository.save(message);
//    }
    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb,
                          @RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepository.delete(message);
    }
}
