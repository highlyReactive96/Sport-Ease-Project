import { Box, Button, createTheme, Stack, ThemeProvider} from '@mui/material';
import axios from 'axios';
import { Form, Formik } from 'formik';
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import * as Yup from 'yup'
import InputBox from './inputBox';

function RegisterOwner() {

    var theme = createTheme({
        typography: {
            htmlFontSize: 10
        }
    })

    var academyOwner = {
        ownerName: "",
        mobileNo: "",
        email: "",
        ownerPhoto: "",
        ownerAddress: "",
        aadharNo: "",
        gender: "",
        academyList: []
    }

    var [message, setMessage] = useState("")
    var [successMessage, setSuccessMessage] = useState("")
    var history = useHistory()
    const token = sessionStorage.Token
    

    var registerOwner = (owner) => {
        axios.post("http://localhost:9090/owner/addOwner",owner)
            .then((result)=>{
                if(result.data === "owner added successfully")
                {
                    setSuccessMessage("Register Success.. You will be redirected to login page")
                    setTimeout(() => {
                        history.push("/")
                    }, 3000);
                }
                else
                {
                    setMessage(result.data);
                }
                
            })
            .catch(()=>{
                console.log(owner);
                alert("Server down");
            })
    }

    var validate = Yup.object({
        ownerName : Yup.string().max(50, "Must be 50 characters or less")
            .required('Owner name is Required'),
        mobileNo : Yup.string().max(10, "Must be 10 characters or less")
                    .required('Mobile number is Required'),
        email : Yup.string().max(50, "Must be 50 characters or less")
            .email('Email is invalid').required('Email is Required'),
        ownerPhoto : Yup.string().max(50, "Must be 50 characters or less")
                        .required('Owner photo is Required'),
        ownerAddress : Yup.string().max(100, "Must be 100 characters or less"),
        aadharNo : Yup.string().max(12, "Must be 12 characters or less")
                        .required('Aadhar number is Required'),
        gender : Yup.string().required('Gender is required')
    })

    return (
        <Formik
            initialValues={
                {
                    ownerName : "",
                    mobileNo : "",
                    email : "",
                    ownerPhoto : "",
                    ownerAddress : "",
                    aadharNo : "",
                    gender : ""
                }
            }
            validationSchema = {validate}
            onSubmit = { values=>{
                    academyOwner.ownerName = values.ownerName
                    academyOwner.mobileNo = values.mobileNo
                    academyOwner.email = values.email
                    academyOwner.ownerPhoto = values.ownerPhoto
                    academyOwner.ownerAddress = values.ownerAddress
                    academyOwner.aadharNo = values.aadharNo
                    academyOwner.gender = values.gender
                    registerOwner(academyOwner)
                }
            }
        >
            {
                formik => (
                    <Form>
                        <ThemeProvider theme = {theme}>
                            <Stack spacing={2}>
                                <h2 style={{textAlign : 'center'}}>REGISTER AS OWNER</h2>
                                <InputBox type="text" name="ownerName" label='ownerName'></InputBox>
                                <InputBox type="text" name="mobileNo" label='Mobile No'></InputBox>
<InputBox type="email" name="email" label='Email'></InputBox>
<InputBox type="text" name="ownerPhoto" label='Owner Photo'></InputBox>
<InputBox type="text" name="ownerAddress" label='Owner Address'></InputBox>
<InputBox type="text" name="aadharNo" label='Aadhar No'></InputBox>
<div className="radio">
<h3>Gender</h3>
<div className="radioBtn">
<label>
<input type="radio" name="gender" value="Male" checked={formik.values.gender === "Male"} onChange={() => formik.setFieldValue("gender", "Male")}/>
Male
</label>
<label>
<input type="radio" name="gender" value="Female" checked={formik.values.gender === "Female"} onChange={() => formik.setFieldValue("gender", "Female")}/>
Female
</label>
</div>
{formik.errors.gender ? <div className="error">{formik.errors.gender}</div> : null}
</div>
<Button type='submit' variant="contained" size="large" fullWidth={true}>REGISTER</Button>
{message ? <div className="error">{message}</div> : null}
{successMessage ? <div className="success">{successMessage}</div> : null}
</Stack>
</ThemeProvider>
</Form>
)
}
</Formik>
)
}

export default RegisterOwner;
