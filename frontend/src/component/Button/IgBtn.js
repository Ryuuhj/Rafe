import React, { useEffect, useState } from "react";

function IgBtn( init, context ){
    const [isClicked, setIsClicked] = useState(false);
    if (init){
        setIsClicked(false)}
    else{
        setIsClicked(true)}

    return(
        <button
        style={{
            backgroundColor : isClicked? '#FF8243' : '#0D3F3D',
            color : isClicked ? '#0D3F3D' : '#FF8243',
            padding : "10px 20px",
            border : 0,
            borderRadius : 10,
            
        }}
        onClick={setIsClicked(true)}>
            {context}
        </button>
    )
}

export default IgBtn;