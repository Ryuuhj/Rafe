import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";

import TransBtn from "../component/Button/TransBtn";
import "./css/IngredientDetailPage.css";

function IngredientDetailPage() {
    const location = useLocation();
    //console.log(location.state.ingredient_id)
    const ingredient_id = location.state.ingredientId
    const [ingreList, setIngreList] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/ingredient/detail", {
            params : {
                ingredientId : ingredient_id,
                userId : localStorage.getItem('userId')
            }
        })
        .then(res => {
            console.log(res.data)
            setIngreList(res.data);
        })
    }, [])

    return (
        <div>
            {ingreList.map((val) => {
                        return (
                            <div>
                            <div className="inName"><h1 id="inName__name">{val.igName}
                            <button id="cart_btn" onClick={()=>{console.log('장바구니 추가')}}>
                                <img id="cart_img" src="../../img/shopping_cart.png" alt="장바구니 추가"/>
                            </button>
                            </h1>
                            </div>
                            <div className="reName">관련 레시피 {/*Link로 각각 레시피 상세 페이지로 연결 필요 */}
                            
                                <table id="reName__table">
                                    <tr>
                                        {val.recipeName.map((recipe) => {return (<th className="reName__th">{recipe}</th>)})}
                                    </tr>
                                    <tr>
                                        {val.recipeImage.map((image) => {return (<td className="reName__td">
                                            <img src={ image } alt="이미지가 없습니다." id="reName__img"></img>
                                        </td>)})}
                                    </tr>
                                </table>
                            </div>
                            <hr></hr>
                            <div>최저가 찾기</div>
                            </div>
                        );
                    })}
        </div>
    );
}

export default IngredientDetailPage;