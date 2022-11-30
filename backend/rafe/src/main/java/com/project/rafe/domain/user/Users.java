package com.project.rafe.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(nullable = false, unique = true, name = "user_email")
    private String userEmail;

    @Column(name = "user_pic")
    private String userPicture;

    @Builder
    public Users(String name, String email, String picture) {
        this.userName = name;
        this.userEmail = email;
        this.userPicture = picture;
    }

}
