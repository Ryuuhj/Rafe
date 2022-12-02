package com.project.rafe.controller;
import com.project.rafe.domain.user.Users;

import com.project.rafe.domain.user.dto.LoginRequestDto;
import com.project.rafe.domain.user.dto.UserDto;
import com.project.rafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login/google")
    public ResponseEntity<Map<String, Object>> loginGoogle(@RequestBody LoginRequestDto loginRequestDto) {
        Map<String, Object> result = new HashMap<>();
        Long userId = userService.check_user(loginRequestDto.getEmail());

        //기존 회원이 아닌 경우, signup 진행 후 저장된 userId 반환
        if (userId == -1L) {
            userId = userService.signUpGoogle(loginRequestDto);
            if (userId == -1L) {
                result.put("error", "회원가입 실패");
            }
        }
        //기존 회원인 경우 -> userId 와 함께 response
        result.put("userId", userId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/user/{userId}")
    public UserDto setProfile(@PathVariable Long userId) {
        return userService.findByUserId(userId);
    }


}