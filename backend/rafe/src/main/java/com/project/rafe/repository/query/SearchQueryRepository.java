package com.project.rafe.repository.query;

import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.domain.Recipe.search.SearchCondDto;
import com.project.rafe.domain.RecipeIngredient.QRecipeIngredient;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.rafe.domain.RecipeIngredient.QRecipeIngredient.recipeIngredient;

@RequiredArgsConstructor
@Repository
public class SearchQueryRepository {
    private final JPAQueryFactory queryFactory;
    //List<String> lacto = Arrays.asList("우유", "요거트");
    QRecipeIngredient riSub = new QRecipeIngredient("riSub");
    public List<SimpleRecipeDto> searchByCond(SearchCondDto cond) {
        System.out.println("Eerrrrrr>>>>>>"+cond.getExceptId());

        return queryFactory
                .select(Projections.constructor(SimpleRecipeDto.class,
                        recipeIngredient.recipe.recipeId,
                        recipeIngredient.recipe.recipeTitle,
                        recipeIngredient.recipe.recipeMainImg.as("recipeImg")
                ))
                .distinct()
                .from(recipeIngredient)
                .where(
                        eqCategory(cond.getCategoryId()),
                        includeIg(cond.getIngredientId()),
                        includeKeyword(cond.getKeyword()),
                        recipeIngredient.recipe.recipeId.notIn(JPAExpressions
                                .select(riSub.recipe.recipeId)
                                .from(riSub)
                                .where(riSub.ingredient.igId.in(cond.getExceptId()))
                ))
                .fetch();
    }

    private BooleanExpression eqCategory(Long cg){
        if((cg == null)||(cg == 10)){ //카테고리 설정 없을 시 모든 레시피 출력 (where 조건 생략)
            return null;
        }
        return recipeIngredient.recipe.recipeCategory.eq(cg);
    }
    private BooleanExpression includeKeyword(String keyword){
        if (keyword == null) {
            return null;
        }
        return recipeIngredient.recipe.recipeTitle.like("%"+keyword+"%");
    }
    private BooleanExpression includeIg(List<Long> ingredientId) {
        if(ingredientId.isEmpty()){
            return null;
        }
        return recipeIngredient.ingredient.igId.in(ingredientId);
    }

    private BooleanExpression excludeIg(List<Long> exceptId) {
        if (exceptId.isEmpty()) {
            return null;
        }
        return recipeIngredient.ingredient.igId.notIn(exceptId);
    }
    private BooleanExpression eqCaffeine (Long yn){
        if (yn == null || yn == 0){
            return null;
        }
        return recipeIngredient.recipe.caffeine.ne(1L);
    }
    private BooleanExpression eqLactose (Long yn){
        if (yn == null || yn == 0){
            return null;
        }
        return recipeIngredient.recipe.lactose.ne(1L);
    }


}
