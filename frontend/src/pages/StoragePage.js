import "../component/Layout/Text.css";
import "./css/StoragePage.css";
import UserStoragePage from "./UserStoragePage";
import AllergyPage from "./AllergyPage";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";


function StoragePage() {
    const [tab, setTab] = useState(0);
    const [stBtn, setStBtn] = useState(1);
    const [alBtn, setAlBtn] = useState(0); 

    const onStorage = () => {
        setTab(0)
        setStBtn(1)
        setAlBtn(0)
    }
    const onAllergy= () => {
        setTab(1)
        setStBtn(0)
        setAlBtn(1)
    }
    return (
        <div className="StorageTot">
            <div className="storage__tab">
                <div className={stBtn? "storage__tab__on" : "storage__tab__off"} onClick={()=> {onStorage()}}>재료 관리</div>
                <div className={alBtn? "storage__tab__on" : "storage__tab__off"} onClick={()=> {onAllergy()}}>알레르기 설정</div>
            
            </div>


            { (tab == 0)
            ? <div><UserStoragePage /></div>
            : <div><AllergyPage /></div>
        }   
        </div>
    )
}

export default StoragePage;