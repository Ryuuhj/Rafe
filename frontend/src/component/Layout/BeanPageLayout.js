import "./Text.css";
import "./css/BeanPageLayout.css"
//import Pagination from "./Pagination";
import './css/Pagination.css';
import Pagination from "react-js-pagination";

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";
import { ImStarFull } from "react-icons/im";

function BeanPageLayout({ star }) {
    const navigate = useNavigate();
    const userId = localStorage.getItem('userId')
    const [beanList, setBeanList] = useState([]);
    const [limit, setLimit] = useState(4);
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;

    const handlePageChange = (page) => {
        setPage(page);
      };
    
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
        axios.get(`http://ec2-52-79-217-14.ap-northeast-2.compute.amazonaws.com:8080/bean/${userId}/${star}`)
            .then(res => {
                getBean(res);
            })
    }, [star]);

    // useEffect(() => {
    //     if (star === 0) {
    //         axios.get('https://fd518520-055a-436e-a971-8a98dcc065fe.mock.pstmn.io/bean/0')
    //             .then(res => {
    //                 getBean(res);
    //             })
    //     }
    //     if (star === 1) {
    //         axios.get('https://fd518520-055a-436e-a971-8a98dcc065fe.mock.pstmn.io/bean/1')
    //             .then(res => {
    //                 getBean(res);
    //             })
    //     }
    // }, [star]);

    function exIdtoString(val) {
        if (val === 0) {
            return "핸드 드립"
        } if (val === 1) {
            return "모카 포트"
        } else {
            return "에스프레소 머신"
        }
    }

    return (
        <div>
            <div className="bean_tot">
                {beanList.slice(offset, offset + limit).map((val) => {
                    return (
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
            <footer className="bean_footer">
                {beanList && 
                <Pagination
                activePage={page} // 현재 페이지
                itemsCountPerPage={4} // 한 페이지랑 보여줄 아이템 갯수
                totalItemsCount={beanList.length} // 총 아이템 갯수
                pageRangeDisplayed={5} // paginator의 페이지 범위
                prevPageText={"‹"} // "이전"을 나타낼 텍스트
                nextPageText={"›"} // "다음"을 나타낼 텍스트
                onChange={handlePageChange} // 페이지 변경을 핸들링하는 함수
                
              />}
            </footer>
        </div>
    )
}

export default BeanPageLayout;