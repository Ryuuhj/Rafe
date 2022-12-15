import "../component/Layout/Text.css";
import "./css/AllergyPage.css";
import "./css/ShoppingPage.css";
import TransBtn from "../component/Button/TransBtn";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

function ShoppingPage() {
    const [cartList, setCartList] = useState([]);
    const [isEmpty, setIsEmpty] = useState(true);
    const userId = localStorage.getItem('userId');

    // 사용자가 장바구니 목록 가져오기 + 저장하기
    useEffect(() => {
        //axios.get("http://localhost:3001/data")
        axios.get(`http://localhost:8080/cart/${userId}`)
            .then(res => {
                getCart(res);
            })
    }, []);

    const getCart = async (res) => {
        if (res.data) {
            setIsEmpty(false)
        } else {
            setIsEmpty(true)
        }

        if (res.data.message == "success") {
            setCartList(res.data.data)
        } else {
            <p>Loading ..</p>;
        }
    }

     //장바구니 목록 삭제 (삭제하려는 재료 id 보내주기)
    const submitDel = async (val) => { 
        const igId = val.igId
        axios.delete(`http://localhost:8080/cart/${userId}/${igId}`)
            .then(res => {
                getCart(res)
            })
    }


    return (
        <div>
            <h2 className="cart__title">장바구니</h2>
                {isEmpty == false
                    ? <table id="StorageTable">
                        <thead>
                            <tr>
                                <th className="name">재료명</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <p id="notice">📢 장바구니 목록</p>
                        </thead>
                        <tbody>
                            {cartList.map((val) => {
                                return (
                                    <tr key={val.igId}>
                                        <td className="name">
                                            <Link to={`/ingredient/detail`} state={{ igId: val.igId }}>
                                                {val.igName}
                                            </Link>
                                        </td>
                                        <td className="select">
                                            <TransBtn onClick={() => { submitDel(val); }} context={"❌"} />
                                        </td>
                                    </tr>
                                );
                            })}
                            <br /><br />
                        </tbody>
                    </table>
                    :
                    <div id="no_storage_box">
                        <img id="no_storage_img" src="../../img/no_storage.png" alt="이미지를 불러올 수 없습니다."></img>
                        <p>장바구니에 넣은 재료가 없습니다.</p>
                    </div>
                }
            </div>
    )
}

export default ShoppingPage;