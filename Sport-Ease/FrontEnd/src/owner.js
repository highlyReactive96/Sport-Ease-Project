import { Button, Grid } from "@mui/material";
import { useHistory } from "react-router-dom";

function Owner() {

    const history = useHistory();

    const showAddPg = ()=>{
        history.push('/addAcademy')
    }

    return (  
        <Grid container spacing={10}>
            <Grid item container xs={12} >
            </Grid>
            <Grid item container justifyContent="flex-end">
                <Button variant="contained" size="large" onClick={showAddPg}> Add pg </Button>
            </Grid>
        </Grid>
    );
}

export default Owner;