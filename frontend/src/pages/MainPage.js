import "../component/Layout/Text.css";
import "./css/MainPage.css";
import ImageSlider from "../component/Layout/ImageSlider";

import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import Btn from "../component/Button/Btn";
import { useNavigate } from "react-router-dom";

function MainPage() {
    const navigate = useNavigate();
    const [likeList, setLikeList] = useState([]);
    const [fastList, setFastList] = useState([]);
    const userId = localStorage.getItem('userId');

    useEffect(() => {
        axios.all(
            [axios.get("http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/main/like"),
            axios.get(`http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/main/fastuse/${userId}`)])
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
                    <p className="main_link"><Link to={`/recipe/popular`}>&gt;&gt; 더보기</Link></p>
                </div>
                {likeList && <ImageSlider data={likeList} />}
            </div>

            <div className="main_box_box">
                <div className="main_title_box">
                    <p className="main_title">빨리 도전해보세요!</p>
                    {fastList.length !== 0 &&
                        <p className="main_link"><Link to={`/recipe`} state={{ categoryId: 10 }}>&gt;&gt; 더보기</Link></p>
                    }
                </div>
                {fastList.length !== 0 && <ImageSlider data={fastList} />}
                {fastList.length === 0 &&
                    <div className="main_no_fast">
                        <p className="main_no_fast_text">빨리 소진하기 원하는 재료를 선택해보세요!</p>
                        <Btn context={'재료 선택하러 가기'} orange={false} onClick={() => {
                            navigate('/storage')
                        }} />
                    </div>}
            </div>
        </div>
    )
}

export default MainPage;