import {Route, useHistory } from "react-router-dom";
import Admin from "./admin";
import Login from "./login";
import Owner from "./owner";
import Tenant from "./player";
import Users from "./users";
import DecodeToken from "./utils/token";

function ProtectedRoute(props) {

    
    var history = useHistory()
    var isLoggedIn = DecodeToken().isLoggedIn;
    var Role = DecodeToken().role

    if(isLoggedIn && Role)
    {
        if(Role == "ROLE_ADMIN" && props.path == '/admin')
        {
            return <Route path = {props.path} exact component={Admin}></Route>
        }
        if(Role == "ROLE_ACADEMY" && props.path == '/owner')
        {
            return <Route path = {props.path} exact component={Owner}></Route>
        }
        if(Role == "ROLE_PLAYER" && props.path == '/player')
        {
            return <Route path = {props.path} exact component={Tenant}></Route>
        }
        if(Role == "ROLE_USER" && props.path == '/user')
        {
            return <Route path = {props.path} exact component={Users}></Route>
        }
        else
        {
            return <Login></Login>
        }
    }
    else{
        history.push('/')
    }   
}

export default ProtectedRoute;