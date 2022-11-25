package com.project.rafe.domain.user.Dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String userEmail;
    private String userName;
    private String userPicture;

    @Builder
    public LoginRequestDto(String userEmail, String userName, String userPicture) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPicture = userPicture;
    }

}
