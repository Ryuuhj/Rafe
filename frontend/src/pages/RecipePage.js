import "../component/Layout/Text.css";
import "./css/StoragePage.css";
import RecipePageLayout from "../component/Layout/RecipePageLayout";
import RecipeHeader from "../component/Layout/RecipeHeader";

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
        // axios.get("http://localhost:3001/recipe", {
        //     params:{
        //         categoryId : categoryId
        //     }
        // })
        axios.get(`http://localhost:8080/recipe/${categoryId}`)
            .then(res => {
                console.log(res)
                getRecipe(res);
            })
    }, []);

    return (
        <div className="Recipe">
            <RecipeHeader category={categoryId}/>
            <RecipePageLayout recipe={recipeList} />
        </div>
    )
}

export default RecipePage;