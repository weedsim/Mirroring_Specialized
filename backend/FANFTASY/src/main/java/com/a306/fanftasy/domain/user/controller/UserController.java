package com.a306.fanftasy.domain.user.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/user")
@Slf4j
public class UserController {

    public ResponseEntity<?> login(String walletID){

        return null;
    }

}
