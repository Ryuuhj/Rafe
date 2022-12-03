import "./Text.css";
import "./css/RecipeDetail.css";
import TransBtn from "../Button/TransBtn";

import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function RecipeDetail({ recipe, recipeLike }) {
    const [like, setLike] = useState(recipeLike)
    
    const onCllickLike = () => {  //recipeLike 값 변동될 때마다 해당 값 넘겨주기
        setLike(!like)
        const req = {
            recipeId : recipe.recipeId,
            userId : localStorage.getItem('userId')
        }
        axios.post('http://localhost:8080/recipe/like', req)
        .then(res=> { //filled_Heart 값 넘어옴
            setLike(res.data.filled_Heart)
        })
    }

    return (
        <div className="recipe">
            {recipe.map((val) => {
                return (
                    <div className="recipe__main">
                        <div className="recipe__imgBox">
                            <img className="recipe__img" src={val.recipeMainImg} alt="이미지가 없습니다." />
                            {like
                                ? <img className="recipe__like" src={'../../img/full_heart.png'} alt="찜하기" onClick={() => onCllickLike()} />
                                : <img className="recipe__like" src={'../../img/empty_heart.png'} alt="찜하기" onClick={() => onCllickLike()} />
                            }
                        </div>
                        <h3 className="recipe__title">{val.recipeTitle}</h3>
                        <h4 className="recipe__ig_title">🥗재료🥗</h4>
                        <p id="recipe__ig__yes_title">보유 중인 재료 표시 : <span id="highlight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
                        <table className="recipe__ig">
                            {val.totalIgList.map((ig) => {
                                return (
                                    <tr className={ig.storage? "recipe__ig__yes" : null}>
                                        <td><Link to={`/ingredient/detail`} state={{igId : val.igId}}>
                                        {ig.igName}
                                        </Link></td>
                                        <td>··· &nbsp;&nbsp;{ig.igCount}</td>
                                        <td>
                                            <img id="recipe__ig__cart" src="../../img/shopping_cart.png" alt="장바구니 추가" onClick={() => { alert(`장바구니에 ${ig.igName} 추가!`) }}/>
                                        </td>
                                    </tr>
                                )
                            })}
                        </table>
                        <h4 className="recipe__ig_title">🍴레시피🍴</h4>
                            <table className="recipe__step">
                                {val.recipeStep.map((recipe) => {
                                    return (
                                        <tr>
                                            {recipe.img
                                            ? <td className="recipe__step__img"><img src={recipe.img} alt="이미지가 없습니다." style={{width:'100%'}}/></td>
                                            : <td className="recipe__step__img"><img src="../../img/no_img.png" alt="이미지가 없습니다." style={{width:'100%'}}/></td>}
                                            <td className="recipe__step__info">{recipe.text}</td>                                    
                                        </tr>
                                    )
                                })}
                            </table>
                    </div>
                )
            })}
        </div >
    )
}

export default RecipeDetail