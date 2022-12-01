import "./Text.css";
import "./RecipeDetail.css";
import TransBtn from "../Button/TransBtn";

import { useState } from "react";
import { Link } from "react-router-dom";
import RecipeDetailPage from "../../pages/RecipeDetailPage";

function RecipeDetail({ recipe }) {
    const [like, setLike] = useState(0)
    console.log(recipe)

    const onCllickLike = () => {
        setLike(1)
    }
    const unClikckLike = () => {
        setLike(0)
    }

    return (
        <div className="recipe">
            {recipe.map((val) => {
                return (
                    <div className="recipe__main">
                        <div className="recipe__imgBox">
                            <img className="recipe__img" src={val.recipe_main_img} alt="이미지가 없습니다." />
                            {like
                                ? <img className="recipe__like" src={'../../img/full_heart.png'} alt="찜하기" onClick={() => unClikckLike()} />
                                : <img className="recipe__like" src={'../../img/empty_heart.png'} alt="찜하기" onClick={() => onCllickLike()} />
                            }
                        </div>
                        <h3 className="recipe__title">{val.recipe_title}</h3>
                        <h4 id="recipe__ig_title">🥗재료🥗</h4>
                        <div className="recipe__ig"></div>
                    </div>
                )
            })}
        </div >
    )
}

export default RecipeDetail