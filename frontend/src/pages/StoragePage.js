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

    useEffect(() => {
        axios.get(`/storage/${userId}`)
        .then(res => {
            getStorage(res);
        })
      }, []);

    const getSearch = async(res) => {  //재료 검색 시 키워드 백엔드로 전송 후 검색결과 반환
        setIsVisible(true)
        const result = await axios.get("/ingredient", {
            params: {
                userId : userId,
                keyward: searchtxt
            }
        })
        console.log(result.data)
        setSearchList(result.data)
    }
    

    const getStorage = async(res) => {  //사용자가 갖고 있는 재료 보여주기
        console.log(res.data)
        setStorageList(res.data)
        //setFast(result.data.fast)
    }

    const submitDel = async(val) => {  //재료 삭제 (삭제하려는 재료 id 보내주기)
        console.log('delete ingredient! id:', val.id)
        console.log('val.check: ', val.check)
        // axios.post('url', { id : val.id})
        // .then(res => {getStorage(res)})
    }

    const checkFast = (val) => {  // 빨리 소진 여부 변경 시 백엔드에 넘겨주기
        console.log('check fast id:', val.igId)
        // axios.post('url', {
        //     id: val.id
        //   })
        //   .then(res => {
        //     getStorage(res)
        //   }) 
    }

    const submitAdd = (val) => {
        console.log('재료 추가')
        alert(val.igName + " (이)가 추가되었습니다😊")
        // axios.post('url', {
        //     id : val.id
        // })
        // .then(res => {
        //     getStorage(res)
        //     alert(val.ingredient_name + " (이)가 추가되었습니다😊");
        // })
    }

    return (
        <div>
            <div id="StorageHeader">
                <pre>           🥗 내 창고 🥗</pre>
            </div>
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
                                    <Link to={`/ingredient/detail`} state={{igId : val.igId}}>
                                    {val.igName}
                                    </Link>
                                    </td>
                                <td></td>
                                <td><input className="checkbox" type={"checkbox"} checked={val.fastUse} onChange={()=> {checkFast(val);}}/>
                                <TransBtn onClick={()=>{submitDel(val);}} context={"❌"}/></td>
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
                                <td><TransBtn context={"➕"} orange={false} onClick={()=>{submitAdd(val);}} /></td>
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