const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const bcrypt = require('bcryptjs');
const config = require('./config');
const mysql = require('mysql');
const base64Img = require('base64-img');
const jwt = require('jsonwebtoken');
const pool = mysql.createPool(config.DATABASE_CONFIG);
const app = express();
const path = require('path');
const logger = require('./winston');

const image = path.resolve('./images');
const upload = path.resolve('./original_images');
const tagged_images = path.resolve('./tagged_images');

// const APIRoutes = require('./APIRoutes/api');
// const fileUploadRouter = require('./APIRoutes/fileupload');
// const fileRepoRoutes = require('./APIRoutes/filerepo');
// const verifyToken = require('./auth');

app.use(cors({
    origin: [
        "http://localhost:4200",
        "http://localhost:3000",
        "http://10.10.10.57:4000"
    ], credentials: true
}));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}));

//uncomment/comment if facing cors issue.
app.use(function(req, res, next) {
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "*");
    res.setHeader("Access-Control-Allow-Headers", 'X-Requested-With, content-type, auth-token');
    res.setHeader('Access-Control-Allow-Credentials', true);
    next();
});

// app.use('/api', APIRoutes);
// app.use('/fileupload', verifyToken , fileUploadRouter);
// app.use('/filerepo', fileRepoRoutes);
// app.use('/original_images', express.static(image));
// app.use('/original_images', express.static(upload));
// app.use('/tagged_images', express.static(tagged_images));

app.get('/test', (req, res) => {
    res.send(req.user);
});

app.post('/user/register', (req, res) => {
    console.log("req.body: ", req.body);
    let { studentId, studentIdError, studentName, studentNameError, examType, examTypeError, remarks, remarksError, sub1, sub2, sub3, sub4, total, percentage } = req.body;
    console.log("studentId: ", studentId);
    console.log("examType: ", examType);
    console.log("studentName: ", studentName);
    console.log("remarks: ", remarks);
    console.log("sub1: ", sub1);
    console.log("sub2: ", sub2);
    console.log("sub3: ", sub3);
    console.log("sub4: ", sub4);

    total = Number(sub1) + Number(sub2) + Number(sub3) + Number(sub4);
    percentage = (total/400)*100;
    console.log("total & percentage: ", total, percentage);
    
    let query = `select * from students where student_id = "${studentId}"`;
    console.log("query: ", query);
    pool.query(query, (err, result0) => {
        if(err) {
            console.log(err);
            return res.status(500).send(err);
        } else {
            console.log("result0: ", result0);
            if(result0.length === 0) {
                let insertStatement = `insert into students(student_id, student_name, exam_type, sub1, sub2, sub3, sub4, total, percentage, remarks) values("${studentId}", "${studentName}", "${examType}", ${sub1}, ${sub2}, ${sub3}, ${sub4}, ${total}, ${percentage}, "${remarks}")`;
                console.log("Insert statement to register new user: " + insertStatement);
                pool.query(insertStatement, (err, result1) => {
                    if(err) {
                        console.log(err);
                        return res.status(500).send(err);
                    } else {
                        console.log("result1: ", result1);
                        return res.status(201).json(studentName + " has been created.");
                    }
                });
            } else {
                console.log("ID already exists.");
                return res.status(400).send('ID already exists');
            }
        }
    });

});

// app.post('/user/register', (req, res) => {
//     let { email, exam_type, name, percentage, remarks, sub1, sub2, sub3, sub4, total } = req.body.userdata;
//     console.log("email: ", email);
//     console.log("exam_type: ", exam_type);
//     console.log("name: ", name);
//     console.log("remarks: ", remarks);
//     console.log("sub1: ", sub1);
//     console.log("sub2: ", sub2);
//     console.log("sub3: ", sub3);
//     console.log("sub4: ", sub4);

//     total = sub1 + sub2 + sub3 + sub4;
//     percentage = (total/400)*100;
//     console.log("total & percentage: ", total, percentage);
    
//     let query = `select * from students where student_id = "${email}"`;
//     console.log("query: ", query);
//     pool.query(query, (err, result0) => {
//         if(err) {
//             console.log(err);
//             return res.status(500).send(err);
//         } else {
//             if(result0.length === 0) {
//                 let insertStatement = `insert into students(student_id, student_name, exam_type, sub1, sub2, sub3, sub4, total, percentage, remarks) values("${email}", "${name}", "${exam_type}", ${sub1}, ${sub2}, ${sub3}, ${sub4}, ${total}, ${percentage}, "${remarks}")`;
//                 console.log("Insert statement to register new user: " + insertStatement);
//                 pool.query(insertStatement, (err, result1) => {
//                     if(err) {
//                         console.log(err);
//                         return res.status(500).send(err);
//                     } else {
//                         console.log("result1: ", result1);
//                         return res.status(201).json(name + " has been created.");
//                     }
//                 });
//             } else {
//                 console.log("ID already exists.");
//                 return res.status(400).send('ID already exists');
//             }
//         }
//     });

// });


app.get('/getUsers', (req, res) => {
    let query = `select *from students`;
    console.log("Get Users Route query: " + query);
    pool.query(query ,(err,result)=>{
        if(err){
            console.log(err);
            return res.status(500).send(err);
        } else {
            console.log("Result of Get Users Route Query: " + JSON.stringify(result));
            res.json(result);
        }
    });
})

app.post('/user/updateUserInformation', (req, res) => {
    let { userdata, prevmail } = req.body;
    // userdata.lob = userdata.lob.toUpperCase();
    console.log("Payload to the update user information route: " + req.body);
    userdata.total = userdata.sub1 + userdata.sub2 + userdata.sub3 + userdata.sub4;
    userdata.percentage = (userdata.total/400)*100;
    let updateStmnt = `update students set student_id='${userdata.email}', student_name='${userdata.name}', exam_type='${userdata.exam_type}', sub1=${userdata.sub1}, sub2=${userdata.sub2}, sub3=${userdata.sub3}, sub4=${userdata.sub4}, total=${userdata.total}, percentage=${userdata.percentage}, remarks='${userdata.remarks}' where student_id='${prevmail}'`;
    logger.info("Update Statement for UpdateUserInformation route: " + updateStmnt);
    pool.query(updateStmnt ,(err,result)=>{
        if(err){
            console.log(err);
            return res.status(500).send(err);
        } else {
            console.log("Result of UpdateUserInformation route: " + result);
            res.json("User information updated successfully.");
        }
    }); 
});

app.post('/user/deleteUserInformation', (req, res) => {
    let { userdata, prevmail } = req.body;
    let deleteStmnt = `delete from students where student_id='${prevmail}'`;
    console.log("Delete Statement for deleteUserInformation Route: " + deleteStmnt);
    pool.query(deleteStmnt , (err, result) => {
        if(err){
            console.log(err);
            return res.status(500).send(err);
        } else {
            console.log("Result for deleteUserInformation route: " + JSON.stringify(result));
            res.json("User information deleted successfully.");
        }
    })
});

app.post('/user/editProfile',  (req, res) => {
    let { email, profileinfo } = req.body;
    let salt = bcrypt.genSaltSync(10);
    let hashedPassword = bcrypt.hashSync(profileinfo.password, salt);
    let updatePasswordStmnt = `update users set password='${hashedPassword}' where email='${email}'`;
    logger.info("Update Password Statement: " + updatePasswordStmnt);
    pool.query(updatePasswordStmnt ,(err,result)=>{
        if(err){
            logger.error(err);
            return res.status(500).send(err);
        } else {
            logger.debug("Result for editProfile update statement: " + JSON.stringify(result, null, 4));
            res.json("User Profile Edited Successfully.");
        }
    });
})

app.get('/type', (req,res)=>{
    let query = `select distinct(type) from typestable`;
    logger.info("Query to get the types: " + query);
    pool.query(query ,(err, result)=>{
        if(err){
            logger.error(err);
            return res.status(500).send(err);
        } else {
            logger.debug(JSON.stringify(result, null, 4));
            res.json(result);
        }
    });
})

const PORT = process.env.PORT || config.PORT;
app.listen(PORT, () => console.log(`Application running on PORT ${PORT}`));
