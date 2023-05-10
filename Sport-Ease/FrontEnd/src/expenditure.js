import { Box, Button, createTheme, MenuItem, Paper, IconButton, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, ThemeProvider, Typography } from "@mui/material";
import axios from "axios";
import ClearIcon from '@mui/icons-material/Clear';
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

function Expenditure() {
    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })

    var id = sessionStorage.getItem("pgId")

    var token = sessionStorage.getItem("Token")
    var [expenditureList, setExpenditureList] = useState([])
    var [message, setMessage] = useState("")
    var [searchType, setSearchType] = useState({
        date: ""
    })

    useEffect(() => {
        axios.get("http://localhost:8080/expenses/findAllExpense/"+id)
        .then((result) => {
            setExpenditureList(result.data)
        })
        .catch((error) => {
            setMessage(error.message)
        })

    },[])

    useEffect(() => {
        axios.get("http://localhost:8080/expenses/sortExpense/"+id+"/"+ searchType.date)
        .then((result) => {
            setExpenditureList(result.data)
        })
        .catch((error) => {
            setMessage(error.message)
        })
    },[searchType])

    var handleChange = (args) => {
        var changedSearchType = {...searchType}
        changedSearchType[args.target.name] = args.target.value;
        setSearchType(changedSearchType);
    }

    return ( 
        <>
            <ThemeProvider theme={theme}>
                <Typography variant="h4">Expenditure List</Typography>
                <br/>
                <Box component="form"
                    sx={{
                        '& > :not(style)': { m: 1, width: '185px' },
                    }}
                    noValidate
                    autoComplete="off">

                    <TextField id="search" onChange={handleChange} helperText="Search by month" 
                                value={searchType.date} name="search" type="date" label=""
                                required size='small'>
                    </TextField>

                </Box>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow sx={{backgroundColor: "lightgray"}}>
                                <TableCell>Expenditure id</TableCell>
                                <TableCell>Expenditure Date</TableCell>
                                <TableCell>Total Bill</TableCell>
                                <TableCell>Total Emp Salary</TableCell>
                                <TableCell>Total Rent</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                expenditureList.map((expenses) => {
                                    return (
                                        <TableRow key={expenses.id}>
                                            <TableCell>{expenses.id}</TableCell>
                                            <TableCell> {expenses.expenditureDate}</TableCell>
                                            <TableCell> {expenses.totalBill}</TableCell>
                                            <TableCell> {expenses.totalRent}</TableCell>
                                            <TableCell> {expenses.totalEmpSal}</TableCell>
                                            {/* <TableCell> {employee.pg}</TableCell> */}
                                        </TableRow>
                                    )
                                })
                            }
                        </TableBody>
                    </Table>
                </TableContainer>

            </ThemeProvider>
        </>
     );
}

export default Expenditure;