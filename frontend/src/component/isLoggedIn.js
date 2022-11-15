import React from 'react';

function isLoggedIn(googleId){
    if (googleId){
        return true
    }else{
        return false
    }
}

export default isLoggedIn;