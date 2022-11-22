//상단 로고 + 네비게이션 바
import {
    Link,
} from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import "./NavBar.css";
import "./Text.css";
import Btn from "../Button/Btn.js"


// const Logout = ({ onClick }) => {
//     localStorage.clear()
//     window.location.replace('/login');
// }


function NavBar() {
    const navigate = useNavigate();

    return (
        <div>
            <div className="btn-wrapper">
                <Btn onClick={()=>{
                    localStorage.clear()
                    navigate("../login",  { replace: true}) //방문기록 남기지 않음
                }} context={"Logout"} orange={false} />
            </div>
            <div className="nav-wrapper">
                <br />
                <img src="../img/rafe_logo.png" alt="Rafe" width={"5%"} />
                <div id="wrapper">
                    <nav id="nav">
                        <ul id="menu__list">
                            <Link to="/main" id="menu__menu">Home</Link>
                            <li id="menu__menu">
                                <Link to="/recipe">
                                    Recipe
                                </Link>
                                <div id="subwrapper">
                                    <nav id="subnav">
                                        <ul id="submenu__list">
                                            <Link to="/coffee" id="submenu__menu">Coffee</Link>
                                            <Link to="/latte" id="submenu__menu">Latte</Link>
                                            <Link to="/smoothie" id="submenu__menu">Smoothie</Link>
                                            <Link to="/juice" id="submenu__menu">Juice</Link>
                                            <Link to="/ade" id="submenu__menu">Ade</Link>
                                            <Link to="/cocktail" id="submenu__menu">Cocktail</Link>
                                        </ul>
                                    </nav>
                                </div>
                            </li>
                            <li id="menu__menu">
                                <Link to="/mypage">
                                My Page
                                </Link>
                                <div id="subwrapper">
                                    <nav id="subnav">
                                        <ul id="submenu__list">
                                            <Link to="/storage" id="submenu__menu">내 창고</Link>
                                            <Link to="/shopping" id="submenu__menu">장바구니</Link>
                                            <Link to="/like_recipe" id="submenu__menu">찜한 레시피</Link>
                                        </ul>
                                    </nav>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            
        </div>
    );
}

export default NavBar;