import { Box, Button, Card, CardActions, CardContent, Typography } from "@mui/material";
import { useHistory } from "react-router-dom";

function CitiesCard(props) {

    var history = useHistory()
    var showAcademy = ()=>{
        sessionStorage.setItem('city', props.name)
        history.push('/usersDashboard')
    }

    return (  
        <Box>
            <Card sx={{width : 200}}>
                <CardContent >
                        <Typography sx={{textAlign : 'center'}} variant="h2">{props.name}</Typography>
                </CardContent>
                <CardActions disableSpacing>
                    <Button sx={{marginLeft: "auto"}} onClick={showAcademy} variant="contained" size="large"> View </Button>
                </CardActions>
            </Card>
        </Box>
    );
}

export default CitiesCard;