import "../component/Layout/Text.css";
import "./css/UserStoragePage.css";
import RecipePageLayout from "../component/Layout/RecipePageLayout";
import RecipeHeader from "../component/Layout/RecipeHeader";

import React, { useEffect, useState } from "react";
import axios from "axios";

export default function PopularRecipePage() {
    const [recipeList, setRecipeList] = useState([]);
    const categoryId = 6;

    //인기순 레시피 받아오기
    const getLikeList = (res) =>{
        if (res.data.success === undefined) {
            setRecipeList(res.data);
        }
        else {
            alert(res.data.message);
        }
    }

    useEffect(() => {
        axios.get("http://localhost:3001/data")
        .then(res => {
                getLikeList(res);
            })
    }, []);

    return (
        <div className="Recipe">
            <RecipeHeader category={categoryId}/>
            <RecipePageLayout recipe={recipeList} length={recipeList.length} />
        </div>
    )
}