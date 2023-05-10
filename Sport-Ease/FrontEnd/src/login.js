import '../node_modules/bootstrap/dist/css/bootstrap.css'
import LoginForm from "./components/loginForm"; 

function Login() {
    return (
        <>
                <div className = "row">
                    {/* <div className = "col-sm-8">
                        <Description></Description>
                    </div> */}
                    <div className = "col-sm-4">
                        <LoginForm></LoginForm>
                    </div>
                </div>
        </>
    );
}

export default Login;