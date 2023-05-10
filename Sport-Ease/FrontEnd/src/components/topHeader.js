import { useState ,useEffect} from 'react';
import { AppBar, Toolbar, Typography,Button, IconButton, Menu, MenuItem  } from "@mui/material";
import { AccountCircle } from '@mui/icons-material';
import Nav from 'react-bootstrap/Nav';
import logo from '../images/logo.png'
import DecodeToken from '../utils/token';
import { useHistory } from "react-router-dom";
import Navlink from './nav';
import { Box } from '@mui/system';

function 
TopHeader( user ) {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleMenuOpen = (event) => setAnchorEl(event.currentTarget);
  const handleMenuClose = () => setAnchorEl(null);
  const history = useHistory();
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userName, setEmail] = useState("");

  useEffect(() => {
    const token = sessionStorage.getItem("Token");
    const email = sessionStorage.getItem("userName");
    const role = DecodeToken(token).role;

    if (role) {
      setIsLoggedIn(true); // update isLoggedIn state when the user is logged in
    }
  }, []);


  const handleReviewClick = () => {
    handleMenuClose();
    history.push("/addReview");
  }
  
  const handleSeeComplaintClick = () => {
    handleMenuClose();
    history.push("/Complaint");
  }

  const handleComplaintClick = () => {
    handleMenuClose();
    history.push("/addComplaint");
  }

  const handleLogout = () => {
    sessionStorage.clear();
    setIsLoggedIn(false);
    //setEmail(null); 
   // window.location.reload();
    history.replace("/");

  };
 

  const email = sessionStorage.getItem("userName");

    var token = sessionStorage.getItem("Token")
   
    const role = DecodeToken(token).role;

    let menuOptions = [];
  
    if (role === "ROLE_PLAYER") {
      menuOptions = [
        <MenuItem sx={{ fontSize: "1rem" }} onClick={handleMenuClose}>Profile</MenuItem>,
        <Button sx={{ fontSize: "1rem" }}  color="inherit" onClick={handleComplaintClick}>Add Complaint</Button>,
        <Button sx={{ fontSize: "1rem" }} color="inherit" onClick={handleReviewClick}> Add Reviews</Button>,
        <MenuItem sx={{ fontSize: "1rem" }} onClick={handleLogout}>Logout</MenuItem>
      ];
    } else if (role === "ROLE_ACADEMY") {
      menuOptions = [
        <MenuItem onClick={handleLogout}>Logout</MenuItem>,
        // <Button color="inherit" onClick={handleSeeComplaintClick}>Complaint</Button>,
      ];
    } else if (role === "ROLE_USER") {
      menuOptions = [
        <MenuItem onClick={handleLogout}>Logout</MenuItem>
      ];
    } else if (role === "ROLE_ADMIN") {
      menuOptions = [
        <MenuItem onClick={handleLogout}>Logout</MenuItem>
      ];
    }

  return (
    <AppBar position="fixed">
      <Toolbar>
  <Box sx={{ display: 'flex', alignItems: 'center' }}>
    <Box sx={{ display: 'flex', alignItems: 'center', mr: '8rem'}}>
      {/* Logo and Title */}
      {/* <img src={logo} height={60} style={{ marginRight: '1rem' }} alt="Logo" /> */}
      <Typography variant="h4" style={{ flexGrow: 1 }}>Sport Ease</Typography>
    </Box>
    {/* Navlinks */}
    <Box style={{ display: 'flex', alignItems: 'center',marginLeft: '8rem'}}>  
  <Navlink role={role} ></Navlink>  
</Box>

    <Nav style={{ fontSize: "25px"}}>
      <Nav.Link href="login" style={{color:"white", marginLeft:"100px"}}>Book</Nav.Link>
      <Nav.Link href="contact" style={{color:"white",marginLeft:'20px',marginRight:"100px"}}>Contact Us</Nav.Link>
      {/* <Nav.Link href="login" style={{color:"white",marginLeft:'20px'}}>Login</Nav.Link> */}
    </Nav>
    {/* User info and menu */}
    {role && (
      <Box sx={{ display: 'flex', alignItems: 'center', ml: 'auto' }}>
        <Typography variant="subtitle2" style={{ marginRight: '1rem' }}>
          <h3>Welcome, {email}!</h3>
        </Typography>
        <IconButton
          size="large"
          edge="start"
          aria-label="account of current user"
          aria-controls="menu-appbar"
          aria-haspopup="true"
          onClick={handleMenuOpen}
          color="inherit"
          sx={{ fontSize: "3rem" }} // Increase icon size by setting fontSize to a larger value
        >
          <AccountCircle sx={{ fontSize: '3rem' }}  />
        </IconButton>
        <Menu
          anchorEl={anchorEl}
          anchorOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
          id="menu-appbar"
          keepMounted
          transformOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
          open={open}
          onClose={handleMenuClose}
        >
          {menuOptions}
        </Menu>
      </Box>
    )}
  </Box>
</Toolbar>

    </AppBar>
  );
}

export default TopHeader;
