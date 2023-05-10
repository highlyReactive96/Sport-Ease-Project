import { UploadFile } from "@mui/icons-material";
import { Button, createTheme, Grid, MenuItem, Stack, TextField, ThemeProvider, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import SportsList from "./components/amenities";

function AddAcademy() {

    var email = sessionStorage.getItem("userName")
    var history = useHistory()
    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })

    var [academy,setAcademy] = useState({
        academyName : "",
        location :"",
        city:"",
        address : "",
        description : "",
        sportList : [],
        photoList : []
    })

    var [sport,setSport]= useState(null)

    var handlechange = (args)=>{
        var copyAcademy = {...academy}
        copyAcademy[args.target.name] = args.target.value
        setAcademy(copyAcademy)
    }

    var getAminities = (sports)=>{
        setSport(sports)
    }

    const [files,setFiles] = useState({
        photo1 : null,
        photo2 : null,
        photo3 : null
    });

    var handleFileChange = (args) => {
        let f=args.target.files[0];
        var changeFiles = {...files}
        changeFiles[args.target.name] = f
        setFiles(changeFiles)
    }

    var savePg = ()=>{
        if(sport.FOOTBALL)
        {
            academy.sportList.push({ sports : "FOOTBALL"})
        }
        if(sport.CRICKET)
        {
            academy.sportList.push({sports : "CRICKET"})
        }
        if(sport.TENNIS)
        {
            academy.sportList.push({sports : "TENNIS"})
        }
        if(sport.BASKETBALL)
        {
            academy.sportList.push({sports : "BASKETBALL"})
        }
        if(sport.VOLLEYBALL)
        {
            academy.sportList.push({sports : "VOLLEYBALL"})
        }
        uploadFiles(pushToBackend)
        
    }

    var uploadFiles = (pushToBackend)=>{
        let formData = new FormData()
        formData.append("photo1",files.photo1)
        formData.append("photo2",files.photo2)
        formData.append("photo3",files.photo3)
        axios.post("http://localhost:4000/pg/"+email+"_"+academy.academyName.replace(" ","_"),formData)
        .then(result=>{
            const paths = result.data
            academy.photoList.push({photo : paths.path1})
            academy.photoList.push({photo : paths.path2})
            academy.photoList.push({photo : paths.path3})
            pushToBackend(academy)
        })
    }

    var pushToBackend = (p) =>{
        console.log(p)
        axios.post("http://localhost:9090/academyOwner/addacademy/"+email,p)
        .then((result)=>{
            if(result.data == "OK")
            {
                history.push("/owner")
            }
        })
        .catch((err)=>{
            console.log(err.message)
        })
    }




    return ( 
        <ThemeProvider theme={theme}>
            <Grid container spacing={6} justifyContent="center" display="flex" flexWrap="wrap">
                <Grid item xs={4}>
                    <TextField id="academyName" type="text" label="Academy Name" variant="outlined"
                        required name="academyName" value={academy.academyName}
                        onChange={handlechange}/>
                </Grid >
                
                <Grid item xs={4}>
                    <TextField id="academyNoOfSports" type="text" label="NoOfSports" variant="outlined"
                        required name="NoOfSports" value={academy.noOfSports} 
                        
                        onChange={handlechange}/>
                </Grid>

                <Grid container item xs={4}>
                    <TextField
                        value={academy.address}
                        name="address"
                        label="Full Address"
                        multiline
                        minRows={5}
                        sx={{width : 300}}
                        onChange={handlechange}
                        />
                </Grid>

                <Grid container item xs={4}>
                    <TextField
                        value={academy.description}
                        name="description"
                        label="Description about Academy"
                        multiline
                        minRows={5}
                        sx={{width : 300}}
                        onChange={handlechange}
                        />
                </Grid>

                <Grid item xs={4}>
                    <TextField id="location" type="text" label="Location" variant="outlined"
                        required name="location" value={academy.location}
                        onChange={handlechange}/>
                </Grid >
                <Grid item xs={4}>
                    <TextField id="city" type="text" label="City" variant="outlined"
                        required name="city" value={academy.city}
                        onChange={handlechange}/>
                </Grid >

                <Grid container item xs={12}>
                    <SportsList getAminities={getAminities}></SportsList>
                </Grid>

                <Grid container item xs={12}>
                    <Stack spacing={2}>
                        <Typography variant="h5">Academy Photos</Typography>               
                        <Stack direction='row' >
                            <input type="file"
                                    name="photo1"
                                    onChange={handleFileChange}/>
                            <input type="file"
                                    name="photo2"
                                    onChange={handleFileChange}/>
                            <input type="file"
                                    name="photo3"
                                    onChange={handleFileChange}/>
                            <Button variant='contained'>Save</Button>
                        </Stack>
                    </Stack>
                </Grid>

                <Grid item container justifyContent="flex-end">
                    <Button variant="contained" size="large" onClick={savePg}> Add Academy </Button>
                </Grid>

            </Grid>
            
                    
        </ThemeProvider>
    );
}

export default AddAcademy;