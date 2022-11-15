import React, { useCallBack, useEffect } from "react";
import GoogleLogin from 'react-google-login';
import { gapi } from 'gapi-script';
import axios from 'axios';
import "./GoogleButton.css";

//clientId는 env 파일에 저장하기
const clientId ="389808376803-g1vrt87k274ne8guf37tocd57tilgdqa.apps.googleusercontent.com";
/*const clientId = "888117857456-asrkckqsa1s2g5plhoe2u43pi4sa4pqf.apps.googleusercontent.com";*/

const GoogleButton = ({onSocial}) => {
    useEffect(() => {
        function start() {
            gapi.client.init({
                clientId,
                scope: 'email',
            });
        }
        gapi.load('client:auth2', start);
    }, []);

    const onSuccess = async(response) => {
    	console.log(response);
        const result = response.profileObj
        localStorage.setItem('login-token', response.accessToken)
        localStorage.setItem('profile', response.profileObj)
    
        
        //mock server로 진행 (post url 변경)
        axios.post('https://302f9c07-f1c7-45dd-a83e-eece1d2596dd.mock.pstmn.io/', {
            name : result.name,
            email : result.email,
            picture : result.imageUrl,
            google : result.googleId //googleId 값 보내고 서버로부터 userid 받아서 localStorage에 저장
         })
         .then(res => {
            console.log(res.data[0].userid)
            window.location.replace('/main');
            localStorage.setItem('userid', res.data[0].userid)
         })
        // .then( res => {
        //     if(localStorage.getItem('login-token')){
        //         console.log(localStorage.getItem('login-token'))
        //         window.location.replace="/main"; //뒤로 돌아가기 불가능
        //         console.log(localStorage.getItem('profile'))
        //     }else{
        //         console.log('failure : token undefined')
        //     }
        // });
    }

    const onFailure = ( response ) => {
        console.log(response);
    };

    return (
        <div className="wrapper">
        <div>
            <div className="img-wrapper">
            <img src="../img/rafe_logo_txt.png" alt="Rafe" width={"40%"}/>
            </div>
            <GoogleLogin
            client = {clientId}
            buttonText = "Google Login"
            onSuccess = {onSuccess}
            onFailure = {onFailure}
            />
        </div>
        </div>
    );
};

export default GoogleButton;