import "../component/Layout/Text.css";
import "./css/RecipeSearchPage.css";
import Btn from "../component/Button/Btn";
import TransBtn from "../component/Button/TransBtn";

import { useLocation } from "react-router-dom"
import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";


function RecipeSearchPage() {
    const [searchTxt, setSearchTxt] = useState([]);
    const [igList, setIgList] = useState([]);
    const categoryList = [
                        {id:0, name: "Coffee"},
                        {id:1, name: "Latte"},
                        {id:2, name: "Smoothie"},
                        {id:3, name: "Juice"},
                        {id:4, name: "Ade"},
                        {id:5, name: "Cocktail"},
                        {id:10, name: "전체 레시피"}
                        ]
    const [category, setCategory] = useState(10);
    const [selectIgList, setSelectIgList] = useState([]);
    const [selectIgName, setSelectIgName] = useState([]);
    const [exceptTxt, setExceptTxt] = useState(""); //제외 재료 검색 키워드
    const [exceptIgList, setExceptIgList] = useState([]); //선택한 제외 재료 id
    const [exceptIgName, setExceptIgName] = useState([]); //선택한 제외 재료 이름
    const [searchList, setSearchList] = useState([]);
    const [btnActive, setBtnActive] = useState(-1);
    const [catActive, setCatActive] = useState(10);
    const [isVisible, setIsVisible] = useState(false);

    // const location = useLocation();
    // //console.log(location.state.categoryId)
    // const categoryId = location.state.categoryId
    const userId = localStorage.getItem('userId')

    const setInit = () => {
        setCategory(10)
        setSelectIgList([])
        setSelectIgName([])
        setExceptIgList([])
        setExceptIgName([])
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
        //axios.get('https://fd518520-055a-436e-a971-8a98dcc065fe.mock.pstmn.io/recipe/search')
            .then(res => {
                getIg(res);
            })
    }, []);

    const getSearch = async (res) => {
        setIsVisible(true)
        const result = await axios.get("http://localhost:8080/ingredient", {
        //const result = await axios.get("https://fd518520-055a-436e-a971-8a98dcc065fe.mock.pstmn.io/ingredient", {
            params: {
                id: userId,
                keyword: exceptTxt
            }
        })
        console.log(result.data)
        setSearchList(result.data.search_result)
        setExceptTxt("")
        
    }

    const selectIg = (e, val) => {
        setBtnActive((prev)=>{
            return e.target.value;
        })
        if(selectIgList.includes(val.igId) === true){
            alert(`${val.igName} 은(는) 이미 선택 재료 목록에 포함되어 있습니다.`)
        } else{
            setSelectIgList([...selectIgList, val.igId]) //선택한 재료 id값들 저장
            setSelectIgName([...selectIgName, val.igName])
        }
        // setSelectIgList([...selectIgList, val.igId]) //선택한 재료 id값들 저장
        // setSelectIgName([...selectIgName, val.igName])
        // console.log(selectIgList)
    };

    const selectCategory = (e, val) => {
        setCatActive((prev)=>{
            return e.target.value;
        })
        setCategory(val.id)
    };

    const addExcept = (val) => {
        if(exceptIgList.includes(val.igId) === true){
            alert(`${val.igName} 은(는) 이미 제외 재료 목록에 포함되어 있습니다.`)
        } else{
            setExceptIgList([...exceptIgList, val.igId])
            setExceptIgName([...exceptIgName, val.igName])
        }
    }

    const setExceptInit = () => {
        setExceptIgList([])
        setExceptIgName([])
    }

    return (
        <div>
            <div className="tot">
            <h2 id="recipe_search_title">레시피 검색</h2>
            <div className="IngredientSearchBar">
                <input className="SearchInput" type="text" placeholder="레시피명을 검색해주세요." value={searchTxt} onChange={(e) => {
                    setSearchTxt(e.target.value);
                }} /><br/><br/>
                {searchTxt
                    ? <p>🔍 {searchTxt}</p>
                    : null}
            </div>

            <div className="category">
            {categoryList.map((val)=>{
                return(
                        <button value={val.id}
                        className={"btn"+(val.id == catActive?"_active": "")}
                        onClick={(e)=>{selectCategory(e, val)}}>
                            {val.name}
                        </button>
                )
            })}
            </div>
            
            <div className="SelectBox">
            <h3 className="SelectBox__ig">제외 재료 선택</h3>
            <p className="SelectBox__ig">📢 알러지 외 추가적으로 제외하고 싶은 재료를 골라주세요.</p>
            <div id="IngredientSearchBar_recipe">
                <input className="SearchInput" type="text" placeholder="재료명을 검색해주세요." value={exceptTxt} onChange={(e) => {
                    setExceptTxt(e.target.value);
                }} />
                <Btn onClick={() => { getSearch() }} context={"검색"} orange={false} />
            </div>
            <div id="Select__igList">
                        🧺제외할 재료 목록🧺
                            {exceptIgName.map((name)=>{
                                return(
                                    <div>{name}</div>
                                )
                            })}
                            </div>
            {/*재료 검색 버튼 클릭 시 보여주는 화면*/}
            <div>
                {isVisible ? (
                    <div className="BackStyle"></div>
                ) : null}
            </div>
            <div>
                {isVisible ? (
                    <div className="Modal">
                        <div id="close" onClick={() => { setIsVisible(false) }}>❌</div>
                        <h4>검색 결과</h4>
                        <table id="SearchTable">
                            {searchList.map((val) => {
                                return (
                                    <tr key={val.igId} className="searchBox">
                                        <td className="name">{val.igName}</td>
                                        <td><TransBtn context={"➕"} orange={false} onClick={() => { addExcept(val); }} /></td>
                                    </tr>
                                );
                            })}
                        </table>
                        <div id="Modal__igList">
                            🧺제외할 재료 목록🧺
                            {exceptIgName.map((name) => {
                                return (
                                    <div>{name}</div>
                                )
                            })}
                        </div>
                        <div id="closeBtn">
                            <Btn onClick={() => { setExceptInit() }} context={"초기화"} orange={false} />
                        </div>
                    </div>) : null}
            </div>
            </div>

            <div className="SelectBox">
            <h3 className="SelectBox__ig">재료 선택</h3>
            <p className="SelectBox__ig">📢 내 창고 속 재료 중 넣고 싶은 재료를 골라주세요.</p>
            <div className="IgSelectBox"> 
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
                    categoryId : category,
                    keyward : searchTxt,
                    ingredientId : selectIgList,
                    exceptIgId : exceptIgList,
                    exceptIgName : exceptIgName
                }}>
                <Btn context={"검색"} orange={false} />  
                </Link>              
            </div>

            </div>
            </div>
        </div>
    )
}

export default RecipeSearchPage;