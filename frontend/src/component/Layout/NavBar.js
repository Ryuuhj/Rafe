//상단 로고 + 네비게이션 바
import {
    Link,
} from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import "./css/NavBar.css";
import "./Text.css";
import Btn from "../Button/Btn.js"


// const Logout = ({ onClick }) => {
//     localStorage.clear()
//     window.location.replace('/login');
// }


function NavBar() {
    const navigate = useNavigate();
    const userId = localStorage.getItem('userId')

    return (
        <div>
            <div className="btn-wrapper">
                {userId
                ? <Btn onClick={() => {
                    localStorage.clear()
                    navigate("../", { replace: true })
                }} context={"Logout"} orange={false} />
            
                : <Btn onClick={() => {
                    navigate("../login", { replace: true })
                }} context={"LogIn"} orange={false} />}
            </div>
            <div className="nav-wrapper">
                <br />
                <img src="../img/rafe_logo.png" alt="Rafe" width={"5%"} />
                <div id="wrapper">
                    <nav id="nav">
                        <ul id="menu__list">
                            <Link to="/main" id="menu__menu">Home</Link>
                            <li id="menu__menu">
                                <Link to={`/recipe`} state={{ categoryId: 10 }}>
                                    Recipe
                                </Link>
                                <div id="subwrapper">
                                    <nav id="subnav">
                                        <ul id="submenu__list">
                                            <Link to={`/recipe`} state={{ categoryId: 0 }} id="submenu__menu">Coffee</Link>
                                            <Link to={`/recipe`} state={{ categoryId: 1 }} id="submenu__menu">Latte</Link>
                                            <Link to={`/recipe`} state={{ categoryId: 2 }} id="submenu__menu">Smoothie</Link>
                                            <Link to={`/recipe`} state={{ categoryId: 3 }} id="submenu__menu">Juice</Link>
                                            <Link to={`/recipe`} state={{ categoryId: 4 }} id="submenu__menu">Ade</Link>
                                            <Link to={`/recipe`} state={{ categoryId: 5 }} id="submenu__menu">Cocktail</Link>
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
                                            <Link to="/bean" id="submenu__menu">원두 일기장</Link>
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