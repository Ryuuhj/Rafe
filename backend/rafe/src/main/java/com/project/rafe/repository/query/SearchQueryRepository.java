package com.project.rafe.repository.query;

import com.project.rafe.domain.Recipe.dto.FastUseRecipeDto;
import com.project.rafe.domain.Recipe.dto.HotRecipeDto;
import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.domain.Recipe.search.SearchCondDto;
import com.project.rafe.domain.RecipeIngredient.QRecipeIngredient;
import com.project.rafe.domain.storage.QStorage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.rafe.domain.Recipe.QRecipe.recipe;
import static com.project.rafe.domain.RecipeIngredient.QRecipeIngredient.recipeIngredient;

@RequiredArgsConstructor
@Repository
public class SearchQueryRepository {
    private final JPAQueryFactory queryFactory;
    QRecipeIngredient riSub = new QRecipeIngredient("riSub");
    QStorage storage = new QStorage("storage");

    public List<HotRecipeDto> getHotRecipe() {
        return queryFactory
                .select(Projections.constructor(HotRecipeDto.class, recipe))
                .from(recipe)
                .orderBy(recipe.likeCount.desc())
                .fetch();
        //인기순 조회
    }

    //우선 소비 조회
    public List<FastUseRecipeDto> getFastUseRecipe(Long userId) {
        return queryFactory
                .select(Projections.constructor(FastUseRecipeDto.class,
                        recipeIngredient.recipe.recipeId,
                        recipeIngredient.recipe.recipeTitle,
                        recipeIngredient.recipe.recipeMainImg.as("recipeImg"),
                        recipeIngredient.recipe.count().as("match"))
                )
                .from(recipeIngredient, storage)
                .where(recipeIngredient.ingredient.igId.eq(storage.ingredient.igId)
                        .and(storage.userId.eq(userId))
                        .and(storage.fastUse.eq(1)))
                .groupBy(recipeIngredient.recipe.recipeId)
                .orderBy(Expressions.stringPath("match").desc())
                .fetch();
    }


    public List<SimpleRecipeDto> searchByCond(SearchCondDto cond) {
        //System.out.println("keyword??"+cond.getKeyword());

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
                        keywordCheck(includeKeyword(cond.getKeyword()), includeKeywordTag(cond.getKeyword())),
                        /*includeKeyword(cond.getKeyword())
                                .or(includeKeywordTag(cond.getKeyword())),*/
                        recipeIngredient.recipe.recipeId.notIn(JPAExpressions
                                .select(riSub.recipe.recipeId)
                                .from(riSub)
                                .where(riSub.ingredient.igId.in(cond.getExceptId())))
                )
                .fetch();
    }

    private BooleanExpression eqCategory(Long cg) {
        if ((cg == null) || (cg == 10)) { //카테고리 설정 없을 시 모든 레시피 출력 (where 조건 생략)
            return null;
        }
        return recipeIngredient.recipe.recipeCategory.eq(cg);
    }

    private BooleanExpression includeKeyword(String keyword) {
        if (keyword.isBlank()) {
            return null;
        }
        return recipeIngredient.recipe.recipeTitle.like("%" + keyword + "%");
    }

    private BooleanExpression includeKeywordTag(String keyword) {
        if (keyword.isBlank()) {
            return null;
        }
        return recipeIngredient.recipe.recipeTag.like("%" + keyword + "%");
    }

    private BooleanExpression keywordCheck(BooleanExpression includea, BooleanExpression includeb){
        if (includeb == null) {
            return includea;
        } else if (includea == null) {
            return includeb;
        } else if (includea.and(includeb)== null) {
            return null;
        }
        return (includea.or(includeb));
    }
    private BooleanExpression includeIg(List<Long> ingredientId) {
        if (ingredientId.isEmpty()) {
            return null;
        }
        return recipeIngredient.ingredient.igId.in(ingredientId);
    }


}
