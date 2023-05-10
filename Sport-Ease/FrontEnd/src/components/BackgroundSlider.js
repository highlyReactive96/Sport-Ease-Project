import React, { useEffect, useState } from 'react'
import ImageSlide from './ImageSlide'
import './BackgroundSlider.css'

const BackgroundSlider = () => {
  const [currentState,setCurrentState] = useState(0);
  useEffect(()=>{
    const timer = setTimeout(()=>{
        if(currentState===4){
          setCurrentState(0)
        }
        else{
          setCurrentState(currentState+1)
        }
    },2000)
      return ()=>clearTimeout(timer);
  },[currentState])
  const bgImageStyle ={
    backgroundImage : `url(${ImageSlide[currentState].url})`,
    backgroundPosition:'center',
    backgroundSize:'cover',
    height:'100%'
  }
  const goToNext=(currentState)=>{
    setCurrentState(currentState);
  }

  return (
    <>
        <div className='container-style'>
          <div style={bgImageStyle}></div>
          <div className='transparent-background'></div>
          <div className='description'>
            <div>
              <h1>{ImageSlide[currentState].title}</h1>
              <br/><br/><br/>
              <p><b>{ImageSlide[currentState].body}</b></p>
            </div>
            <div className='carousel-boult'>
              {
                ImageSlide.map((ImageSlide,currentState)=>(
                  <span key={currentState} onClick={()=>goToNext(currentState)}></span>
                ))
              }
            </div>
          </div>
          </div>
    </>
  )
}

export default BackgroundSlider