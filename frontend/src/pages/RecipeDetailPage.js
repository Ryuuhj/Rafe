import "../component/Layout/Text.css";

import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import RecipeDetail from "../component/Layout/RecipeDetail";

function RecipeDetailPage(){
    const location = useLocation();
    const recipeId = location.state.recipeId
    const [recipeList, setRecipeList] = useState([]);
    const [recipeLike, setRecipeLike] = useState(null);
    const data = {
        userId : localStorage.getItem('userId'),
        recipeId : recipeId
    }
    console.log("recipeId", recipeId)

    useEffect(() => {
        //axios.get("http://localhost:3001/recipe_detail")
        axios.post("http://localhost:8080/recipe/detail", data)
        .then(res => {
            setRecipeList(res.data);
            setRecipeLike(res.data[0].recipeLike)
        })
    }, [])

    return(
        <div>
            {recipeLike != null
            ? <RecipeDetail recipe={recipeList} recipeLike={recipeLike} />
            : <p>Loading..</p>}
        </div>
    )
}

export default RecipeDetailPage;