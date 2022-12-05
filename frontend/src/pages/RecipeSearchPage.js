import "../component/Layout/Text.css";
import "./css/RecipeSearchPage.css";
import Btn from "../component/Button/Btn";

import { useLocation } from "react-router-dom"
import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";


function RecipeSearchPage() {
    const [searchTxt, setSearchTxt] = useState([]);
    const [igList, setIgList] = useState([]);
    const [lactose, setLactose] = useState(0);
    const [caffeine, setCaffeine] = useState(0);
    const [selectIgList, setSelectIgList] = useState([]);
    const [selectIgName, setSelectIgName] = useState([]);
    const [btnActive, setBtnActive] = useState("");

    const location = useLocation();
    //console.log(location.state.categoryId)
    const categoryId = location.state.categoryId
    const userId = localStorage.getItem('userId')

    const setInit = () => {
        setCaffeine(0)
        setLactose(0)
        setSelectIgList([])
        setSelectIgName([])
    }
    const getIg = (res) => {
        if (res.data.success === undefined) {
            setIgList(res.data);
        }
        else {
            alert(res.data.message);
        }
    };

    useEffect(() => {
        axios.get(`http://localhost:8080/recipe/search/${userId}`)
            .then(res => {
                getIg(res);
            })
    }, []);

    const getSearch = () => {
        console.log(searchTxt)
        alert(searchTxt + '  검색하기 🔍')
        //recipe/result 페이지로 이동 (props : 카테고리 id, 선택한 재료id, 검색 키워드)
        // recipe result에서 props 값들 백엔드로 보내주고 결과 받아서 랜더링(pageLayout 사용)
    }

    const selectIg = (e, val) => {
        setBtnActive((prev)=>{
            return e.target.value;
        })
        setSelectIgList([...selectIgList, val.igId]) //선택한 재료 id값들 저장
        setSelectIgName([...selectIgName, val.igName])
        console.log(selectIgList)
    };


    return (
        <div>
            <div className="IngredientSearchBar">
                <input className="SearchInput" type="text" placeholder="검색어를 입력해주세요." value={searchTxt} onChange={(e) => {
                    setSearchTxt(e.target.value);
                }} /><br/><br/>
                {searchTxt
                    ? <p>🔍 {searchTxt}</p>
                    : null}
            </div>
            <div className="SelectBox">
            <h3 className="SelectBox__ig">제외 조건 선택</h3>
            <p className="SelectBox__ig">📢 검색 결과에서 유제품 또는 카페인 레시피를 제외하고 싶다면 선택해주세요.</p>
            <div className="ExceptBox">
                <Btn onClick={()=>{setLactose(1)}} context={"lactose"} orange={lactose? true : false} /> &nbsp;&nbsp;&nbsp;
                <Btn onClick={()=>{setCaffeine(1)}} context={"caffeine"} orange={caffeine? true : false} />                
            </div>

            <h3 className="SelectBox__ig">재료 선택</h3>
            <div className="IgSelectBox">  {/*필터 중에 caffaine이랑 lactose 있어야함!*/}
            {igList.map((val, idx)=>{
                return(
                    <div className="IgSelectBox__box">
                        <button value={idx}
                        className={"btn"+(idx == btnActive?"_active": "")}
                        onClick={(e)=>{selectIg(e, val)}}>
                            {val.igName}
                        </button>
                    </div>
                )
            })}
            </div>
            <div id="Select__igList">
                        🧺선택한 재료 목록🧺
                            {selectIgName.map((name)=>{
                                return(
                                    <div>{name}</div>
                                )
                            })}
                            </div>
            <div className="SelectBox__sumbit">
                <Btn onClick={()=>{setInit()}} context={"초기화"} orange={false} /> &nbsp;&nbsp;&nbsp;
                <Link to={`/recipe/result`} state={{
                    categoryId : categoryId,
                    keyward : searchTxt,
                    lactos : lactose,
                    caffeine : caffeine,
                    ingredientId : selectIgList
                }}>
                <Btn onClick={()=>{getSearch()}} context={"검색"} orange={false} />  
                </Link>              
            </div>

            </div>
        </div>
    )
}

export default RecipeSearchPage;