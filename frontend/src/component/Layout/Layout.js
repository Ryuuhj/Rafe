//상단 로고 + 네비게이션 바
import {
    Outlet,
} from "react-router-dom";
import "./Text.css";
import "./css/Layout.css"
import NavBar from "./NavBar";
import Btn from "../Button/Btn.js"
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';


function Layout () {
    const [userId, setUserId] = useState(localStorage.getItem("userId"));
    const navigate = useNavigate();

    useEffect(() => {
        const handleUserIdChange = () => {
          if (!localStorage.getItem("userId")) {
            setUserId(null);
          }
        };
        window.addEventListener("storage", handleUserIdChange);
        return () => {
          window.removeEventListener("storage", handleUserIdChange);
        };
      }, []);
      
      const handleLogout = () => {
        localStorage.removeItem("userId");
        setUserId(null);
        navigate("../", { replace: true })
      };

    return (
        <div className="wrapper">
            <div className="nav-wrapper">
                <NavBar></NavBar>
                <div className="btn-wrapper">
                {userId
                ? <Btn onClick={handleLogout} context={"Logout"} orange={false} />
            
                : <Btn onClick={() => {
                    navigate("../login", { replace: true })
                }} context={"LogIn"} orange={false} />}
            </div>
            </div>
            {userId
            ? <Outlet />
            : <div id="rafe_login_box">
                <img id="rafe_login_img" src="../../img/Rafe_login.png" alt="이미지를 찾을 수 없습니다."></img>
                <p>로그인 후 Rafe를 이용해주세요.</p>
                </div>
            }
        </div>
    );
}

export default Layout;