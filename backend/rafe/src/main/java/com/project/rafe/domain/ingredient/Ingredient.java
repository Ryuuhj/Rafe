package com.project.rafe.domain.ingredient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id", nullable = false)
    private Long igId;

    @Column(name = "ingredient_name", nullable = false)
    private String igName;

/*    @Column
    private Boolean lactose;

    @Column
    private Boolean caffaine;*/

    @Builder
    public Ingredient(String igName){
        this.igName = igName;
    }
}
