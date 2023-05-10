const SaveBills = function(file,name) {
    let fileName = name+".png"
    let path = __dirname+"\\uploads\\"+fileName
    
    file.mv(path,(err)=>{
        if(err)
        {
            return "";
        }
    })
    return fileName
}

module.exports = SaveBills;