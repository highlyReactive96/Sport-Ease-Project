const SaveAcademyPhoto = function(file,name) {
    let fileName = name+".png"
    let path = __dirname+"\\uploads\\"+fileName
    
    console.log(file)
    console.log(name)

    file.mv(path,(err)=>{
        if(err)
        {
            return "";
        }
    })
    return 'uploads/'+fileName
}

module.exports = SaveAcademyPhoto;