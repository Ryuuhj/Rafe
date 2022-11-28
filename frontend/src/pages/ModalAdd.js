// 내 창고 페이지에서 재료 추가하는 모달창

import Btn from "../component/Button/Btn";
import axios from "axios";


function ModalAdd(ContentList, setIsVisible) {

    const ingredientAdd = () => {
        // 백엔드로 추가하는 재료 id 넘겨주기
        //axios.post('url')
        

    }
    
    return (
        <div className="Modal-Content">
            <div>
                {{ ContentList }.map(Content => <p>{Content.ingredient_name}</p>)}
            </div>
            <div className="Modal-Button">
                <Btn onClick={ingredientAdd} context={"재료 추가하기"} orange={true} />
            </div>
        </div>
    )
}

export default ModalAdd;


// const ingredientSearch = async () => {
//     await axios.get("https://302f9c07-f1c7-45dd-a83e-eece1d2596dd.mock.pstmn.io/ingredient", {
//         params: {
//             keyward: searchtxt
//         }
//     })
//         .then(res => {
//             console.log(res.data)
//             setIsVisible(true); //modal창 값 true로 변경 -> modal창 보여줌
//             setIngredientList(res.data); //받아온 재료명+재료id를 IngredientList에 저장
//             console.log("ingredient: ", ingredientList, "visible", isVisible);
//         })
//         .then(() => {
//             setSearchtxt("")
//         })
// }