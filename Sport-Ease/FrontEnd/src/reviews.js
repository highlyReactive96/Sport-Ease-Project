import { Box, Button, createTheme, MenuItem, Paper, IconButton, Stack, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, ThemeProvider, Typography } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";

function Reviews() {
    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })
    var history = useHistory();
    var token = sessionStorage.getItem("Token")
    var [reviewList, setReviewList] = useState([])
    var [message, setMessage] = useState("")

    var AddReview = () =>{
        history.push("/addReviews")
    }


    var id = sessionStorage.getItem("academyId")
    useEffect(() => {
        axios.get("http://localhost:9090/reviews/getReviewById/"+id)
        .then((result) => {
            setReviewList(result.data)
        })
        .catch((error) => {
            setMessage(error.message)
        })
    },[])

    return ( 
        <>
            <ThemeProvider theme={theme}>
                <Typography variant="h5">Review List</Typography>
                <br/>
                <Box component="form"
                    sx={{
                        '& > :not(style)': { m: 1, width: '185px' },
                    }}
                    noValidate
                    autoComplete="off">
                    
                </Box>
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow sx={{backgroundColor: "lightgray"}}>
                                <TableCell>Review id</TableCell>
                                <TableCell>Date</TableCell>
                                <TableCell>Description</TableCell>
                                <TableCell>Stars</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                reviewList.map((reviews) => {
                                    return (
                                            <TableRow key={reviews.id}>
                                                <TableCell>{reviews.id}</TableCell>
                                                <TableCell> {reviews.date}</TableCell>
                                                <TableCell> {reviews.description}</TableCell>
                                                <TableCell> {reviews.stars}</TableCell>
                                            </TableRow>
                                    )
                                })
                            }
                        </TableBody>
                    </Table>
                </TableContainer>
                        <br/>
                        <Button variant='contained' onClick={AddReview} >Add Review</Button>
                    

            </ThemeProvider>
        </>
     );
}

export default Reviews;