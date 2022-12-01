import "../component/Layout/Text.css";
import "./css/RecipeHeader.css";

import { useState } from "react";
import { Link } from "react-router-dom";

function RecipeHeader({ category }) {
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
        default:
            name = "전체 레시피"
            break;
    }

    return (
        <div className="recipe_search_box">
            <h2 className="recipe_search_box__name">{name}</h2>
            <Link to={`/recipe/search`} state={{ categoryId: category }}>
                <img className="recipe_search__img" src="../../img/search_img.png" alt="레시피 검색하기" />
            </Link>
        </div>
    );
}

export default RecipeHeader;