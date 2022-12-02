import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";

import TransBtn from "../component/Button/TransBtn";
import "./css/IngredientDetailPage.css";

function IngredientDetailPage() {
    const location = useLocation();
    //console.log(location.state.ingredient_id)
    const ingredient_id = location.state.ingredientId
    const [ingreList, setIngreList] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:3001/ingredientDetail", {
            params: {
                ingredientId: ingredient_id,
                userId: localStorage.getItem('userId')
            }
        })
            .then(res => {
                console.log(res.data)
                setIngreList(res.data);
            })
    }, [])

    return (
        <div>
            {ingreList.map((val) => {
                return (
                    <div>
                        <div className="inName">
                            <h2 id="inName__name">{val.igName}</h2>
                                <button id="cart_btn" onClick={() => { console.log('장바구니 추가') }}>
                                    <img id="cart_img" src="../../img/shopping_cart.png" alt="장바구니 추가" />
                                </button>
                            
                        </div>
                        <div className="about_recipe">
                            {val.recipes.map((recipe) => {
                                return (
                                    <Link to={`/recipe/detail`} state={{ recipeId: recipe.recipeId }}>
                                    <div key={recipe.recipeId} className="about_recipe__box">
                                        <img src={recipe.recipeImg} alt="이미지가 없습니다." className="about_recipe__img" />
                                        <p>{recipe.recipeTitle}</p>
                                    </div>
                                    </Link>
                                )
                            })}
                        </div>
                        {/* 최저가 찾기 추가 */}
                    </div>
                );
            })}
        </div>
    );
}

export default IngredientDetailPage;