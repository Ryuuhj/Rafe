import "../component/Layout/Text.css";
import "./css/PageLayout.css";
import Pagination from "./Pagination";

import { useState } from "react";
import { Link } from "react-router-dom";

function PageLayout({ recipe }) {
    const [limit, setLimit] = useState(12);
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;

    return (
        <div className="recipeBox">
            {recipe.slice(offset, offset + limit).map(({ recipeId, recipeName, recipeImage, matchRate }) => (
                <div key={recipeId} className="recipeBox__box">
                    <Link to={`/recipe/detail`} state={{ recipeId: recipeId }} className="recipeBox__link">
                        <span><img className="recipeBox__image" src={recipeImage} alt="이미지를 찾을 수 없습니다." /></span>
                        {recipeName.length > 7
                            ? <span className="recipeBox__name">{recipeName.slice(0, 7)}..</span>
                            : <span className="recipeBox__name">{recipeName}</span>}
                    </Link>
                </div>
            ))}
            <footer className="footer">
                <Pagination
                    total={recipe.length}
                    limit={limit}
                    page={page}
                    setPage={setPage}
                />
            </footer>
        </div>
    );
}

export default PageLayout;