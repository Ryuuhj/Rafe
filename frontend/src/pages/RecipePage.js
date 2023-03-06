import "../component/Layout/Text.css";
import "./css/UserStoragePage.css";
import RecipePageLayout from "../component/Layout/RecipePageLayout";
import DefaultRecipeHeader from "../component/Layout/DefaultRecipeHeader";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom"

function RecipePage() {
    const [recipeList, setRecipeList] = useState([]);
    const location = useLocation();
    const categoryId = location.state.categoryId

    const getRecipe = (res) => {
        if (res.data.success === undefined) {
            setRecipeList(res.data);
        }
        else {
            alert(res.data.message);
        }
    };
    

    useEffect(() => {
        axios.get(`http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/recipe/${categoryId}`)
            .then(res => {
                getRecipe(res);
            })
    }, [categoryId]);

    return (
        <div className="Recipe">
            <DefaultRecipeHeader category={categoryId}/>
            <RecipePageLayout recipe={recipeList} length={recipeList.length} />
        </div>
    )
}

export default RecipePage;