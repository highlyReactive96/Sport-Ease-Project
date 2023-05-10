import { Box, Button, Grid, Stack, Typography } from "@mui/material";
import { red } from "@mui/material/colors";
import axios from "axios";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

function TenantInfo() {
    var [tenantdetails , setTenantDetails] = useState({})
    var [message , setMessage] = useState("")
    var [roomlist , setRoomList] = useState([])
    var [stat , setStat] = useState("")

    var tid = sessionStorage.getItem("tid");
    var history = useHistory();
    useEffect(()=>{
        axios.get("http://localhost:8080/tenant/getTenant/" +tid)
        .then((result)=>{
            setTenantDetails(result.data)
            console.log(result.data)
            setRoomList(result.data.room)
        })
        .catch((error)=>{
            setMessage(error.data)
        })
    }, [])
    var currentStat = ""
    const current = new Date();
    const date = `${current.getFullYear()}/${current.getMonth() + 1}/${current.getDate()}`;
     if(date > tenantdetails.leavingDate){
        currentStat = "Living"
     }
     else{
        currentStat = "Left"
     }

     var DeleteTenant = () =>{
        axios.get("http://localhost:8080/tenant/deleteTenant/18")
        .then((result)=>{
            if(result.data == "OK"){
                history.go(0);
            }
        })
        .catch((error)=>{
            setMessage(error.data)
        })
     }
    
    return ( 
        <>
            <h3>Tenant Information</h3>
            <Box>
                <Grid container spacing={2}>
                    <Grid xs={4}><br/><br/>
                            <Typography variant="h5">Name: {tenantdetails.name}</Typography><br/>
                            <Typography variant="h5">DOB: {tenantdetails.dob}</Typography><br/>
                            <Typography variant="h5">Room No.: {roomlist.roomNo}</Typography><br/>
                            <Typography variant="h5">Aadhar No.: {tenantdetails.aadharNo}</Typography><br/>
                            <Typography variant="h5">Gender: {tenantdetails.gender}</Typography><br/>
                            <Typography variant="h5">Start Date: {tenantdetails.startDate}</Typography><br/>
                            <Typography variant="h5">Rent Amount: {tenantdetails.rentAmount}</Typography>
                    </Grid>
                    <Grid  xs={4}><br/><br/>
                            <Typography variant="h5">Address: {tenantdetails.address}</Typography><br></br>
                            <Typography variant="h5">Email: {tenantdetails.email}</Typography><br/>
                            <Typography variant="h5">Mobile No.: {tenantdetails.mobileNo}</Typography><br/>
                            <Typography variant="h5">Pan No.: {tenantdetails.panNo}</Typography><br/>
                            <Typography variant="h5">Profession: {tenantdetails.profession}</Typography><br/>
                            <Typography variant="h5">Room Category: {roomlist.roomCategory}</Typography><br/>
                            <Typography variant="h5">Status: {currentStat}</Typography>
                    </Grid>
                </Grid>
            
            <br/><br/><br/>
                <Box component="form"
                        sx={{'& > :not(style)': { marginTop: 0, marginLeft: 80, width: '115px' }}}>
                        <Button variant='contained' color="error" onClick={DeleteTenant}>Remove Tenant</Button>
                </Box>
            </Box>
        </>
     );
}

export default TenantInfo;