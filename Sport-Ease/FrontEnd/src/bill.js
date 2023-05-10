import { Box, Button, createTheme, MenuItem, Paper, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, ThemeProvider, Typography } from "@mui/material";
import axios from "axios";
import ClearIcon from '@mui/icons-material/Clear';


import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

function Bill() {
    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })

    // ,{ 
    //     headers : {
    //      "Authorization" : "Bearer " + Token
    //     } 
    //  }
    var history = useHistory()
    var Token = sessionStorage.getItem("Token")
    var [billList, setBillList] = useState([])
    var [searchType, setSearchType] = useState({
                                    Type: "",
                                    date: ""
                                })


    var [message, setmessage] = useState("")
    var id = sessionStorage.getItem("pgId")
    
    useEffect(() => {
        axios.get("http://localhost:8080/bill/billList/"+id)
            .then((result) => {
                setBillList(result.data)
            })
            .catch((error) => {
                setmessage(error.message)
            });

    }, [])

    useEffect(() => {
        debugger
        if (searchType.Type != "" && searchType.date == "") {
            var url = "http://localhost:8080/bill/findbytype/"+id+"/" + searchType.Type;
            axios.get(url)
                .then((result) => {
                    setBillList(result.data)
                })
                .catch((error) => {
                    setmessage(error.message)
                });
        }
        else if (searchType.Type == "" && searchType.date != "") {

            var url = "http://localhost:8080/bill/findbydate/"+id+"/" + searchType.date;
            axios.get(url)
                .then((result) => {
                    setBillList(result.data)
                })
                .catch((err) => {
                    setmessage(err.data)
                })
        }
        else if(searchType.Type != "" && searchType.date != ""){

            var url = "http://localhost:8080/bill/findByDateAndTime/"+id+"/" + searchType.Type+":"+ searchType.date
            axios.get(url)
                .then((result) => {
                    setBillList(result.data)
                })
                .catch((err) => {
                    setmessage(err.data)
                })
        }
        
    }, [searchType])


    var clearButton = () => {
        searchType.Type = "";
        searchType.date = "";

        var url = "http://localhost:8080/bill/billList/"+id;
        axios.get(url)
            .then((result) => {
                setBillList(result.data)
            })
            .catch((error) => {
                setmessage(error.message)
            });
    }

    var AddBill = ()=>{
        history.push("/addBill")
    }

    var handleChange = (args) => {
        var changedSearchType = {...searchType}
        changedSearchType[args.target.name] = args.target.value;
        setSearchType(changedSearchType);
    }

    return (<>
             <h2>Bill List</h2>

        <ThemeProvider theme={theme}>

            <Box component="form"
                sx={{
                    '& > :not(style)': { m: 1, width: '150px' },
                }}
                noValidate
                autoComplete="off">
                <TextField id="billtype" label="Search By type" name="Type"
                    onChange={handleChange} required select size='small' value={searchType.Type}  >
                    <MenuItem value="Light bill">Light bill</MenuItem>
                    <MenuItem value="Wifi">Wifi</MenuItem>
                    <MenuItem value="Water">Water</MenuItem>
                    <MenuItem value="Gas bill">Gas bill</MenuItem>
                </TextField>
                <TextField type={"date"} value={searchType.date} size='small' name="date" onChange={handleChange} ></TextField>

                <Button 
               style={{width: '100px', height: '38px'}} size='small' color="error"
                variant='contained' onClick={clearButton} endIcon={<ClearIcon />} >Clear</Button>

            </Box>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow sx={{backgroundColor: "lightgray"}}>
                            <TableCell>BillNo</TableCell>
                            <TableCell>Type</TableCell>
                            <TableCell>Date</TableCell>
                            <TableCell>Amount</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            billList.map((bill) => {
                                return (
                                    <TableRow key={bill.id}>
                                        <TableCell>{bill.id}</TableCell>
                                        <TableCell> {bill.billType}</TableCell>
                                        <TableCell>{bill.dueDate}</TableCell>
                                        <TableCell>{bill.amount}</TableCell>
                                    </TableRow>
                                )
                            })
                        }
                    </TableBody>
                </Table>
            </TableContainer>
                    <br/>
                    <Button variant='contained' onClick={AddBill} >Add Bill</Button>
                        <p style={{color : "red"}}>{message}</p>
        </ThemeProvider>
    </>);
}

export default Bill;