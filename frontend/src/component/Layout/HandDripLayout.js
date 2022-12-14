import "./Text.css";
import "./css/HandDripLayout.css";
import Btn from "../Button/Btn";

import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/esm/locale";
import { ImStarFull } from "react-icons/im";

export default function HandDripLayout() {
    const [clicked, setClicked] = useState([false, false, false, false, false]);
    const array = [0, 1, 2, 3, 4]

    const navigate = useNavigate();
    const exId = 0;
    const loasting_type = ["최약배전", "약배전", "중약배전", "중배전", "강중배전", "약강배전", "강배전", "최강배전"]
    const exG_type = ["very fine", "fine", "medium", "coarse"]
    const [btnActive, setBtnActive] = useState("");
    const [GbtnActive, setGBtnActive] = useState("");
    const [bean, setBean] = useState("")
    const [loasting, setLoasting] = useState("")
    const [exAmount, setExAmount] = useState(0)
    const [exG, setExG] = useState("")
    const [wTemp, setWTemp] = useState(0)
    const [wAmount, setWAmount] = useState(0)
    const [exMin, setExMin] = useState("")
    const [exSec, setExSec] = useState("")
    const [filter, setFilter] = useState("")
    const [comment, setComment] = useState("")
    const [pickDate, setPickDate] = useState(new Date())
    const [pickDateString, setPickDateString] = useState("")

    const selectLoasting = (e, val) => {
        setBtnActive((prev) => {
            return e.target.value;
        })
        setLoasting(val);
        console.log(val)
    };

    const selectGrind = (e, val) => {
        setGBtnActive((prev) => {
            return e.target.value;
        })
        setExG(val);
        console.log(val)
    };
    const dateToString = (date) => { //날짜 변환
        return date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0')
    }
    useEffect(() => {
        setPickDateString(dateToString(pickDate))
    }, [pickDate])

    const handleStarClick = index => {
        let clickStates = [...clicked];
        for (let i = 0; i < 5; i++) {
            clickStates[i] = i <= index ? true : false;
        }
        setClicked(clickStates);
    };
    let score = clicked.filter(Boolean).length;

    const submit = () => {
        axios.post("http://localhost:8080/bean/create", {
            userId: localStorage.getItem('userId'),
            exId: exId,
            pickDate: pickDateString,
            bean: bean,
            loasting: loasting,
            exAmount: exAmount,
            exG: exG,
            wTemp: wTemp,
            wAmount: wAmount,
            exMin: exMin,
            exSec: exSec,
            filter: filter,
            comment: comment,
            star : score
        }).then(() => {
            navigate('/bean_diary')
        })
    }
    
    return (
        <div>
            <div className="bean">
                <p className="bean_title">🍵 핸드 드립</p>
                <p className="bean_title">날짜 선택</p>
                <div className="bean_date"><DatePicker
                    selected={pickDate}
                    onChange={(date) => setPickDate(date)}
                    locale={ko}                   // 한글로 변경
                    dateFormat="yyyy.MM.dd (eee)" // 시간 포맷 변경
                    showPopperArrow={false}       // 화살표 변경
                //minDate={new Date()}          // 오늘 날짜 전은 선택 못하게

                />
                </div>
            </div>
            <div className="bean">
                <p className="bean_title">원두 종류</p>
                <input className="beanInput" type="text" placeholder="원두명을 입력해주세요." value={bean} onChange={(e) => {
                    setBean(e.target.value);
                }} />
            </div>
            <div className="bean">
                <p className="bean_title">로스팅</p>
                <div className="loasting_box">
                    {loasting_type.map((val, idx) => {
                        return (
                            <div className="loasting_box_btn">
                                <button value={idx}
                                    className={"btn" + (idx == btnActive ? "_active" : "")}
                                    onClick={(e) => { selectLoasting(e, val) }}>
                                    {val}
                                </button>
                            </div>
                        )
                    })}
                </div>
            </div>

            <div className="bean">
                <p className="bean_title">원두량</p>
                <input className="beanAmountInput" type="number" placeholder="0" value={exAmount} onChange={(e) => {
                    setExAmount(e.target.value);
                }} />g
            </div>

            <div className="bean">
                <p className="bean_title">분쇄도</p>
                <div className="grind_box">
                    {exG_type.map((val, idx) => {
                        return (
                            <div className="grind_box_btn">
                                <button value={idx}
                                    className={"grindbtn" + (idx == GbtnActive ? "_active" : "")}
                                    onClick={(e) => { selectGrind(e, val) }}>
                                    {val}
                                </button>
                            </div>
                        )
                    })}
                </div>
                <p className="bean_g">Very Fine : 0.3mm</p>
                <p className="bean_g">Fine : 0.5 ~ 0.7mm</p>
                <p className="bean_g">Medium : 0.7 ~ 1.0mm</p>
                <p className="bean_g">Coarse : 0.1mm 이상</p>
            </div>
            <div className="bean">
                <p className="bean_title">물 온도</p>
                <input className="beanAmountInput" type="number" placeholder="0" value={wTemp} onChange={(e) => {
                    setWTemp(e.target.value);
                }} />℃
            </div>
            <div className="bean">
                <p className="bean_title">주유량(물 양)</p>
                <input className="beanAmountInput" type="number" placeholder="0" value={wAmount} onChange={(e) => {
                    setWAmount(e.target.value);
                }} />ml
            </div>
            <div className="bean">
                <p className="bean_title">총 주유 시간(추출 시간)</p>
                <input className="beanAmountInput" type="number" placeholder="0" value={exMin} onChange={(e) => {
                    setExMin(e.target.value);
                }} />분 &nbsp;&nbsp;
                <input className="beanAmountInput" type="number" placeholder="0" value={exSec} onChange={(e) => {
                    setExSec(e.target.value);
                }} />초
            </div>
            <div className="bean">
                <p className="bean_title">필터 종류</p>
                <input className="beanInput" type="text" placeholder="필터 종류를 입력해주세요." value={filter} onChange={(e) => {
                    setFilter(e.target.value);
                }} />
            </div>
            <div className="bean">
                <p className="bean_title">🌟 맛 한줄평</p>
                <div className="bean_star">
                    {array.map((el) => (
                        <ImStarFull
                            key={el}
                            onClick={() => handleStarClick(el)}
                            className={clicked[el] && 'black'}
                            size="35"
                        />))}
                </div>
                <textarea className="bean_text" type="text" placeholder="커피 맛 한줄평을 작성해주세요!" value={comment} onChange={(e) => {
                    setComment(e.target.value);
                }} />

            </div>

            <div className="bean_sumbit">
                <Btn context={"작성하기"} orange={false} onClick={() => { submit() }} />
            </div>
        </div>
    )

}