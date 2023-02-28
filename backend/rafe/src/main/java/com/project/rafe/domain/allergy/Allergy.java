package com.project.rafe.domain.allergy;

import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Builder
    public Allergy(Long userId, Ingredient ingredient){
        this.userId = userId;
        this.ingredient = ingredient;
    }

    public static Allergy addAllergy(Long userId, Ingredient ingredient) {
        Allergy allergy = Allergy.builder()
                .userId(userId)
                .ingredient(ingredient)
                .build();
        return allergy;
    }
}
