package com.project.rafe.domain.user;

import com.project.rafe.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, name = "user_email")
    private String userEmail;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(name = "user_pic")
    private String userPicture;


    @Builder
    public Users(String userName, String userEmail, String userPicture) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPicture = userPicture;
    }

    
}
