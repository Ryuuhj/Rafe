import "./Text.css";
import "./css/RecipePageLayout.css";
import Pagination from "./Pagination";

import { useState } from "react";
import { Link } from "react-router-dom";

function RecipePageLayout({ recipe, length }) {
    const [limit, setLimit] = useState(12);
    const [page, setPage] = useState(1);
    const offset = (page - 1) * limit;

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
                <Pagination
                    total={length}
                    limit={limit}
                    page={page}
                    setPage={setPage}
                />
            </footer>
        </div>
    );
}

export default RecipePageLayout;