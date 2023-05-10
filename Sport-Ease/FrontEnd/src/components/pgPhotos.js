import { Button, Stack, Typography } from "@mui/material";


function AddPgPhotos() {

    return ( 
        <Stack spacing={2}>
            <Typography variant="h5">Pg Photos</Typography>               
            <Stack direction='row' >
                <input type="file"
                        name="photo"
                        label="Upload"/>
                <input type="file"
                        name="aadharCard"
                        label="Upload"/>
                <input type="file"
                        name="aggrementLetter"
                        label="Upload"/>
                <Button variant='contained'>Save</Button>
            </Stack>
        </Stack>
     );
}

export default AddPgPhotos;