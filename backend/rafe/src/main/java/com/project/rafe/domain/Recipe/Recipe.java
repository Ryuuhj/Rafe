package com.project.rafe.domain.Recipe;

import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.StringListConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "recipe_title", nullable = false)
    private String recipeTitle;

    @Column(name = "recipe_mimg", nullable = false)
    private String recipeMainImg;


    @Column(name = "recipe_category", nullable = false)
    private Long recipeCategory;

    @Lob
    @Column(length = 10000)
    @Convert(converter = StringListConverter.class)
    private List<String> recipeStep;

    @Lob
    @Column(length = 10000)
    @Convert(converter = StringListConverter.class)
    private List<String> recipeStepImg;

    private String recipeTag;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<RecipeIngredient> igList = new ArrayList<>();

    @Column(name = "likes")
    private Long likeCount;

    @Builder
    public Recipe(String recipeTitle, String recipeMainImg, Long recipeCategory,
                  List<String> recipeStep, List<String> recipeStepImg, List<String> recipeTag) {

        this.recipeTitle = recipeTitle;
        this.recipeMainImg = recipeMainImg;
        this.recipeCategory = recipeCategory;
        this.recipeStep = recipeStep;
        this.recipeStepImg = recipeStepImg;
        this.recipeTag = StringUtils.join(recipeTag, ',');
        this.likeCount = 0L;
    }

    public Long updateLike(Boolean plus){
        if (plus) { //좋아요 +1인 경우
            this.likeCount += 1;
        } else { //좋아요 -1인 경우
            this.likeCount -= 1;
        }
        return likeCount;
    }


}
