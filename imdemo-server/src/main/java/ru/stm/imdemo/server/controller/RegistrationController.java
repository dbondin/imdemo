package ru.stm.imdemo.server.controller;

import ru.stm.imdemo.server.domain.Role;
import ru.stm.imdemo.server.domain.User;
import ru.stm.imdemo.server.repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    //Здесь вовзращается страничка регистрации
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    /**
        *Здесь реализована логика регистрации
        *Если при регитрации, пользователь с таким именем уже существует,
        *то возвращается "User exists!", после этого вовзращается страничка регистрации
        *Если же регистрация проходит валидно, тогда показывыаем что пользователь активный
        *Присваиваем ему роль, сохраняем и переадрессовываем на страничку авторизации
     */
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        //TODO user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login ";
    }
}
