import "../component/Layout/Text.css";
import "./css/MypagePage.css";
import MenuBtn from "../component/Button/MenuBtn";

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function MypagePage() {
    const navigate = useNavigate();
    const [user, setUser] = useState([]);
    const userId = localStorage.getItem('userId')

    useEffect(() => {
        axios.get(`http://localhost:8080/user/${userId}`)
        .then(res => {
            console.log(res.data);
            setUser(res.data);
        })
    }, [])

    return (
        <div>
            <div className="MyPageUserContent">
                <div><img src={ user.userPicture } referrerPolicy="no-referrer" alt="이미지가 없습니다." id="myImg"></img></div>
                <div>{user.userName}</div>
            </div>
            <div className="MyPageMenu">
                <div className="menubtn"><MenuBtn onClick={(()=>{navigate('/storage')})} context={"내 창고"} orange={false} /></div>
                <div className="menubtn"><MenuBtn onClick={(()=>{navigate('/bean_diary')})} context={"원두 일기장"} orange={false} /></div>
                <div className="menubtn"><MenuBtn onClick={(()=>{navigate('/shopping')})} context={"장바구니"} orange={false} /></div>
                <div className="menubtn"><MenuBtn onClick={(()=>{navigate('/like_recipe')})} context={"찜한 레시피"} orange={false} /></div>
            </div>
        </div>
    )
}

export default MypagePage;