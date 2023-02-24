//상단 로고 + 네비게이션 바
import {
    Outlet,
} from "react-router-dom";
import "./Text.css";
import "./css/Layout.css"
import NavBar from "./NavBar";

function Layout () {
    const userId = localStorage.getItem('userId')
    return (
        <div className="wrapper">
            <div className="nav-wrapper">
                <NavBar></NavBar>
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