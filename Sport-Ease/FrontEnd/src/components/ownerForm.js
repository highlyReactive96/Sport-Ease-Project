import * as React from 'react';
import { Stack } from '@mui/system';
import { useState } from 'react';
import { Button, MenuItem} from '@mui/material';
import FormData from 'form-data';
import axios from 'axios';
import { Form, Formik } from 'formik';
import InputBox from './inputBox';
import * as Yup from 'yup'
import RegularExpression from '../utils/regx';
import { useHistory } from 'react-router-dom';

function AddOwner(props) {

    var owner = {
        ownerName : "",
        mobileNo : "",
        email : "",
        ownerAddress : "",
        aadharNo : "",
        gender: "",
        ownerPhoto: ""
    }

    var history = useHistory();

    var [message,setMessage] = useState("")

    const token = sessionStorage.getItem("Token")
    const [files,setFiles] = useState({
        photo : null,
        aadharCard : null,
        aggrementLetter : null
    });

    var validate = Yup.object({
        firstName: Yup.string().max(25, "Must be 25 characters or less")
                    .matches(RegularExpression().nameRegx,
                    'Name can only contain letters.')
                    .required('First Name is Required'),
        lastName : Yup.string().max(25, "Must be 25 characters or less")
                    .matches(RegularExpression().nameRegx,
                    'Name can only contain letters.')
                    .required('Last Name is Required'),
        email : Yup.string().max(50, "Must be 50 characters or less")
                .email('Email is invalid').required('Email is Required'),
        mobileNo : Yup.string().matches(RegularExpression().phoneRegx,'Invalid phone no')
                    .required('Mobile no is Required'),
        ownerAddress : Yup.string().max(100,"Must be 500 characters or less")
                    .required('Address is Required'),
        gender : Yup.string().required('Gender is Required'),
        aadharNo : Yup.string().matches(RegularExpression().addharRegx,"Invalid addhar no")
                    .required("Addhar is requried")
                    
    })
    var history = useHistory()
    var [message, setmessage] = useState("")
    var [successMessage, setSuccessMessage] = useState("")

    var handleFileChange = (args) => {
        let f=args.target.files[0];
        var changeFiles = {...files}
        changeFiles[args.target.name] = f
        setFiles(changeFiles)
    }
    var uploadFiles = (email,addOwnerFun)=>{
        let formData = new FormData()
        formData.append("owner_photo",files.photo)
        axios.post("http://localhost:4000/owner/"+email,formData)
        .then(result=>{
            const paths = result.data;
            owner.ownerPhoto = paths.pathOp
            owner.aadharPhoto = paths.pathOa
            owner.agreementLetter = paths.pathOal
            addOwnerFun(owner)
        })
        .catch(err=>{
            console.log(err)
        })
    } 

    var addOwner = (o)=>{
        debugger
        console.log(o)
        axios.post("http://localhost:8080/admin/addowner",o,{
            headers : {
                "Authorization" : "Bearer "+token
            }
        })
        .then((result)=>{
            if(result.data == "OK")
            {
                history.go(0)
                props.exit(false)
            }
            else
            {
                setMessage(result.data)
            }
        })
        .catch((err)=>{
            setMessage(err.message)
        })
    }

    return ( 
        <Formik
            initialValues={
                {firstName : "",
                lastName : "",
                mobileNo : "",
                email : "",
                ownerAddress : "",
                aadharNo : "",
                gender: "",}
            }
            validationSchema={validate} 
            onSubmit={values=>{
                owner.ownerName = values.firstName + " " + values.lastName
                owner.mobileNo = values.mobileNo
                owner.email = values.email
                owner.ownerAddress = values.ownerAddress
                owner.gender = values.gender
                owner.aadharNo = values.aadharNo
                uploadFiles(owner.email,addOwner)
             }} 
        >
        {
            formik => (
                        <Form >
                            <Stack spacing={2}>
                            <InputBox type="text" name="firstName" label="First Name"></InputBox>

                            <InputBox type="text" name="lastName" label="Last Name"></InputBox>

                            <InputBox type="text" name="email" label="Email"></InputBox>
                        
                            <InputBox type="text" name="mobileNo" label="Mobile No"></InputBox>

                            <InputBox type="text" name="aadharNo" label="Addhar No"></InputBox>

                            <InputBox select label="Select Gender" defaultValue="MALE" name="gender">
                                    <MenuItem value="MALE">Male</MenuItem>
                                    <MenuItem value="FEMALE">Female</MenuItem>
                                    <MenuItem value="OTHER">Other</MenuItem>
                            </InputBox> 

                            <InputBox type="text" name="ownerAddress" label='Address' multiline rows={3}></InputBox>
                        
                        </Stack>
                        <br></br>
                        Upload documents
                        <br></br>
                        <br></br>
                        
                        <Stack direction='row' spacing={2}>
                                <input type="file"
                                        name="photo"
                                        onChange={handleFileChange}
                                        label="Upload"/><br/>

                                <input type="file"
                                        name="aadharCard"
                                        onChange={handleFileChange}
                                        label="Upload"/>
                                        
                                <input type="file"
                                        name="aggrementLetter"
                                        onChange={handleFileChange}
                                        label="Upload"/><br></br>
                        </Stack>
                        <br></br>
                        <Button type='submit' variant='contained'>Submit</Button>
                </Form>
            )
        }
        </Formik>
        
     );
}

export default AddOwner;