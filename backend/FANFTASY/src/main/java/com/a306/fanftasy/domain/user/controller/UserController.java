package com.a306.fanftasy.domain.user.controller;


import com.a306.fanftasy.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
@Controller
@RequestMapping("api/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(String address){
        return ResponseEntity.ok(userService.login(address));
    }
}
