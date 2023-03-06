import "./Text.css";
import "./css/RecipeDetail.css";
import TransBtn from "../Button/TransBtn";

import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function RecipeDetail({ val, recipeLike }) {
    const [like, setLike] = useState(recipeLike)
    console.log('recipeDetail, val',val)

    const onCllickLike = () => {  //recipeLike 값 변동될 때마다 해당 값 넘겨주기
        setLike(!like)
        const req = {
            recipeId : val.recipeId,
            userId : localStorage.getItem('userId')
        }
        axios.post('http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/recipe/like', req)
        .then(res=> { //filled_Heart 값 넘어옴
            console.log('recipe/like res', res.data.filledHeart)
            setLike(res.data.filledHeart)
        })
    }
    
    const submitCart = (val) => {
        if(val.cart == 1){
            alert("이미 장바구니에 추가된 재료입니다.")
        }else{
            axios.post("http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/cart", {
                userId: localStorage.getItem('userId'),
                igId: val.igId
            }).then((res)=>{
                alert(`${val.igName}이 장바구니에 성공적으로 담겼습니다.`)
                console.log("장바구니 추가 후 res.data", res.data)
                if(res.data.cart === 0){
                    alert("이미 장바구니에 추가된 재료입니다.")
                }
            })
        }
    }

    return (
        <div className="recipe">
                    <div className="recipe__main">
                        <div className="recipe__imgBox">
                            <img className="recipe__img" src={val.recipeMainImg} alt="이미지가 없습니다." />
                            {like
                                ? <img className="recipe__like" src={'../../img/full_heart.png'} alt="찜하기" onClick={() => onCllickLike()} />
                                : <img className="recipe__like" src={'../../img/empty_heart.png'} alt="찜하기" onClick={() => onCllickLike()} />
                            }
                        </div>
                        <h3 className="recipe__title">{val.recipeTitle}</h3>
                        
                    <p className="recipe__title">매칭률 : {Math.floor(val.matchingRate)}%</p>
                        <h4 className="recipe__ig_title">🥗재료🥗</h4>
                        <p id="recipe__ig__yes_title">보유 중인 재료 표시 : <span id="highlight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p>
                        <table className="recipe__ig">
                            {val.totalIgList.map((ig) => {
                                return (
                                    <tr className={ig.storage? "recipe__ig__yes" : null}>
                                        <td className="recipe__ig__name"><Link to={`/ingredient/detail`} state={{igId : ig.igId}}>
                                        {ig.igName}
                                        </Link></td>
                                        <td>·· &nbsp;&nbsp;{ig.igCount}</td>
                                        <td>
                                            <img id="recipe__ig__cart" src="../../img/shopping_cart.png" alt="장바구니 추가" onClick={() => { submitCart(ig) }}/>
                                        </td>
                                    </tr>
                                )
                            })}
                        </table>
                        <h4 className="recipe__step__title">🍴레시피🍴</h4>
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
                
            
        </div >
    )
}

export default RecipeDetail