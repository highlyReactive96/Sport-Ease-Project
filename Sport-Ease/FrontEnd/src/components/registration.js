
import { Box, Button, createTheme, Stack, ThemeProvider} from '@mui/material';
import axios from 'axios';
import { Form, Formik } from 'formik';
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import * as Yup from 'yup'
import InputBox from './inputBox';

function Registration() {

    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })

    var users = {
                    email: "",
                    password: ""
                }

    var [message, setmessage] = useState("")
    var [successMessage, setSuccessMessage] = useState("")
    var history = useHistory()
    const token = sessionStorage.Token
    

    var RegisterUser = (u) => {
            axios.post("http://localhost:9090/user/addUser",u)
            .then((result)=>{
                if(result.data == "user added successfully")
                {
                    setSuccessMessage("Register Success.. You will be redirected to login page")
                    setTimeout(() => {
                        history.push("/")
                    }, 3000);
                }
                else
                {
                    setmessage(result.data);
                }
                
            })
            .catch(()=>{
                console.log(u);
                alert("Server down");
            })
    }

    var validate = Yup.object({
        email : Yup.string().max(50, "Must be 50 characters or less")
            .email('Email is invalid').required('Email is Required'),
        password : Yup.string().max(20, "Must be 20 characters or less")
                    .required('Password is Required'),
        confirmPassword : Yup.string()
                            .oneOf([Yup.ref('password'),null],'Password should be matching')
                            .required('Confirm Password is required')
    })

    return (
        <Formik
            initialValues={
                {
                    email : "",
                    password : "",
                    confirmPassword : ""
                }
            }
            validationSchema = {validate}
            onSubmit = { values=>{
                    users.email = values.email
                    users.password = values.password
                    RegisterUser(users)
                }
            }
        >
            {
                formik => (
                    <Form>
                        <ThemeProvider theme = {theme}>
                            <Stack spacing={2}>
                                <h2 style={{textAlign : 'center'}}>REGISTER</h2>
                                <InputBox type="email" name="email" label='Email'></InputBox>
                                <InputBox type="password" name="password" label="Password"></InputBox>
                                <InputBox type="password" name="confirmPassword" label="Confirm Password"></InputBox>
                                {/* <InputBox type="role" name="Role" label="Role"></InputBox> */}
                                <p style={{color:'red'}}>{message}</p>
                                <p style={{color:'green'}}>{successMessage}</p>
                                <Stack direction='row' spacing={2}>
                                    <Button variant="contained" 
                                        onClick={()=>{history.push('/')}} >
                                        Back</Button>
                                    <Button type='submit' variant='contained' >Register</Button> 
                                    <Button onClick={()=>{history.push('/registerOwner')}} variant='contained' >Register as Owner</Button>  
                                </Stack>
                            </Stack>
                        </ThemeProvider>
                    </Form>
                )
            }
        </Formik>
    );
}

export default Registration;