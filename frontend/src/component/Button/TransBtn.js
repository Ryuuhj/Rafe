//button component

function TransBtn({ onClick, context}) {
    return (
        <button
        onClick={onClick} 
        style={{
            color : '#ffffff',
            padding : "10px 20px",
            border : 0,
            borderRadius : 10,
            
        }}>
            {context}
        </button>
    );
}

export default TransBtn;