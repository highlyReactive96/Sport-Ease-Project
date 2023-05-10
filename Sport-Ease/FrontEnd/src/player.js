import { useEffect, useState } from "react";

function Player() {

    var [validuser, setValiduser] = useState(null)
    var token = sessionStorage.getItem("Token")

    useEffect(() => {
        setValiduser(sessionStorage.getItem("userName"))
    },[])

    return (
        <h1>Hello {validuser}</h1>
    );
}

export default Player;