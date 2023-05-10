import { Paper } from "@mui/material";
import Carousel from "react-material-ui-carousel";


function CarouselTag(props) {

    const ImgList = props.photolist;
    // const imgState=
    // const handleImgSrc=(item)=>{
    // var imgsrc="E:\FrontEnd\src\images\"+item.value
    // }

    return ( 
        <Carousel>
                            {
                                ImgList.map((item)=>{
                                    return(
                                            <Paper>
                                                <img src={item.photo} height='200px' width='100%' alt={"photo"} ></img>
                                            </Paper>
                                          )
                                })
                            }
                            
        </Carousel>
     );
}

export default CarouselTag;