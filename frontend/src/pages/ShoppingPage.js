import "../component/Layout/Text.css";
import "./css/AllergyPage.css";
import "./css/ShoppingPage.css";
import TransBtn from "../component/Button/TransBtn";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function ShoppingPage() {
    const [cartList, setCartList] = useState([]);
    const [isEmpty, setIsEmpty] = useState(true);
    const userId = localStorage.getItem('userId');

    // 사용자가 장바구니 목록 가져오기 + 저장하기
    useEffect(() => {
        //axios.get("https://fd518520-055a-436e-a971-8a98dcc065fe.mock.pstmn.io/cart")
            axios.get(`http://localhost:8080/cart/${userId}`)
            .then(res => {
                getCart(res);
            })
    }, []);

    const getCart = async (res) => {
        console.log("getStorage res.data:", res.data)
        if(res.data){
            setIsEmpty(false)
            setCartList(res.data.data)
        }else{
            <p>Loading ..</p>;
            setIsEmpty(true)
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
            <table id="CartTable">
                <thead>
                    <tr>
                        <th className="name">재료명</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <p id="notice">&nbsp;📢 장바구니 목록</p>
                </thead>
                <tbody>
                    {cartList.length !== 0 && cartList.map((val) => {
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
            {cartList.length === 0 &&
                <div id="no_storage_box">
                    <img id="no_cart_img" src="../../img/no_storage.png" alt="이미지를 불러올 수 없습니다."></img>
                    <p>장바구니에 넣은 재료가 없습니다.</p>
                </div>
            }

        </div>
    )
}

export default ShoppingPage;