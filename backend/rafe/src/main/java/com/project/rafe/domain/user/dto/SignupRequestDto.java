package com.project.rafe.domain.user.dto;

import com.project.rafe.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String userEmail;
    private String userName;
    private String userPicture;

/*    @Builder
    public SignupRequestDto(String userEmail, String userName, String userPicture) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPicture = userPicture;
    }*/

    @Builder
    public SignupRequestDto (LoginRequestDto loginRequestDto) {
        this.userName = loginRequestDto.getUserName();
        this.userEmail = loginRequestDto.getUserEmail();
        this.userPicture = loginRequestDto.getUserPicture();
    }


    //DB에 저장할 때 DTO -> Entity 거쳐서 저장
    public Users toEntity() {
        return Users.builder()
                .name(userName)
                .email(userEmail)
                .picture(userPicture)
                .build();
    }
}
