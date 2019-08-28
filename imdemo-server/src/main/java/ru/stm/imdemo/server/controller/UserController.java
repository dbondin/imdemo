package ru.stm.imdemo.server.controller;

import com.fasterxml.jackson.annotation.JsonView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import ru.stm.imdemo.server.Entity.Message;
import ru.stm.imdemo.server.Entity.User;
import ru.stm.imdemo.server.Entity.Views;
import ru.stm.imdemo.server.repository.MessageRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @GetMapping
    public User getCurrentUserProfile(Principal principal){
    	if(principal instanceof OAuth2Authentication)
    	{
    		return (User) ((OAuth2Authentication) principal).getUserAuthentication().getPrincipal();
    	}
    	logger.info("{}", principal);
        return null;
    }
}
