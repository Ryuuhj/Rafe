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

    useEffect(() => {
        //axios.get("http://localhost:3001/recipe_detail")
        axios.post("http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/recipe/detail", data)
        .then(res => {
            setRecipeList(res.data);
            //console.log('recipeDetail res.data', res.data)
            setRecipeLike(res.data.recipeLike)
        })
    }, [])

    return(
        <div>
            {recipeLike != null
            ? <RecipeDetail val={recipeList} recipeLike={recipeLike} />
            : <p>Loading..</p>}
        </div>
    )
}

export default RecipeDetailPage;