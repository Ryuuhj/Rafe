package com.project.rafe.service;

import com.project.rafe.domain.user.Users;
import com.project.rafe.domain.user.dto.LoginRequestDto;
import com.project.rafe.domain.user.dto.UserDto;
import com.project.rafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    //db에 있는지 email로 체크 -> 있으면 userId 반환, 없으면 -1 반환
    public Long check_user(String email) {
        if (email == null) {
            System.out.println("이메일 없음!!!");}
        Optional<Users> user = userRepository.findUserByUserEmail(email);
        if (user.isEmpty()) {
            return -1L;
        }
        return user.get().getUserId();
    }

    @Transactional
    public Long signUpGoogle(LoginRequestDto loginRequestDto) {
        try {
            return userRepository.save(loginRequestDto.toEntity()).getUserId();

        } catch (RuntimeException e) {
            logger.error("회원가입 실패: {}", e);
            return -1L;
        }
    }


    //유저 프로필 반환
    @Transactional(readOnly = true)
    public UserDto findByUserId(Long userId) {
        Users users = userRepository.findUserByUserId(userId).get();

        return new UserDto(users.getUserName(), users.getUserPicture());
    }
}
