import { Button, createTheme, Stack, ThemeProvider, Typography } from "@mui/material";
import axios from "axios";
import { Form, Formik } from "formik";
import jwtDecode from "jwt-decode";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import InputBox from "./inputBox";
import * as Yup from 'yup'

function LoginForm() {

    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })

    var users = {
        email: "",
        password: ""
    }

    var [message, setMessage] = useState("")

    var history = useHistory();

    var validate = Yup.object({
        email: Yup.string().max(50, "Must be 50 characters or less")
            .email('Email is invalid').required('Email is Required'),
        password: Yup.string().required('Password is Required'),


    })

    var authenticate = (u) => {
        setMessage("")
        axios.post("http://localhost:9090/authenticate", u)
            .then((result) => {
                if (result.data != "") {
                    var token = result.data;
                    var Role = jwtDecode(token).role;
                    var validUserName = jwtDecode(token).name;
                    sessionStorage.setItem("isLogedIn", true)
                    sessionStorage.setItem("userName", validUserName)
                    sessionStorage.setItem("Token", token);
                    if (Role == "ROLE_ADMIN") {
                        history.push('/admin')
                        window.location.reload();
                    }
                    else if (Role == "ROLE_PLAYER") {
                        history.push('/player')
                        window.location.reload();

                    }
                    else if (Role == "ROLE_USER") {
                        history.push('/user')
                        window.location.reload();
                    }
                    else if (Role == "ROLE_ACADEMY") {
                        history.push('/academy')
                        window.location.reload();
                    }
                }
            })
            .catch((error) => {
                console.log(error.message)
                setMessage("Invalid username or password")
            })
    }
    return (
        <div >
            <Formik
                initialValues={
                    {
                        email: "",
                        password: ""
                    }
                }
                validationSchema={validate}
                onSubmit={
                    (values) => {
                        users.email = values.email;
                        users.password = values.password;
                        authenticate(users);
                    }
                }
            >{
                    formik =>
                    (<Form>
                        <ThemeProvider theme={theme}>
                            <Stack spacing={2}>
                                <h2 style={{ textAlign: 'center' }}>LOG IN</h2>
                                <InputBox type="email" name="email" label="Email"></InputBox>
                                <InputBox type="password" name="password" label="Password"></InputBox>
                                <Button type='submit' variant='contained' >Login</Button>
                                <p style={{ color: 'red' }}>{message}</p>
                                <Stack direction='row-reverse' spacing={2} justifyItems='center'>

                                    <Button variant="contained" size="small"
                                        onClick={() => { history.push('/register') }}>
                                        Register</Button>
                                    <Typography>Not register?</Typography>

                                </Stack>
                            </Stack>

                        </ThemeProvider>

                    </Form>)

            }
            </Formik>
        </div>
    );
}

export default LoginForm;
