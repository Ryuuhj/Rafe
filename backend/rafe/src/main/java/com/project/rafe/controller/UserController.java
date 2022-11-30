package com.project.rafe.controller;
import com.project.rafe.domain.user.Users;

import com.project.rafe.domain.user.dto.LoginRequestDto;
import com.project.rafe.domain.user.dto.UserDto;
import com.project.rafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login/google")
    public ResponseEntity<Map<String, Object>> loginGoogle(@RequestBody LoginRequestDto loginRequestDto) {

        ResponseEntity<Map<String, Object>> response = userService.loginGoogle(loginRequestDto);

        //기존 회원인 경우 -> 토큰 발급, userId 와 함께 response
        if (response.getBody().containsKey("userId")) {
            return response;
        }
        //기존 회원이 아닌 경우, signup 진행 후 다시 login 진행
        userService.signUpGoogle(loginRequestDto);
        return userService.loginGoogle(loginRequestDto);
    }

    @GetMapping("/user/{userId}")
    public UserDto setProfile(@PathVariable Long userId) {
        return userService.findByUserId(userId);
    }

/*
    @GetMapping("/storage/{userId}")
    public
*/


}