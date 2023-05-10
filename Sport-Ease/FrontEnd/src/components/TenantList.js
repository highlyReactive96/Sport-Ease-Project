import { Box, Button, createTheme, MenuItem, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, ThemeProvider } from "@mui/material";
import axios from "axios";
import { useEffect, useState, props } from "react";
import SearchIcon from '@mui/icons-material/Search';
import { useHistory } from "react-router-dom";
import ClearIcon from '@mui/icons-material/Clear';

function TenantList() {
    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })
    var [tenantList, setTenantList] = useState([])
    var [roomList, setRoomList] = useState([])
    var [floorList, setFloorList] = useState([])
    var id = sessionStorage.getItem("pgId")


    var [search, setSearch] = useState({
        name: "",
        roomNo: "",
        floorNo: "",
        genders: ""
    })

    var handlechange = (args) => {
        var changedSearch = { ...search }
        changedSearch[args.target.name] = args.target.value
        setSearch(changedSearch)
    }


    var [message, setMessage] = useState("")

    var history = useHistory();
    useEffect(() => {
        axios.get("http://localhost:8080/tenant/getAllTenant/"+id,)
            .then((result) => {
                setTenantList(result.data)
            })
            .catch((error) => {
                setMessage(error.message)
            });



        axios.get("http://localhost:8080/tenant/getRoomList/"+id,)
            .then((result) => {
                setRoomList(result.data)
                console.log(result.data)
            })
            .catch((error) => {
                setMessage(error.message)
            });



        axios.get("http://localhost:8080/tenant/getFloorList/"+id,)
            .then((result) => {
                setFloorList(result.data)
                console.log(result.data)
            })
            .catch((error) => {
                setMessage(error.message)
            });
    }, [])

    useEffect(() => {
        
        if (search.name != "" && search.roomNo == "" && search.floorNo == "" && search.genders == "") {
            var url = "http://localhost:8080/tenant/getTenantByName/"+id;
            axios.post(url, search)
                .then((result) => {
                   
                    setTenantList(result.data)
                })
                .catch((error) => {
                    setMessage(error.message)
                });

        }
        else if (search.name == "" && search.roomNo == "" && search.floorNo != "" && search.genders == "") {
            var url = "http://localhost:8080/tenant/getTenantByFloors/" + search.floorNo;
            axios.get(url)
                .then((result) => {
                    setTenantList(result.data)
                })
                .catch((error) => {
                    setMessage(error.message)
                });

        }
        else if (search.name == "" && search.roomNo != "" && search.floorNo == "" && search.genders == "") {
            var url = "http://localhost:8080/tenant/getTenantByRooms/" + search.roomNo;
            axios.get(url)
                .then((result) => {
                    setTenantList(result.data)
                })
                .catch((error) => {
                    setMessage(error.message)
                });

        }

        else if (search.name == "" && search.roomNo == "" && search.floorNo == "" && search.genders != "") {
            var url = "http://localhost:8080/tenant/getTenantByGender/"+id+"/"+ search.genders;
            axios.get(url)
                .then((result) => {
                    setTenantList(result.data)
                })
                .catch((error) => {
                    setMessage(error.message)
                });
        }
    }, [search])

    var clearSearch = ()=>{
        history.go(0)
    }


    var DetailsPage = (id) => {
        sessionStorage.setItem("tid" , id)
        history.push("/tenantInfo")
    }


    return (<>
        <ThemeProvider theme={theme}>
            <Box component="form" textAlign={"right"}
                sx={{
                    '& > :not(style)': { m: 1, width: '150px' },
                }}
                noValidate
                autoComplete="off">
                
                <TextField type={"text"} value={search.name} size='small' name="name" onChange={handlechange}  ></TextField>


                <TextField label="Search By Room" name="roomNo" onChange={handlechange}
                    select size='small'    >
                    {
                        roomList.map((room) => {
                            return (
                                <MenuItem value={room}>{room}</MenuItem>
                            )
                        })
                    }

                </TextField>
                <TextField label="Search By Floor" name="floorNo" onChange={handlechange}
                    select size='small'    >
                    {
                        
                        floorList.map((floor) => {
                            return (
                                <MenuItem value={floor}>{floor}</MenuItem>
                            )
                        })
                    }

                </TextField>
                <TextField label="Search By Gender" name="genders" onChange={handlechange}
                    select size='small'  >
                    <MenuItem value="MALE">MALE</MenuItem>
                    <MenuItem value="FEMALE">FEMALE</MenuItem>
                </TextField>
                <Button onClick={clearSearch} endIcon={<ClearIcon />} ></Button>
            </Box>
            <p style={{ color: "red" }}>{message}</p>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Tenant id</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Room No</TableCell>
                            <TableCell>Floor No</TableCell>
                            <TableCell>Living Status</TableCell>
                            <TableCell>Gender</TableCell>
                            <TableCell>Details</TableCell>
                            
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            tenantList.map((Tenant) => {
                                var status = "";
                                const current = new Date();
                                const date = `${current.getFullYear()}/${current.getMonth() + 1}/${current.getDate()}`;
                                if (Tenant.livingStatus < date) {
                                    status = "Living"
                                } else {
                                    status = "Left"
                                }



                                return (

                                    <TableRow key={Tenant.tenantid}>
                                        <TableCell>{Tenant.tenantid}</TableCell>
                                        <TableCell> {Tenant.tenantName}</TableCell>
                                        <TableCell>{Tenant.roomNo}</TableCell>
                                        <TableCell>{Tenant.floorNo}</TableCell>
                                        <TableCell>{status}</TableCell>
                                        <TableCell>{Tenant.gender}</TableCell>
                                        <TableCell><Button onClick={()=>{
                                            DetailsPage(Tenant.tenantid)
                                        }} endIcon={<SearchIcon />} ></Button></TableCell>
                                    </TableRow>
                                )
                            })
                        }
                    </TableBody>
                </Table>
            </TableContainer>
        </ThemeProvider>
    </>);
}

export default TenantList;