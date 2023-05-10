import { Button, IconButton, Paper, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, ThemeProvider, Typography } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import '../node_modules/bootstrap/dist/css/bootstrap.css'
import SearchIcon from '@mui/icons-material/Search';
import ClearIcon from '@mui/icons-material/Clear';
import DialogBox from "./components/dialogBox";
import { createTheme } from "@mui/material";


function Admin() {
    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })
    var [validuser, setValiduser] = useState(null)
    var [isEmpty,setIsEmpty] = useState(false)
    var [ownerlist, setOwnerlist] = useState([])
    var [searchtext , setSearchtext] = useState("")
    var [message , setmessage] = useState("")
    var token = sessionStorage.getItem("Token")

  
    var Search=(args)=>{
        setSearchtext(args.target.value);
    }

    useEffect(()=>{
        if(searchtext == "")
        {
            axios.get("http://localhost:9090/admin/ownerlist",{
            headers : {
                "Authorization" : "Bearer "+token
            }
            })
            .then((result) => {
                setOwnerlist(result.data)
            })
            .catch((error)=>{
                setValiduser(error.message)
            });
        }
        else{
            setIsEmpty(false)
        }
    },[searchtext])

    useEffect(() => {
        setValiduser(sessionStorage.getItem("userName"))
        axios.get("http://localhost:9090/admin/ownerlist",{
            headers : {
                "Authorization" : "Bearer "+token
            }
        })
        .then((result) => {
            setOwnerlist(result.data)
        })
        .catch((error)=>{
            setValiduser(error.message)
        });
            
    }, [])

    var showSearch =()=>{
        if(searchtext != "")
        {
            var url = "http://localhost:9090/admin/findbyname/" + searchtext; 
            axios.get(url,{
                headers : {
                    "Authorization" : "Bearer "+token
                }
            })
            .then((result) => {
                setOwnerlist(result.data)
            })
            .catch((error)=>{
                setValiduser(error.message)
            });
        }
        else
        {
            setIsEmpty(true)
        }
        
    }
    



    return (
        <>
            <Typography variant="h2"> Hello admin {validuser}</Typography>
            <ThemeProvider theme={theme}>
            <Stack spacing={2} direction='column' >
                <Stack direction='row' spacing={2} alignItems={"center"}>
                    <TextField id="outlined-search" label="Search Owner" 
                        variant="filled" 
                        type={"search"}
                        InputProps={{
                                        style: {height : 50},
                                        endAdornment : <IconButton onClick={()=>{
                                            setSearchtext("")
                                        }}>
                                            <ClearIcon></ClearIcon>
                                        </IconButton>
                                    }}
                        InputLabelProps={{style: {fontSize: 15}}}
                        value={searchtext}
                        onChange={Search}
                        error={isEmpty}/>
                    <Button style={{height : 40}} endIcon={<SearchIcon/>} 
                    variant="contained" color="info" size="medium"
                    onClick={showSearch}>Search</Button>
                    <DialogBox></DialogBox>
                </Stack>
                
                <TableContainer component={Paper} >
                    <Table>
                        <TableHead >
                                    <TableRow>
                                        <TableCell>Owner_id</TableCell>
                                        <TableCell>Owner_Name</TableCell>
                                        <TableCell>Mobile No</TableCell>
                                        <TableCell>Email</TableCell>
                                        <TableCell>Address</TableCell>
                                        <TableCell>Aadhar No</TableCell>
                                        <TableCell>Registration Date</TableCell>
                                        <TableCell>Gender</TableCell>
                                    </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                ownerlist.map((owner) => {

                                    return (
                                        <TableRow key={owner.id}>
                                            <TableCell>{owner.id}</TableCell>
                                            <TableCell> {owner.ownerName}</TableCell>
                                            <TableCell>{owner.mobileNo}</TableCell>
                                            <TableCell>{owner.email}</TableCell>
                                            <TableCell>{owner.ownerAddress}</TableCell>
                                            <TableCell>{owner.aadharNo}</TableCell>
                                            <TableCell>{owner.regDate}</TableCell>
                                            <TableCell>{owner.gender}</TableCell>
                                        </TableRow>

                                    )
                                })
                            }
                        </TableBody>
                    </Table>
                </TableContainer>
                
                
            </Stack>
            </ThemeProvider>        
        </>
    );
}

export default Admin;