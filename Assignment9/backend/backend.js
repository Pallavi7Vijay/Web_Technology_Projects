import axios, * as others from "axios";
import express from "express";
import path from "path";
import cors from "cors";

import { fileURLToPath } from "url";
'use strict';

const app = express()
app.use(cors());


const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const publicPath = path.join(__dirname, "/dist/business-project");
app.use(express.static(publicPath));


const api_key = "vb0IACB4aq-fQx0vPAbag6kXK5dY8AaO1mEUw2OeeApki-_CxA9KDrpPKrkuXCRpm8gbARM8V3q6ZcMXUjaA9j-1toWJ_v2qRDeeEN6E6e3-wXLfe0F1oRMN1eI5Y3Yx"


app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname + "/dist/business-project/index.html"));
});
app.get('/search', (req, res) => {
    res.sendFile(path.join(__dirname + "/dist/business-project/index.html"));
});
app.get('/booking', (req, res) => {
    res.sendFile(path.join(__dirname + "/dist/business-project/index.html"));
});
app.set('trust proxy', true);

app.get('/submit', (req, res) => {
    let params1;

    if(req.query.ad=='true'){
        params1= {
            categories: req.query.category,
            term: req.query.keyword,
            radius: parseInt(req.query.distance),
            latitude: req.query.latitude.toString(),
            longitude: req.query.longitude.toString()
     } 


    }else{
        params1= {
            location: req.query.location,
            categories: req.query.category,
            term: req.query.keyword,
            radius: parseInt(req.query.distance)
     }
     console.log(params1)
    }
    axios.get("https://api.yelp.com/v3/businesses/search", {
        headers: {
            Authorization: `Bearer ${api_key}`
       },params: params1
    }).then((data) => {
        console.log(data)
        let json1 = JSON.stringify(data.data.businesses.splice(10) || [])
        res.send(json1);
    })        
})


app.get("/cardDetails", (req,res)=>{
    axios.get("https://api.yelp.com/v3/businesses/"+req.query.id, {
        headers: {
            Authorization: `Bearer ${api_key}`
       }
    }).then((data) => {  
        let json1 =data.data
        axios.get("https://api.yelp.com/v3/businesses/"+req.query.id+"/reviews", {
            headers: {
                Authorization: `Bearer ${api_key}`
           }
        }).then((data)=>{
            let json2 = data.data
            let obj = {"cardInfo":json1, "reviews":json2}
            res.send(JSON.stringify(obj));

        })
    })   
})

app.get("/keywordSuggest", (req,res)=>{
    axios.get('https://api.yelp.com/v3/autocomplete?text='+req.query.key, {
        headers: {
            Authorization: `Bearer ${api_key}`
       }
    }).then((data) => {  
       let json1 =data.data
            res.send(JSON.stringify(json1));
        })
    })

app.listen(8080, () => console.log('Example app listening on port 8080!'))