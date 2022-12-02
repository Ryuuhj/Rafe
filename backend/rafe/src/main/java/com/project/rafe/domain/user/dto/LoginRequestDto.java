package com.project.rafe.domain.user.dto;

import com.project.rafe.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

    private String name;
    private String email;
    private String picture;

    @Builder
    public LoginRequestDto(String userEmail, String userName, String userPicture) {
        this.name = userName;
        this.email = userEmail;
        this.picture = userPicture;
    }
    //DB에 저장할 때 DTO -> Entity 거쳐서 저장
    public Users toEntity() {
        return Users.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }

}