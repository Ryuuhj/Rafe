import "./Text.css";
import "./css/RecipePageLayout.css";
//import Pagination from "./Pagination";

import './css/Pagination.css';
import Pagination from "react-js-pagination";

import { useState } from "react";
import { Link } from "react-router-dom";

function RecipePageLayout({ recipe, length }) {
    const [limit, setLimit] = useState(12);
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;

    const handlePageChange = (page) => {
        setPage(page);
      };

    return (
        <div className="recipeBox">
            {recipe.slice(offset, offset + limit).map(({ recipeId, recipeTitle, recipeImg }) => (
                <div key={recipeId} className="recipeBox__box">
                    <Link to={`/recipe/detail`} state={{ recipeId: recipeId }} className="recipeBox__link">
                        <span><img className="recipeBox__image" src={recipeImg} alt="이미지를 찾을 수 없습니다." /></span>
                        {recipeTitle.length > 7
                            ? <span className="recipeBox__name">{recipeTitle.slice(0, 7)}..</span>
                            : <span className="recipeBox__name">{recipeTitle}</span>}
                    </Link>
                </div>
            ))}
            <footer className="footer">
                {/* <Pagination
                    total={length}
                    limit={limit}
                    page={page}
                    setPage={setPage}
                />
                 */}
                 <Pagination
      activePage={page} // 현재 페이지
      itemsCountPerPage={12} // 한 페이지랑 보여줄 아이템 갯수
      totalItemsCount={length} // 총 아이템 갯수
      pageRangeDisplayed={5} // paginator의 페이지 범위
      prevPageText={"‹"} // "이전"을 나타낼 텍스트
      nextPageText={"›"} // "다음"을 나타낼 텍스트
      onChange={handlePageChange} // 페이지 변경을 핸들링하는 함수
      
    />
            </footer>
        </div>
    );
}

export default RecipePageLayout;