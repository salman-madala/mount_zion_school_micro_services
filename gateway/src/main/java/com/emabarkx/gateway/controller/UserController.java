package com.emabarkx.gateway.controller;

import com.emabarkx.gateway.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
