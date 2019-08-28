package ru.stm.imdemo.server.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.stm.imdemo.server.domain.User;

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
