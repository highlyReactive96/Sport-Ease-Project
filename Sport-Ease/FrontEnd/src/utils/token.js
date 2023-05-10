import { decodeToken } from "react-jwt";

const DecodeToken = function() {

    var token = sessionStorage.getItem("Token")
    var user = {
        userName : "",
        role : "",
        isLoggedIn : ""
    }
    if(token)
    {   
        user.userName = decodeToken(token).name
        user.role = decodeToken(token).role
        user.isLoggedIn = sessionStorage.getItem("isLogedIn")
    }
    return user;
}

export default DecodeToken;
