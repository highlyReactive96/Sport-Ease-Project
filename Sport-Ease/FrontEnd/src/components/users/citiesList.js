import { Grid, Typography } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import CitiesCard from "./citiesCard";

function CitiesList() {

    var [cities,setCities] = useState([])

    useEffect(()=>{
        axios.get("http://localhost:9090/academy/finddistinctcities")
        .then(result => {
            console.log(result.data)
            setCities(result.data)
        })
        .catch(err=>{
            console.log(err.message)
            alert("something went wrong")
        })
    },[])

    return ( 
        <>
            <Typography variant="h1" sx={{textAlign : 'center',marginBottom : 5,marginTop : 5}}>Choose a city...</Typography>
            <Grid container sx={{display : 'flex', flexWrap : 'wrap'
                                , justifyContent: 'center'}} spacing={2}>
            
            {   
                cities.map(city=>{
                                return (
                                        <Grid item key={city} >
                                            <CitiesCard name = {city}></CitiesCard>
                                        </Grid>
                                        )
            })}
            
            </Grid>
        </> 
        
    );
}

export default CitiesList;
