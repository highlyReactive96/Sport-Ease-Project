import SportsCricketIcon from '@mui/icons-material/SportsCricket';
import SportsSoccerIcon from '@mui/icons-material/SportsSoccer';
import SportsTennisIcon from '@mui/icons-material/SportsTennis';
import SportsBasketballIcon from '@mui/icons-material/SportsBasketball';
import SportsVolleyballIcon from '@mui/icons-material/SportsVolleyball';
import { Box, Grid, Typography, } from '@mui/material';
import { useEffect, useState } from 'react';

function Amenities({getAminities}) {

    var [sports,setSports] = useState({
        FOOTBALL : false,
        CRICKET : false,
        TENNIS : false,
        BASKETBALL : false,
        VOLLEYBALL: false
    })

    var handleChange = (args)=>{
        var copySports = {...sports}
        copySports[args.target.name] = args.target.checked
        setSports(copySports)
    }

    useEffect(()=>{
        getAminities(sports)
    },[sports])

    return ( 
        <>
            <h3>Sports list</h3>
            <Grid container spacing={2} >
                <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Box bgcolor='lightblue' p={2}>
                        <SportsSoccerIcon  
                            sx={{
                                height: 40,
                                width: 40,
                            }}></SportsSoccerIcon>
                    </Box>
                    <input type="checkbox" name="FOOTBALL" checked={sports.FOOTBALL} onChange={handleChange}/>
                </Grid>
                <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Box bgcolor='lightblue' p={2}>
                        <SportsCricketIcon 
                            sx={{
                                height: 40,
                                width: 40,
                            }}></SportsCricketIcon>
                    </Box>
                    <input type="checkbox" name="CRICKET" checked={sports.CRICKET} onChange={handleChange}/>
                </Grid>
                <Grid item xs={3} sx={{textAlign: 'center'}}>
                    <Box bgcolor='lightblue' p={2}>
                        <SportsTennisIcon
                            sx={{
                                height: 40,
                                width: 40,
                            }}></SportsTennisIcon>
                    </Box>
                    <input type="checkbox" name="TENNIS" checked={sports.TENNIS} onChange={handleChange}/>

                </Grid>
                <Grid item xs={3}  sx={{textAlign: 'center'}}>
                    <Box bgcolor='lightblue' p={2}>
                        <SportsBasketballIcon
                            sx={{
                                height: 40,
                                width: 40,
                            }}></SportsBasketballIcon>
                    </Box>
                    <input type="checkbox" name="BASKETBALL" checked={sports.BASKETBALL} onChange={handleChange}/>

                </Grid>
                <Grid item xs={3}  sx={{textAlign: 'center'}}>
                    <Box bgcolor='lightblue' p={2}>
                        <SportsVolleyballIcon
                            sx={{
                                height: 40,
                                width: 40,
                            }}></SportsVolleyballIcon>
                    </Box>
                    <input type="checkbox" name="VOLLEYBALL" checked={sports.VOLLEYBALL} onChange={handleChange}/>
                </Grid>
            </Grid>
        </>
     );
}

export default Amenities;