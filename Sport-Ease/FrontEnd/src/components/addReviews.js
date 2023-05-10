import * as React from 'react';
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';
import StarIcon from '@mui/icons-material/Star';
import { Button, Stack, TextField } from '@mui/material';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

function AddReview() {

      var history =  useHistory();
    const [value, setValue] = React.useState(0);
    const [reviewText, setReviewText] = React.useState({
        
    });
    const labels = {
        0: '',
        1: 'Poor',
        2: 'Bad',
        3: 'Ok',
        4: 'Good',
        5: 'Excellent'
    };
    
  var email = sessionStorage.getItem("userName");

    const handleSaveReview = () => {
        const reviewData = {
            rating: value,
            reviewText: reviewText
        };
        axios.post(`http://localhost:8080/reviews/addReview/`+email, reviewData)
            .then(response => {
                history.push()
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    };

    return ( 
        <>
            <h3>Add Review</h3>
            <Typography component="legend" variant="h5">Pg Name: TCG

                <Box
                
                    sx={{
                    width: 200,
                    display: 'flex',
                    alignItems: 'center'}}>
                        <Rating
                            sx = {{ml: 40, marginTop: -2.5}}
                            name="simple-controlled"
                            size="large"
                            value={value}
                            onChange={(event, newValue) => {
                            setValue(newValue);
                            }}/> 
                        <Box sx={{ ml: 2, marginTop: -2.5 }}>{labels[value]}</Box>
                </Box>  
                
            </Typography>

            <br/>
            <Box component="form"
                    sx={{
                        '& > :not(style)': { width: '500px' },
                    }}
                    noValidate
                    autoComplete="off">

                <TextField id="review" type="text" label='Type here' variant="outlined"
                        name="ownerAddress" 
                        multiline rows={6} required 
                        size='medium' inputProps={{ style: { fontSize: 15} ,maxLength: 200}}
                        value={reviewText} onChange={(e) => setReviewText(e.target.value)} /><br /><br />

                <Stack direction='row-reverse' spacing={2}>
                    <Button variant='contained' onClick={handleSaveReview}>Save</Button>
                </Stack>  
            </Box>
            
        </>
     );
}

export default AddReview;


