import "../component/Layout/Text.css";

import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import RecipeDetail from "../component/Layout/RecipeDetail";

function RecipeDetailPage(){
    const location = useLocation();
    const recipeId = location.state.recipeId
    const [recipeList, setRecipeList] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:3001/recipe_detail", {
            params : {
                recipeId : recipeId
            }
        })
        .then(res => {
            setRecipeList(res.data);
        })
    }, [])

    return(
        <RecipeDetail recipe={recipeList} />
    )
}

export default RecipeDetailPage;