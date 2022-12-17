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

    useEffect(() => {
        axios.all(
            [axios.get("http://localhost:8080/main/like"), 
            axios.get(`http://localhost:8080/main/fastuse/${userId}`)])
            .then(
              axios.spread((res1, res2) => {
                  setLikeList(res1.data);
                  setFastList(res2.data);
              })
            )
          .catch((err) => console.log(err));
      }, []);

    return (
        <div className="main_box">
            <h2>Rafe에 오신 걸 환영합니다!</h2>
        <div className="main_box_box">
            <div className="main_title_box">
            <p className="main_title">인기 많은 레시피</p>
            <p className="main_link"><Link to={`/recipe/popular`} state={{ categoryId: 10 }}>&gt;&gt; 더보기</Link></p>
            </div>
        {likeList && <ImageSlider data={likeList} />}
        </div>

        <div className="main_box_box">
        <div className="main_title_box">
            <p className="main_title">빨리 도전해보세요!</p>
            <p className="main_link"><Link to={`/recipe`} state={{ categoryId: 10 }}>&gt;&gt; 더보기</Link></p>
            </div>
        {fastList && <ImageSlider data={fastList} />}
        </div>
        </div>
    )
}

export default MainPage;