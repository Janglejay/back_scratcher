package com.janglejay.controller;

import com.janglejay.entity.User;
import com.janglejay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        User user = new User();
        user.setAuthCode(1);
        boolean isAuth = userService.authUser(user);
        if (isAuth) {
            List<User> users = userService.queryAllUser();
            return users;
        }
        return ListUtils.of();
    }
}
