
import { useEffect, useState } from "react";
import { Route, Switch } from "react-router-dom";
import Admin from "./admin";
import Header from "./components/header";
import Login from "./login"
import Owner from "./owner";
import ProtectedRoute from "./protectedRoute";
import Register from "./register";
import Player from "./player";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navlink from "./components/nav";

import Users from "./users";
import OwnerDashboard from "./ownerDashboard";
import UserDashBoard from "./usersDashboard";

import Amenities from "./components/amenities";
import AddAcademy from "./addAcademy";
import CitiesList from "./components/users/citiesList";
import AcademyInfo from "./components/academy/AcademyInfo";
import AcademyDisplay from "./components/AcademyDisplay";
import FrontPage from './components/FrontPage';

import OwnerRegistration from "./components/ownerregistration";
import AddAcademyTo from "./components/addacademy";

function MainUi() {

    return ( 
       < div style={{backgroundImage:'url("http://surl.li/fkxri")',backgroundRepeat: 'no-repeat',backgroundSize: 'cover' ,alt:"Nothing",height:"100vh" }}>
                <Header></Header>
                <br></br>
                <Switch> 
                    <Route path="/" exact component={FrontPage}></Route>
                    <Route path='/login' exact component={Login}></Route>
                    <Route path='/register' exact component={Register}></Route>
                    <Route path='/registerOwner' exact component={OwnerRegistration}></Route>
                    <Route path='/addAcademyToOwner' exact component={AddAcademyTo}></Route>
                    <Route path='/amenities' exact component={Amenities}></Route>
                    <Route path='/addAcademy' exact component={AddAcademy}></Route>
                    <Route path='/navbar' exact component={Navlink}></Route>
                    <Route path='/ownerDashboard' exact component={OwnerDashboard}></Route>
                    <Route path='/academydisplay' exact component={AcademyDisplay}></Route>
                    <Route path='/usersDashboard' exact component={UserDashBoard}></Route>
                    <Route path='/citiesList' exact component={CitiesList}></Route>
                    <Route path='/academyInfo' exact component={AcademyInfo}></Route>
                    <Route path='/academyList' exact component={AcademyDisplay}></Route>

                    <ProtectedRoute path={'/admin'}  exact component={Admin}></ProtectedRoute>
                    <ProtectedRoute path={'/academy'}  exact component={Owner}></ProtectedRoute>
                    <ProtectedRoute path={'/player'}  exact component={Player}></ProtectedRoute>
                    <ProtectedRoute path={'/user'}  exact component={Users}></ProtectedRoute>
                </Switch>
               </div> 

     );
}

export default MainUi;