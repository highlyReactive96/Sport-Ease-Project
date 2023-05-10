import { Button, Dialog,DialogTitle,DialogContent,DialogActions,DialogContentText } from "@mui/material";
import AddCircleIcon from '@mui/icons-material/AddCircle';
import { useEffect, useState } from "react";
import AddOwner from "./ownerForm";
import { useHistory } from "react-router-dom";
import AddPgPhotos from "./pgPhotos";

function DialogBox() {

    var [open,setOpen] = useState(false)

    return ( 
        <>
            <Button variant="contained" size="large" endIcon={<AddCircleIcon/>} onClick={()=>{setOpen(true)}}
            style={{ fontSize: '15px', height : 40}}>Add Owner</Button>
            <Dialog open={open} onClose={()=>setOpen(false)}>
                <DialogTitle>Register Owner</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        <AddOwner exit={setOpen}></AddOwner>
                    </DialogContentText>
                </DialogContent>
                <DialogActions >
                    <Button onClick={()=>{setOpen(false)}}>Cancel</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}

export default DialogBox;