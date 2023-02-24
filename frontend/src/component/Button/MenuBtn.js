//menu button component (ex. mypage)

function Btn({ onClick, context, orange}) {
    return (
        <button
        onClick={onClick} 
        style={{
            backgroundColor : orange ? '#FF8243' : '#0D3F3D',
            color : orange ? '#0D3F3D' : '#FF8243',
            width : '300px',
            height : '60px',
            border : 0,
            borderRadius : 10,
            
        }}>
            {context}
        </button>
    );
}

export default Btn;