import "../component/Layout/Text.css";
import "./css/StoragePage.css";
import Btn from "../component/Button/Btn";
import TransBtn from "../component/Button/TransBtn";

import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

function StoragePage() {
    const [searchtxt, setSearchtxt] = useState("");
    const [storageList, setStorageList] = useState([]);
    const [searchList, setSearchList] = useState([]);
    const [isVisible, setIsVisible] = useState(false);
    //const[storageExist, setStorageListExist] = useState(false);
    const userId = localStorage.getItem('userId')

    // 사용자가 가지고 있는 재료 목록 가져오기 + 저장하기
    useEffect(() => {
        axios.get("http://localhost:3001/data")
            //axios.get(`http://localhost:8080/storage/${userId}`)
            .then(res => {
                getStorage(res);
            })
    }, []);

    const getStorage = async (res) => {
        console.log(res.data)
        setStorageList(res.data)
    }

    // 재료 검색 후 검색 결과 저장
    const getSearch = async (res) => {
        setIsVisible(true)
        const searchData = {
            userId: userId,
            keyward: searchtxt
        }

        //const result = await axios.get("http://localhost:8080/ingredient", searchData)
        const result = await axios.get('http://localhost:3001/search_result')
        console.log(result.data)
        setSearchList(result.data)
    }

    // 재료 삭제(response : 사용자 재료 목록)
    const submitDel = async (val) => {  //재료 삭제 (삭제하려는 재료 id 보내주기)
        console.log('delete ingredient! id:', val.igId)
        // axios.delete(`http://localhost:8080/${userId}/${val.igid}`)
        // .then(res => {getStorage(res)})
    }

    // 빨리 소진하고 싶은 재료 선택 (response : 사용자 재료 목록)
    const checkFast = (val) => {  // 빨리 소진 여부 변경 시 백엔드에 넘겨주기
        console.log('check fast id:', val.igId)
        const dataFast = {
            userId: userId,
            igId: val.igId
        }
        // axios.patch('http://localhost:8080/storage/fast', dataFast)
        //     .then(res => {
        //         getStorage(res)
        //     })
    }

    // 재료 추가 (response : 사용자 재료 목록)
    const submitAdd = (val) => {
        const addData = {
            userId: userId,
            igId: val.igId
        }

        if (val.exists == true) {
            alert(`${val.igName} 은(는) 이미 내 창고에 있는 재료입니다.`)
        } else {
            alert(`${val.igName} 추가 완료!`)
            // axios.post('http://localhost:8080/storage/insert', addData)
            //     .then(res => {
            //         getStorage(res)
            //     })
        }
    }

    return (
        <div>
            <h2 className="storage__title">내 창고</h2>
            <div className="IngredientSearchBar">
                <input className="SearchInput" type="text" placeholder="재료명을 검색해주세요." value={searchtxt} onChange={(e) => {
                    setSearchtxt(e.target.value);
                }} />
                <Btn onClick={() => { getSearch() }} context={"검색"} orange={false} />
            </div>

            <table id="StorageTable">
                <thead>
                    <tr>
                        <th className="name">재료명</th>
                        <th></th>
                        <th id="fast">빨리 소진하기</th>
                    </tr>
                </thead>
                <tbody>
                    {storageList.map((val) => {
                        return (
                            <tr key={val.igId}>
                                <td className="name">
                                    <Link to={`/ingredient/detail`} state={{ igId: val.igId }}>
                                        {val.igName}
                                    </Link>
                                </td>
                                <td></td>
                                <td><input className="checkbox" type={"checkbox"} checked={val.fastUse} onChange={() => { checkFast(val); }} />
                                    <TransBtn onClick={() => { submitDel(val); }} context={"❌"} /></td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>

            {/*재료 검색 버튼 클릭 시 보여주는 화면*/}
            <div>
                {isVisible ? (
                    <div className="BackStyle" onClick={() => { setIsVisible(false) }}></div>
                ) : null}
            </div>

            <div>
                {isVisible ? (
                    <div className="Modal">
                        <h4>검색 결과</h4>
                        <table id="SearchTable">
                            {searchList.map((val) => {
                                return (
                                    <tr key={val.igId} className="searchBox">
                                        <td className="name">{val.igName}</td>
                                        <td><TransBtn context={"➕"} orange={false} onClick={() => { submitAdd(val); }} /></td>
                                    </tr>
                                );
                            })}
                        </table>
                        <div id="closeBtn">
                            <Btn onClick={() => { setIsVisible(false) }} context={"닫기"} orange={false} />
                        </div>
                    </div>) : null}
            </div>
        </div>
    )
}

export default StoragePage;