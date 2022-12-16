import "../component/Layout/Text.css";
import "./css/BeanDetailPage.css";
import { useLocation } from "react-router-dom"
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Btn from "../component/Button/Btn";
import { ImStarFull } from "react-icons/im";

export default function BeanDetailPage() {
    const location = useLocation();
    const navigate = useNavigate();
    const beanId = location.state.beanId
    const [beanDetail, setBeanDetail] = useState([]);
    var [dateString] = [];
    console.log('beanid', beanId)

    useEffect(() => {
        axios.get("http://localhost:3001/data")
            //axios.get(`http://localhost:8080/bean/detail/${beanId}`)
            .then(res => {
                setBeanDetail(res.data);
                console.log(res.data)
            })
    }, [])

    function exIdtoString(val) {
        if (val === 0) {
            return "핸드 드립"
        } if (val === 1) {
            return "모카 포트"
        } else {
            return "에스프레소 머신"
        }
    }

    const submitEdit = (val) => {
        navigate('/bean/edit', {state:{beanId : beanId, exId : val.exId}})
    }

    //삭제 후 미리보기 페이지로 이동
    const submitDelete = () => {
        axios.delete(`http://localhost:8080/bean/delete/${beanId}`)
        .then(()=> {
            navigate("/bean");
        })
    }

    return (
        <div>
            {beanDetail.map((val) => {
                return (
                    <div>
                        {dateString = val.pickDate.split('-')}
                        <div className="bean_detail_title">
                            {dateString[0]}년 {dateString[1]}월 {dateString[2]}일의 원두 일기
                            </div>
                        <div className="bean_detail_box">
                            {val.exId === 0 && <img className="been_ex_img" src="../../img/hand_drip.png" alt="핸드드립"/>}
                            {val.exId === 1 && <img className="been_ex_img" src="../../img/mokaport.png" alt="모카포트"/>}
                            {val.exId === 2 && <img className="been_ex_img" src="../../img/exmachine.png" alt="에스프레소 머신"/>}
                            <div className="bean_pre_star"><ImStarFull className={'black'} size="30" />
                            <p className="bean_pre_star_text">{val.star}.0</p>
                            </div>
                            <div className="bean_detail_comment">{val.comment}</div>
                            <div className="bean_detail_text">원두 종류 : {exIdtoString(val.exId)}</div>
                            <div className="bean_detail_text">로스팅 : {val.loasting}</div>
                            <div className="bean_detail_text">원두량 : {val.exAmount}g</div>
                            <div className="bean_detail_text">분쇄도 : {val.exG}</div>
                            {val.wTemp && <div className="bean_detail_text">물 온도 : {val.wTemp}℃</div>}
                            {val.wAmount && <div className="bean_detail_text">주유량(물 양) : {val.wAmount}ml</div>}
                            <div className="bean_detail_text">총 주유 시간 : {val.exMin}분 {val.exSec}초</div>
                            {val.filter && <div className="bean_detail_text">필터 종류 : {val.filter}</div>}

                            <div className="bean_detail_btn">
                                <Btn context={"수정하기"} orange={false} onClick={()=>{ submitEdit(val) }} />
                                &nbsp;&nbsp;
                                <Btn context={"삭제하기"} orange={false} onClick={()=>{ submitDelete() }} />
                            </div>
                        </div>
                    </div>
                )
            })}
        </div>
    )
}