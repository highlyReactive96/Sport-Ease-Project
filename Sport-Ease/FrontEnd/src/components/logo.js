import logo from '../images/logo.png'

function Logo () {
    return (
        <>
            <div style={{display:'flex',flexWrap : 'wrap', margin: "1%"}}>
                <img src={logo} height={127} style={{display : 'inline-block'}}></img>
                <p style={{display : 'inline-block', marginTop :'3.8%',marginLeft : '1%', fontSize:50}}>Apana Pg</p>
            </div>
        </>
      );
}

export default Logo;