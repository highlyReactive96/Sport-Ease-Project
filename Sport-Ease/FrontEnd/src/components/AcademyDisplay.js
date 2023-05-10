import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { Card, CardContent, Grid, Typography, Box, Button, Stack } from '@mui/material';
import MyIcons from '../utils/myIcons';
import CarouselTag from "../carouselTag";
// import ReviewList from '../components/ReviewList';

function AcademyDisplay() {
    const [academy, setAcademy] = useState({});
    var id  = sessionStorage.getItem("academyInfoId");

    useEffect(() => {
        console.log();
        console.log(id);
        axios.get(`http://localhost:9090/academy/${id}`)
            .then(response => {
                console.log(response.data);
                setAcademy(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    },[id]);

    const bookNow = (sport) => {
        sessionStorage.setItem('academy', academy.id);
        sessionStorage.setItem('sport', sport.sports);
        window.location.href = '/booking';
    }

    return (
        <Box sx={{ p: 2 }}>
            <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                    <Card sx={{ bgcolor: '#bbdefb' }}>
                        <CardContent>
                            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                                <img src={academy.photoList && academy.photoList[0]} alt="Academy" style={{ width: '100%' }} />
                                {/* <CarouselTag photolist={academy.photoList}></CarouselTag> */}
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} sm={6}>
                    <Card sx={{ bgcolor: '#bbdefb' }}>
                        <CardContent>
                            <Typography variant="h3">{academy.academyName}</Typography>
                            <Typography variant="h4">Location: {academy.city}</Typography>
                            <Typography variant="h4">Sports Available: </Typography>
                            <Grid container spacing={2}>
                                {academy.sportList && academy.sportList.map(sport => (
                                    <Grid item xs={4} key={sport.sports}>
                                        <MyIcons name={sport.sports}></MyIcons>
                                        <Typography>{sport.sports}</Typography>
                                        <Button variant="contained" onClick={() => bookNow(sport)}>Book Now</Button>
                                    </Grid>
                                ))}
                            </Grid>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>
            <Box sx={{ mt: 2 }}>
                {/* <ReviewList academyId={id} /> */}
            </Box>
        </Box>
    );
}

export default AcademyDisplay;
