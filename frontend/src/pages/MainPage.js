import "../component/Layout/Text.css";
import "./css/MainPage.css";
import ImageSlider from "../component/Layout/ImageSlider";

import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function MainPage() {
    const [likeList, setLikeList] = useState([]);
    const [fastList, setFastList] = useState([]);
    const userId = localStorage.getItem('userId');

    //인기순 레시피 받아오기
    const getLikeList = (res) =>{
        if (res.data.success === undefined) {
            setLikeList(res.data.slice(0,4));
        }
        else {
            alert(res.data.message);
        }
    }

    useEffect(() => {
        axios.get("http://localhost:8080/main/like")
        //axios.get("http://localhost:3001/data")
        .then(res => {
                getLikeList(res);
            })
    }, []);

    //빨리 소진해야 하는 재료 레시피 받아오기
    const getFastList = (res) =>{
        if (res.data.success === undefined) {
            setFastList(res.data.slice(0,4));
        }
        else {
            alert(res.data.message);
        }
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/main/fastuse/${userId}`)
        //axios.get("http://localhost:3001/data1")
        .then(res => {
                getFastList(res);
            })
    }, []);

    return (
        <div className="main_box">
            <h2>Rafe에 오신 걸 환영합니다!</h2>
        <div className="main_box_box">
            <div className="main_title_box">
            <p className="main_title">인기 많은 레시피</p>
            <p className="main_link"><Link to={`/recipe/popular`} state={{ categoryId: 10 }}>&gt;&gt; 더보기</Link></p>
            </div>
        <ImageSlider data={likeList} />
        </div>

        <div className="main_box_box">
        <div className="main_title_box">
            <p className="main_title">빨리 도전해보세요!</p>
            <p className="main_link"><Link to={`/recipe`} state={{ categoryId: 10 }}>&gt;&gt; 더보기</Link></p>
            </div>
        <ImageSlider data={fastList} />
        </div>
        </div>
    )
}

export default MainPage;