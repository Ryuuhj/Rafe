import "../component/Layout/Text.css";
import "./css/BeanPage.css"
import TransBtn from "../component/Button/TransBtn";
import BeanPageLayout from "../component/Layout/BeanPageLayout";

import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from "react-router-dom";

function BeanPage() {
    const navigate = useNavigate();
    const [tab, setTab] = useState(0);
    const [newBtn, setNewBtn] = useState(1);
    const [starBtn, setStarBtn] = useState(0);

    const onNewest = () => {
        setTab(0) //tab 0 == 최신순 (star 0과 동일)
        setNewBtn(1)
        setStarBtn(0)
    }
    const onStar= () => {
        setTab(1) //tab 1 == 별점순 (star 1과 동일)
        setNewBtn(0)
        setStarBtn(1)
    }

    return (
        <div>
            <div className="bean_pre_header">
                <p className="bean_pre_header_title">나만의 원두 일기장</p>
                <TransBtn context={"✏️"} onClick={(()=>{navigate('/bean/create')})} />
            </div>
            <div className="bean_sort_type">
                <div className={newBtn? "bean_sort_on" : "bean_sort_off"} onClick={()=> {onNewest()}}>날짜순</div>
                <div className={starBtn? "bean_sort_on" : "bean_sort_off"} onClick={()=> {onStar()}}>별점순</div>
            </div>

            <BeanPageLayout star={tab}/>
        </div>
    )
}

export default BeanPage;