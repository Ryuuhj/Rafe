import "../component/Layout/Text.css";
import "./css/StoragePage.css";
import Btn from "../component/Button/Btn";
import TransBtn from "../component/Button/TransBtn";
import PageLayout from "./PageLayout";
import RecipeHeader from "./RecipeHeader";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom"

function RecipePage() {
    const [recipeList, setRecipeList] = useState([]);

    const location = useLocation();
    console.log(location.state.categoryId)
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
        axios.get("/recipe", {
            params:{
                categoryId : categoryId
            }
        })
            .then(res => {
                getRecipe(res);
            })
    }, []);

    return (
        <div className="Recipe">
            <RecipeHeader category={categoryId}/>
            <PageLayout recipe={recipeList} />
        </div>
    )
}

export default RecipePage;