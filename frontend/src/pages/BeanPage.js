import "../component/Layout/Text.css";
import "./css/BeanPage.css"
import Btn from "../component/Button/Btn";
import TransBtn from "../component/Button/TransBtn";
import Pagination from "../component/Layout/Pagination";

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";
import { ImStarFull } from "react-icons/im";

function BeanPage() {
    const navigate = useNavigate();
    const userId = localStorage.getItem('userId')
    const [beanList, setBeanList] = useState([]);
    const [limit, setLimit] = useState(4);
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;


    //원두 미리보기 정보 가져오기
    const getBean = (res) => {
        if (res.data.success === undefined) {
            setBeanList(res.data);
        }
        else {
            alert(res.data.message);
        }
    };
    useEffect(() => {
        //axios.get(`http://localhost:8080/bean/${userId}`)
        axios.get('http://localhost:3001/data')
            .then(res => {
                getBean(res);
            })
    }, []);

    function exIdtoString(val){
        if(val === 0){
            return "핸드 드립"
        }if(val === 1){
            return "모카 포트"
        }else{
            return "에스프레소 머신"
        }
    }
    
    return (
        <div>
            <div className="bean_pre_header">
                <p className="bean_pre_header_title">나만의 원두 일기장</p>
                <TransBtn context={"✏️"} onClick={(()=>{navigate('/bean/create')})} />
            </div>

            <div className="bean_tot">
                {beanList.slice(offset, offset + limit).map((val)=>{
                    return(
                        <div className="bean_pre_box">
                            <Link to={`/bean/detail`} state={{ beanId: val.beanId }}>
                            <p className="bean_pre_ex">{exIdtoString(val.exId)}</p>
                            <p className="bean_pre_bean">{val.bean} 원두</p>
                            <div className="bean_pre_star"><ImStarFull className={'black'} size="35" />
                            <p className="bean_pre_star_text">{val.star}.0</p>
                            </div>
                            <p className="bean_pre_date">{val.pickDate} 작성</p>
                            </Link>
                        </div>
                    )
                })}
            </div>
            <footer className="footer">
                {beanList && <Pagination
                    total={beanList.length}
                    limit={limit}
                    page={page}
                    setPage={setPage}
                />}
            </footer>
        </div>
    )
}

export default BeanPage;