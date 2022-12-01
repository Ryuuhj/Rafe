import "../component/Layout/Text.css";
import "./css/StoragePage.css";
import Btn from "../component/Button/Btn";
import TransBtn from "../component/Button/TransBtn";
import PageLayout from "./PageLayout";
import RecipeHeader from "./RecipeHeader";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom"

function RecipeResultPage() {
    const [recipeList, setRecipeList] = useState([]);

    const location = useLocation();
    console.log(location.state)
    const categoryId = location.state.categoryId
    const lactos = location.state.lactos
    const caffeine = location.state.caffeine
    const igId = location.state.ingredientId
    const keyward = location.state.keyward
    console.log("categoryId:", categoryId)
    console.log("lactos:", lactos)
    console.log("caffeine:", caffeine)
    console.log("igId:", igId)
    console.log("keyward:", keyward)


    const getResult = (res) => {
        if (res.data.success === undefined) {
            setRecipeList(res.data);
        }
        else {
            alert(res.data.message);
        }
    };

    useEffect(() => {
        axios.post("/recipe/result", {
            categoryId: categoryId,
            lactos: lactos,
            caffeine: caffeine,
            ingredientId: igId,
            keyward: keyward
        })
        // axios.get("http://localhost:3001/recipe_result", {params:
        // {
        //     categoryId: categoryId,
        //     lactos: lactos,
        //     caffeine: caffeine,
        //     ingredientId: igId,
        //     keyward: keyward
        // }})
        .then(res => {
                getResult(res);
            })
    }, []);

    return (
        <div className="Recipe">
            <RecipeHeader category={categoryId} />
            <PageLayout recipe={recipeList} />
        </div>
    )
}

export default RecipeResultPage;