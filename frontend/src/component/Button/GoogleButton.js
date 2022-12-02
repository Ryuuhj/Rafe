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
        axios.post('/login/google', {
            name : result.name,
            email : result.email,
            picture : result.imageUrl
         })
         .then(res => {
            console.log(res.data[0].userId)
            window.location.replace('/');
            localStorage.setItem('userId', res.data[0].userId)  //백엔드로부터 받은 userid 저장
         })
        // .then( res => {
        //     if(localStorage.getItem('login-token')){
        //         console.log(localStorage.getItem('login-token'))
        //         window.location.replace="/";
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
        <div className="btnwrapper">
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