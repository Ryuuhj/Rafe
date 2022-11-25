package com.project.rafe.domain.user.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String userName;
    private String userPicture;

    @Builder
    public UserDto(String userName, String userPicture) {
        this.userName = userName;
        this.userPicture = userPicture;
    }

}