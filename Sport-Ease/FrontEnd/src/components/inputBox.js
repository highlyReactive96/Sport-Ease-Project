import { TextField } from "@mui/material";
import { ErrorMessage, useField } from "formik";

function InputBox({label,...props}) {

    const [field,meta] = useField(props)

    return ( <>
                <TextField {...field} {...props} label={label} variant="outlined" 
                                size='small' required
                                className={`${meta.touched && meta.error && 'is-invalid'}`}/>
                <ErrorMessage component="div" name={field.name} className="error"></ErrorMessage>
             </>
        
     );
}

export default InputBox;