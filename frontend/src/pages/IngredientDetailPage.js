import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";

import TransBtn from "../component/Button/TransBtn";
import "./css/IngredientDetailPage.css";

function IngredientDetailPage() {
    const location = useLocation();
    //console.log('location',location.state.igId)
    const ingredient_id = location.state.igId
    const [ingreList, setIngreList] = useState([]);
    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
        console.log("ingredintId,", ingredient_id)
        console.log('userid,', localStorage.getItem('userId'))
        axios.post("http://localhost:8080/ingredient/detail", {
            userId: localStorage.getItem('userId'),
            igId: ingredient_id
        })
            .then(res => {
                //console.log(res.data.recipes)
                setIngreList(res.data);
                setRecipes(res.data.recipes)
            })
    }, [])

    return (
        <div>
            <div>
                <div className="inName">
                    {console.log(ingreList)}
                    <h2 id="inName__name">{ingreList.igName}</h2>
                    <button id="cart_btn" onClick={() => { console.log('장바구니 추가') }}>
                        <img id="cart_img" src="../../img/shopping_cart.png" alt="장바구니 추가" />
                    </button>

                </div>
                <div id="about_recipe_title">관련 레시피</div>
                <div className="about_recipe">
                    {ingreList.recipes && ingreList.recipes.map((val) => {
                        return (
                            <Link to={`/recipe/detail`} state={{ recipeId: val.recipeId }}>
                                <div key={val.recipeId} className="about_recipe__box">
                                    <img src={val.recipeImg} alt="이미지가 없습니다." className="about_recipe__img" />
                                    <p>{val.recipeTitle}</p>
                                </div>
                            </Link>
                        )
                    })
                    }

                </div>
                {/* 최저가 찾기 추가 */}
            </div>

        </div>
    );
}

export default IngredientDetailPage;