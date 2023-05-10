import SportsCricketIcon from '@mui/icons-material/SportsCricket';
import SportsSoccerIcon from '@mui/icons-material/SportsSoccer';
import SportsTennisIcon from '@mui/icons-material/SportsTennis';
import SportsBasketballIcon from '@mui/icons-material/SportsBasketball';
import SportsVolleyballIcon from '@mui/icons-material/SportsVolleyball';
function MyIcons(props) {
    switch (props.name) {
        case "FOOTBALL":
            return <SportsSoccerIcon sx={{height : 50,width : 50}}></SportsSoccerIcon>
        case "CRICKET":
            return <SportsCricketIcon sx={{height : 50,width : 50}}></SportsCricketIcon>
        case "TENNIS":
            return <SportsTennisIcon sx={{height : 50,width : 50}}></SportsTennisIcon>
        case "BASKETBALL":
            return <SportsBasketballIcon sx={{height : 50,width : 50}}></SportsBasketballIcon>
        case "VOLLEYBALL":
            return <SportsVolleyballIcon sx={{height : 50,width : 50}}></SportsVolleyballIcon>    
        default:
            return;
    }
}
     

export default MyIcons;