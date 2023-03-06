import "./css/Pagination.css";
import { useState } from "react";

function Pagination({ total, limit, page, setPage }) {
    const numPages = Math.ceil(total / limit);
    const [currPage, setCurrPage] = useState(page)
    let firstNum = currPage - (currPage % 5) + 1
    let lastNum = currPage - (currPage % 5) + 5
    //console.log({"currPage is":currPage, "firsNum is" : firstNum, "page is" : page})
    return (
            <div className="Pagination">
                <button className="paginationBtn" onClick={() => {setPage(page - 1); setCurrPage(page-2);}} disabled={page === 1}>
                    &lt;
                </button>
                <button className={page === firstNum ? "page" : 'null'} onClick={() => setPage(firstNum)}
                    aria-current={page === firstNum ? "page" : null}>
                        {firstNum}
                </button>
                
                
                {Array(4).fill().map((_, i) => {
                    
                    if(i <=2){
                        return(
                        <button className={page === firstNum+i + 1 ? "page" : 'null'}
                            key={i + 1}
                            onClick={() => setPage(firstNum + i + 1)}
                            aria-current={page === firstNum+i + 1 ? "page" : null}
                        >
                            {firstNum + i + 1}
                        </button>
                        )
                    }
                else if(i>=3){
                    return(
                        <button
                        className={page === lastNum ? "page": 'null'}
                        key = {i+1}
                        onClick={()=> setPage(lastNum)}
                        aria-current = {page === lastNum ? "page": null}
                        >
                            {lastNum}
                        </button>
                    )
                }
                    })}
                <button className='paginationBtn'
                onClick={() => {setPage(page + 1); setCurrPage(page);}} 
                disabled={page === numPages}>
                    &gt;
                </button>
            </div>
    );
}

export default Pagination;