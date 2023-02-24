import React from 'react';

function isLoggedIn(){
    if (localStorage.getItem('userid')){
        return true
    }else{
        return false
    }
}

export default isLoggedIn;