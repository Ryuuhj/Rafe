import "../component/Layout/Text.css";
import "./css/LikeRecipePage.css";
import RecipePageLayout from "../component/Layout/RecipePageLayout";
import Btn from "../component/Button/Btn";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function LikeRecipePage() {
    const navigate = useNavigate();
    const userId = localStorage.getItem('userId')
    const [recipeList, setRecipeList] = useState([]);

    const getRecipe = (res) => {
        if (res.data.success === undefined) {
            setRecipeList(res.data);
        }else {
            alert(res.data.message);
        }
    };

    useEffect(() => {
        //axios.get(`http://localhost:3001/likeRecipe`)
        axios.get(`http://localhost:8080/recipe/like/${userId}`)
            .then(res => {
                getRecipe(res)
            })
    }, []);

    return (
        <div className="Recipe">
            <div className="like_title">
                <h2 className="like_title_name">찜한 레시피</h2>
            </div>

            {recipeList.length != 0
                ? <RecipePageLayout recipe={recipeList} length={recipeList.length} />
                : <div className="like_no">
                    <p className="like_no_notice">아직 찜한 레시피가 없습니다.</p>
                    <div className="like_no_btn">
                        <Btn context={"레시피 보러가기"} orange={false} onClick={(()=>{navigate('/recipe')})} />
                        </div>
                </div>
            }
        </div>
    )
}

export default LikeRecipePage;