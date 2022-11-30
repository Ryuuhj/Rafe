package com.project.rafe.domain.Recipe;

import com.project.rafe.domain.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.h2.engine.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RecipeLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeLike(Users users, Recipe recipe) {
        this.user = users;
        this.recipe = recipe;
    }

}
