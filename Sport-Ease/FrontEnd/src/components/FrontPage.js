import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import BackgroundSlider from './BackgroundSlider'
import Footer from './Footer'
// import Review from './Review'
const FrontPage = () => {
  return (
    <>
    <div> 
    <BackgroundSlider/>
    </div>
<div>
    <Footer/>
</div>
    </>
  )
}

export default FrontPage
