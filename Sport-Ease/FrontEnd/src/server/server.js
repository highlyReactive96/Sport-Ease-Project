const express = require('express')
const app = express()
const cors = require('cors')
const fileUpload = require('express-fileupload')
const SaveFile = require('./saveFiles')
const SaveBills = require('./saveBills')
const SaveEmpPhoto = require('./saveEmpPhoto')
const SavePgPhoto = require('./savePgPhoto')
app.use(express.json())
app.use(cors())
app.use(fileUpload())

var paths = {
    pathOp : "",
    pathOa : "",
    pathOal : ""
}

var pgPhoto = {
    path1 : "",
    path2 : "",
    path3 : ""
}

var billpaths = ""

app.post("/owner/:ownerEmail",(req,res)=>{
    const name = req.params.ownerEmail
    const ownerPhoto = req.files.owner_photo;
    const ownerAddhar = req.files.owner_aadhar;
    const ownerAggrementLetter = req.files.owner_aggrementLetter;
    
    paths.pathOa = SaveFile(ownerPhoto,name+"_ownerAddhar")
    paths.pathOp = SaveFile(ownerAddhar,name+"_ownerPhoto")
    paths.pathOal = SaveFile(ownerAggrementLetter,name+"_ownerAggremen")

    console.log(paths)
    
    res.send(paths)
    
})

app.post("/bill/:id",(req , res)=>{
    const pgId = req.params.id;
    const billPhoto = req.files.billPhoto; 
    billpaths = SaveBills(billPhoto,pgId+"_"+Date.now()+"_bill")
    console.log(billpaths)
    res.send(billpaths)
})

app.post("/emp/:addhar/:pgId",(req , res)=>{
    const pgId = req.params.pgId;
    const addhar = req.params.addhar;
    const empPhoto = req.files.emp_photo;
    var empPhotoPath = SaveEmpPhoto(empPhoto,pgId+"_"+addhar+"_empPhoto")
    console.log(empPhotoPath)
    res.send(empPhotoPath)
})

app.post("/pg/:name",(req , res)=>{
    const name = req.params.name;
    const photo1  = req.files.photo1;
    const photo2  = req.files.photo2;
    const photo3  = req.files.photo3;
    console.log(req.files)

    pgPhoto.path1 = SavePgPhoto(photo1,"photo1_"+name)
    pgPhoto.path2 = SavePgPhoto(photo2,"photo2_"+name)
    pgPhoto.path3 = SavePgPhoto(photo3,"photo3_"+name)
    console.log(pgPhoto)
    res.send(pgPhoto)
})

app.listen((4000),()=>{
    console.log("node server started on 4000")
})