import "../component/Layout/Text.css";
import "./css/BeanCreatePage.css"
import HandDripLayout from "../component/Layout/HandDripLayout";
import MokaPortLayout from "../component/Layout/MokaPortLayout";
import ExMachineLayout from "../component/Layout/ExMachineLayout";

import React, { useState, useEffect } from 'react';
import axios from 'axios';

function BeanCreatePage() {
    const [exId, setExId] = useState(0)
    
    return (
        <div className="bean_create_tot">
            <div className="bean_header">
            <p>☕오늘의 원두를 기록해보세요!</p>
            </div>

            <div className="bean_ex">
                <p>추출 방식을 선택해주세요.</p>
                <div className="been_ex_btn">
                <button onClick={()=>{setExId(0)}}><img className="been_ex_img" src="../../img/hand_drip.png" alt="핸드드립"/></button>
                <button onClick={()=>{setExId(1)}}><img className="been_ex_img" src="../../img/mokaport.png" alt="모카포트"/></button>
                <button onClick={()=>{setExId(2)}}><img className="been_ex_img" src="../../img/exmachine.png" alt="에스프레소 머신"/></button>
                </div>
            </div>

            <div className="bean_box">
                {exId === 0 && <HandDripLayout />}
                {exId === 1 && <MokaPortLayout />}
                {exId === 2 && <ExMachineLayout />}
            </div>
        </div>
    )
}

export default BeanCreatePage;