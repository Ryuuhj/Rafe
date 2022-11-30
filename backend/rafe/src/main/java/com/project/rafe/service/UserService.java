package com.project.rafe.service;

import com.project.rafe.domain.user.Users;
import com.project.rafe.domain.user.dto.LoginRequestDto;
import com.project.rafe.domain.user.dto.SignupRequestDto;
import com.project.rafe.domain.user.dto.UserDto;
import com.project.rafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public Long save(SignupRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUserId();
    }

    @Transactional
    public Optional<Users> findByUserEmail(String userEmail) {
        return userRepository.findUserByUserEmail(userEmail);
    }

    @Transactional
    public Long signUpGoogle(LoginRequestDto loginRequestDto) {
        try {
            SignupRequestDto newUser = new SignupRequestDto(loginRequestDto);
            return userRepository.save(newUser.toEntity()).getUserId();

        } catch (RuntimeException e) {
            logger.error("회원가입 실패: {}", e);
            return -1L;
        }
    }


    @Transactional
    public ResponseEntity<Map<String, Object>> loginGoogle(LoginRequestDto loginRequestDto) {
        Map<String, Object> userInfo = new HashMap<>();
        HttpStatus status = null;

        Optional<Users> result = userRepository.findUserByUserEmail(loginRequestDto.getUserEmail());

        try {
            if (result.isPresent()) {
                Users loginUser = result.get();

                userInfo.put("userId", loginUser.getUserId());
                //httpstate 세팅
                status = HttpStatus.OK;

            } else {//result가 null인 경우 -> 가입 X 상태
                userInfo.put("message", "회원 조회 결과 없음. 회원 가입 진행");
                //signUpGoogle(loginRequestDto);
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            userInfo.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(userInfo, status);
    }

    //유저 프로필 반환
    @Transactional(readOnly = true)
    public UserDto findByUserId(Long userId) {
        Users users = userRepository.findUserByUserId(userId).get();

        return new UserDto(users.getUserName(), users.getUserPicture());
    }
}
