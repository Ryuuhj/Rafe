import "../component/Layout/Text.css";
import "./css/UserStoragePage.css";
import RecipePageLayout from "../component/Layout/RecipePageLayout";
import RecipeHeader from "../component/Layout/RecipeHeader";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom"

function RecipeResultPage() {
    const [recipeList, setRecipeList] = useState([]);

    const location = useLocation();
    console.log(location.state)
    const categoryId = location.state.categoryId
    const igId = location.state.ingredientId
    const keyword = location.state.keyward
    const exceptId = location.state.exceptIgId
    const exceptName = location.state.exceptIgName
    console.log("categoryId:", categoryId)
    console.log("igId:", igId)
    console.log("exceptId", exceptId)
    console.log("keyword:", keyword)
    console.log("exceptName:", exceptName)


    const getResult = (res) => {
        if (res.data.success === undefined) {
            setRecipeList(res.data);
        }
        else {
            alert(res.data.message);
        }
    };

    useEffect(() => {
        axios.post("http://localhost:8080/recipe/result", {
            userId : localStorage.getItem('userId'),
            categoryId: categoryId,
            keyword: keyword,
            ingredientId: igId,
            exceptId : exceptId
        })
        .then(res => {
                getResult(res);
            })
    }, []);

    return (
        <div className="Recipe">
            <RecipeHeader category={categoryId} exceptName = {exceptName} searchTxt = {keyword} />
            <RecipePageLayout recipe={recipeList} length={recipeList.length}/>
        </div>
    )
}

export default RecipeResultPage;