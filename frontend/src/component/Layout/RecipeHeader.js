import "./Text.css";
import "./css/RecipeHeader.css";

import { useState } from "react";
import { Link } from "react-router-dom";

function RecipeHeader({ category, exceptName, searchTxt }) {
    let name = ""
    switch (category) {
        case 0:
            name = "Coffee"
            break;
        case 1:
            name = "Latte"
            break;
        case 2:
            name = "Smoothie"
            break;
        case 3:
            name = "Juice"
            break;
        case 4:
            name = "Ade"
            break;
        case 5:
            name = "Cocktail"
            break;
        case 6:
            name = "인기순 레시피"
            break;
        default:
            name = "전체 레시피"
            break;
    }

    return (
        <div className="tot_recipe_box">
            {searchTxt && 
            <div className="recipe_search_condition_txt">
                "{searchTxt}" 검색 결과입니다.
            </div>
            }

            {(exceptName.length !== 0)
            ?
            <div> 
                <div className="recipe_search_condition_title">제외 재료 :</div>
            <div className="recipe_search_condition_ig">
                {exceptName.map((val)=>{
                    return(
                        <div>&nbsp;{val} |</div>
                    )
                })}
                </div>
                </div>
            :
            <div className="recipe_search_condition_title">제외 재료가 없습니다.</div>}
        <div className="recipe_search_box">
            <h2 className="recipe_search_box__name">{name}</h2>
            <Link to={`/recipe/search`} state={{ categoryId: category }}>
                <img className="recipe_search__img" src="../../img/search_img.png" alt="레시피 검색하기" />
            </Link>
        </div>
        </div>
    );
}

export default RecipeHeader;