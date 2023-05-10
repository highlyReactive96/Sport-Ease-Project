import { Block } from "@mui/icons-material";
import { Button, ButtonGroup, Stack } from "@mui/material";
import HomeIcon from '@mui/icons-material/Home';
import AddReactionIcon from '@mui/icons-material/AddReaction';
import { Link } from "react-router-dom";

function  Navlink(props) {
  const { role } = props;

  return (<>
    
    <Stack  spacing={2} direction="row">
      <ButtonGroup>
        {role === "ROLE_ACADEMY" &&<Button  variant="contained"><h6>Home</h6></Button>}
        <Link to="/employee">{role === "ROLE_ACADEMY" && <Button variant="contained"><h6>Employee</h6></Button>}</Link>
        <Link to="/bill">{role === "ROLE_ACADEMY" &&<Button variant="contained"><h6>Bill</h6></Button>}</Link>
        <Link to="/expenditure">{role === "ROLE_ACADEMY" && <Button variant="contained"><h6>Expenses</h6></Button>}</Link> 
        <Link to="/complaint"> {role === "ROLE_ACADEMY" &&<Button variant="contained"><h6>Complaint</h6></Button>}</Link>
        <Link to="/reviews"> {role === "ROLE_ACADEMY" &&<Button variant="contained"><h6>Reviews</h6></Button>}</Link>

        <Link to="/usersDashboard"> {role === "ROLE_USER" && <Button variant="contained"><h6>DASH BOARD</h6></Button>}</Link>
        {/* <Link to="/academyInfo"> {role === "ROLE_USER" && <Button variant="contained"><h6>ACADEMY INFO</h6></Button>}</Link>
        <Link to="/academyList"> {role === "ROLE_USER" && <Button variant="contained"><h6>ACADEMY LIST</h6></Button>}</Link> */}
        <Link to="/citiesList"> {role === "ROLE_USER" && <Button variant="contained"><h6>City LIST</h6></Button>}</Link>
      </ButtonGroup>
    </Stack>
  </>);
}

export default Navlink;