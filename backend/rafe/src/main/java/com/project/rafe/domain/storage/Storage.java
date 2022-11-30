package com.project.rafe.domain.storage;

import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long storageId;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "fast_use")
    private Integer fastUse;

    @Builder
    public Storage(Long userId, Ingredient ingredient){
        this.userId = userId;
        this.ingredient = ingredient;
        this.fastUse = 0;
    }

    public static Storage addStorage(Long userId, Ingredient ingredient){
        Storage storage = Storage.builder()
                .userId(userId)
                .ingredient(ingredient)
                .build();
        return storage;
    }

    public void updateFastUse(Integer upadteValue) {
        this.fastUse = upadteValue;
    }
}
