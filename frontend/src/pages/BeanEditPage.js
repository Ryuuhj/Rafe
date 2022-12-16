import "../component/Layout/Text.css";
import "./css/BeanEditPage.css";
import HandDripLayout from "../component/Layout/HandDripLayout";
import MokaPortLayout from "../component/Layout/MokaPortLayout";
import ExMachineLayout from "../component/Layout/ExMachineLayout";

import axios from "axios";
import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';

export default function BeanEditPage(){
    const location = useLocation();
    const beanId = location.state.beanId
    const exId = location.state.exId
    const [beanEdit, setBeanEdit] = useState([]);
    
    useEffect(() => {
        axios.get("http://localhost:3001/data")
            //axios.get(`http://localhost:8080/bean/detail/${beanId}`)
            .then(res => {
                setBeanEdit(res.data);
                //console.log(res.data)
            })
    }, [])

    return(
        <div className="bean_create_tot">
            <div className="bean_header">
            <p>☕오늘의 원두 일기 수정하기</p>
            </div>

            <div className="bean_ex">
                {exId === 0 && <p>추출 방식 : 핸드 드립</p>}
                {exId === 1 && <p>추출 방식 : 모카 포트</p>}
                {exId === 2 && <p>추출 방식 : 에스프레소 머신</p>}
                <div className="been_edit_ex">
                {exId === 0 && <img className="been_edit_img" src="../../img/hand_drip.png" alt="핸드드립"/>}
                {exId === 1 &&<img className="been_edit_img" src="../../img/mokaport.png" alt="모카포트"/>}
                {exId === 2 &&<img className="been_edit_img" src="../../img/exmachine.png" alt="에스프레소 머신"/>}
                </div>
            </div>

            <div className="bean_box">
                {exId === 0 && <HandDripLayout />}
                {exId === 1 && <MokaPortLayout editList={beanEdit[0]}/>}
                {exId === 2 && <ExMachineLayout />}
            </div>
        </div>
    )
}