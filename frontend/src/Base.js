//상단 로고 + 네비게이션 바
import {
    BrowserRouter,
    Routes,
    Route,
    Link,
} from "react-router-dom";
import "./Base.css";
import "./Text.css";
import Btn from "./component/Btn.js"

const Logout = ({onClick}) => {
    localStorage.removeItem('userid')
    window.location.replace('/');
}

function Base() {
    return (
        <div>
            <div className="btn-wrapper">
            <Btn onClick={Logout} context={"Logout"} orange={false}/>
            </div>
        <div className="nav-wrapper">
        <br />
            <img src="../img/rafe_logo.png" alt="Rafe" width={"5%"} />
            <div id="wrapper">
                <nav id="nav">
                    <ul id="menu__list">
                    <Link to="/main" id="menu__menu">Home</Link>
                        <li id="menu__menu">
                            Recipe
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
                            My Page
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
        <p style={{height:'1000px'}}></p>
        </div>
    );
}

export default Base;