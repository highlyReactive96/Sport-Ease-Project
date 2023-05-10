import '../node_modules/bootstrap/dist/css/bootstrap.css'
import LoginForm from "./components/loginForm"; 
import Registration from './components/registration';
function Register() {
    return (
        <>
                <div className = "row">
                    <div className = "col-sm-8">
                    </div>
                    <div className = "col-sm-4">
                        <Registration></Registration>
                    </div>
                </div>
        </>
    );
}

export default Register;