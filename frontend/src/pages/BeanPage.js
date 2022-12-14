import "../component/Layout/Text.css";
import "./css/BeanPage.css"
import Btn from "../component/Button/Btn";
import TransBtn from "../component/Button/TransBtn";

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function BeanPage() {
    const navigate = useNavigate();
    return (
        <div>
            <div className="bean_pre_header">
                <p className="bean_pre_header_title">나만의 원두 일기장</p>
                <TransBtn context={"✏️"} onClick={(()=>{navigate('/bean_diary/create')})} />
            </div>

            <div className="bean_tot">
                <p>원두 일기 미리보기 나올 화면</p>
            </div>
        </div>
    )
}

export default BeanPage;