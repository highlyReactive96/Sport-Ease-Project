import { Grid } from "@mui/material";
import axios from "axios";
import { Result } from "postcss";
import { useEffect, useState } from "react";
import AcademyInfo from "./components/academy/AcademyInfo";

function UserDashBoard() {

    const city = sessionStorage.getItem('city')

    var [academyList,setacademyList] = useState([])

    useEffect(()=>{
        axios.get("http://localhost:9090/academy/findbycity/"+city)
            .then(result=>{
                console.log(result.data)
                setacademyList(result.data)
                
            })
            .catch(err=>{
                console.log(err.message)
                alert("server down")
            })
    },[])

    

    return ( 
        <Grid container rowSpacing={3}>
        
        {
            academyList.map(a=>{
                return(
                    <Grid item xs={9} key={a.id}>
                        <AcademyInfo academy={a}></AcademyInfo>
                    </Grid>
                    
                )
            })
        }
        </Grid>
     );
}

export default UserDashBoard;