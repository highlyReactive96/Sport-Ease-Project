import { Box, Button, Card, CardContent, Grid, Stack, Typography,Container } from "@mui/material";
import CarouselTag from "../../carouselTag";
import MyIcons from "../../utils/myIcons";
import { useHistory } from "react-router-dom";

function AcademyInfo(props) {
    var history = useHistory()
    var showAcademy = ()=>{
        sessionStorage.setItem('academyInfoId', props.academy.id)
        history.push('/academydisplay')
    }
    return ( 
        
        <Box>
            <Card style={{backgroundColor : '#bbdefb'}}>
            <CardContent>
                        <Grid container spacing={3} sx={{m : 0.01}}>
                            <Grid item xs={4}>
                                <CarouselTag photolist={props.academy.photoList}></CarouselTag>
                            </Grid>
                            <Grid item xs={8}>
                                <Grid container spacing={2}>
                                    <Grid item xs={6}>
                                        <Typography variant="h4">Name : {props.academy.academyName}</Typography>
                                    </Grid>
                                    <Grid item xs={6} spacing={2}>
                                        <Typography variant="h4">Location : {props.academy.city}</Typography>
                                    </Grid>
                                    {/* <Grid item xs={6} spacing={2}>
                                        <Typography variant="h4">Address : {props.academy.address}</Typography>
                                    </Grid>
                                    <Grid item xs={6} spacing={2}>
                                        <Typography variant="h4">No Of Sports : {props.academy.noOfSports}</Typography>
                                    </Grid> */}
                                    <Grid item xs={12}>
                                        <Typography variant="h4">Sports Available : </Typography>
                                    </Grid>
                                    {
                                        props.academy.sportList.map(sport=>{
                                            return (
                                                <Grid item xs={2} key={sport.sports}>
                                                    <MyIcons name={sport.sports}></MyIcons>
                                                </Grid>
                                            )
                                        })
                                    }
                                </Grid>
                            </Grid> 
                        </Grid>
                        <Stack direction={'row-reverse'}>
                                <Button sx={{marginLeft: "auto"}} variant="contained" onClick={showAcademy}  size="large"> View </Button>
                        </Stack>
                        </CardContent>
                
            </Card>
        </Box>
     );
}

export default AcademyInfo;