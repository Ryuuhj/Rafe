//상단 로고 + 네비게이션 바
import {
    Outlet,
} from "react-router-dom";
import "./Text.css";
import NavBar from "./NavBar";

function Layout () {
    return (
        <div className="wrapper">
            <div className="nav-wrapper">
                <NavBar></NavBar>
            </div>
                <Outlet />
        </div>
    );
}

export default Layout;