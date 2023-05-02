$(document).ready(function () {
    let form = {}
    var setToDefault = function(){
        form.keyWord = '';
        form.distance = 10;
        form.category = "all";
        form.location = "";
        form.getLocation =  'off';
        form.cords={ lat: '', long: ''}
    }
    setToDefault();
    let getAPIObject = {}
    getAPIObject.method = "GET";
    getAPIObject.header ={}
    getAPIObject.header["Access-Control-Allow-Origin"]= '*'
    getAPIObject.header["Accept"]= 'application/json'
    getAPIObject.header["Content-Type"]= 'application/json'
    let sort = {}
    sort.colName="";
    sort.order = false;
    let isWaiting= false

    //important
    $('select').on('change', (e) =>
     onChange(e)
     );
    $('input').on('change', (e) =>
     onChange(e)
     );
    const onChange = async (e) => {
        form[e.target.id] = e.target.value;
        id = e.target.id
        value = e.target.value;
        if ($('#get-location')['0'].checked && id === 'get-location') {
            $('#submit').prop('disabled', true);
            isWaiting = true;
            await fetch('https://ipinfo.io?token=d86e1ea0fedc63', getAPIObject)
                .then(res => {
                    if (res && res.status === 200)
                        return res.json()
                }
                ).then(data => {
                    form.cords = {}
                    const { loc } = data
                    form.cords.lat = parseFloat(loc.split(',')[0])
                    form.cords.long = parseFloat(loc.split(',')[1])
                })
                .finally(()=>{$('#submit').prop('disabled', false);})
            form['location'] = '';
            form['get-location'] = true
            $('#location').val('');
            $('#location')['0'].disabled = true;
        }
        if (id === 'get-location' && !$('#get-location')['0'].checked) {
            form['get-location'] = false;
            $('#get-location').prop('checked', false);
            $('#location')['0'].disabled = false;
            $('#location').val('');
            form['cords']={long:'',lat:''}
        }
        if (id === 'location' && $('#location').val() != ''&&$('#location').val().length>0) {
            $('#submit').prop('disabled', true);
            isWaiting= true;

            const addressURL = 'https://maps.googleapis.com/maps/api/geocode/json?address=' + value + '&key=' + 'AIzaSyCs1n8Zg39pudq1cOWo17H0BHWTSfFaljo'
            await fetch(addressURL, getAPIObject)
                .then(res => {
                    if (res && res.status === 200)
                        return res.json()
                }
                ).then(data => {
                    if (data.results && data.results.length > 0) {
                        form.cords = {}
                        let x = data.results[0]
                        form.cords.lat = x.geometry.location.lat
                        form.cords.long = x.geometry.location.lng
                    }
                    else {
                        form.cords = {lat: '22.2222',long: '22.22'}
                    }
                })
                .finally(()=>{$('#submit').prop('disabled', false); isWaiting=false;})
        }
    }


    $('#clear').on('click', () => {
        setToDefault();
        businesses = [];
        $('#dCard').empty();$('#keyWord').val('');$('#dTable').empty();$('#distance').val(10);$('#category').val('all');$('#location').val('');$('#location')['0'].disabled = false;$('#get-location').prop('checked', false);
        sort = {}
        sort.colName = "";
        sort.order = false;
    })

    const validate = () => {
        isValid = true;
        if (form.keyWord.length<=0){
            isValid = false
        }
        if (form.category.length<=0){
            isValid = false
        }
        if (form.category.distance==""){
            isValid = false
        }
        if (form.cords.location ==''){
            isValid = false
        }
        return isValid
    }
    const onSubmit = async (e) => {
        setTimeout(()=>{console.log("timeout for 1 second")
        },1000)
        if (validate()) {
            e.preventDefault()
            $('#dCard').empty();
            $('#dTable').empty();
            intDistance = (parseInt(form.distance * 1609.344))
            //important
            let url = 'https://psyched-age-363304.wl.r.appspot.com/getDets?keyWord=' + form.keyWord +'&&category=' + form.category + '&&distance=' + (parseInt(form.distance * 1609.344)) + '&&locationLong=' + form.cords.long  +'&&locationLat=' + form.cords.lat               
            await fetch(url, getAPIObject)
                .then((response) => {
                    return response.json()
                }).then((data) => {
                    if (data && data.businesses) {
                        businesses = [...data.businesses]
                        generateTable(data.businesses);
                    }
                })
        }
        else if(!validate()&& $('#location').val()!==''){
            alert('Entered Location is Invalid')
        }
        return false
    };
    $('#submit').on('click', (e) => onSubmit(e));

    const generateTable = (businesses) => {  
        let tableTag = `<table style="border-collapse: collapse;width: 1136px;background-color: white;text-align: center;" id="tid">`
        let tHead = `<thead class="blue-head" style="color: black;font-weight: bolder;text-align: center;">`
        let td1 = `<td class="item-no">No.</td>`
        let td2 = `<td style= "width: 151px">Image</td>`
        let td3 = `<td class="item-name" id='name'>Business Name</td>`
        let td4 = `<td class="cursorPointer" style= "width: 151px" id='rating'>Rating</td>`
        let td5 = `<td class="cursorPointer" style= "width: 151px"id='distance'>Distance(miles)</td>
        </thead>`                
        tableHead = tableTag + tHead + td1 + td2 + td3 + td4 +td5
        if (businesses.length > 0) {
            let tableBody = ``
            for (let i = 0; i < businesses.length; i++) {
                let dist = (businesses[i].distance * 0.000621371192).toFixed(2)
                let img = businesses[i].image_url.length > 0 ? businesses[i].image_url : ''
                itemHtml =`<tr>
                <td class="item-no"><p>${i + 1}</p>
                </td>`
                imgHtml = `<td><img class='itemImage' alt=${businesses[i].name} src=${img} ></td>`
                businessHtml = `<td class="itemName gray" id=${businesses[i].id}><p id=${businesses[i].id}>${businesses[i].name}</p>
                </td>`
                ratingHtml = `<td style= "width: 151px"><p>${businesses[i].rating}</p>
                </td>`
                distHtml = `<td style= "width: 151px"><p>${dist}</p>
                </td>
                </tr>`
                tableBody += itemHtml+imgHtml+businessHtml+ratingHtml+distHtml
            }
            let tblHTML = tableHead + tableBody + `</table>`;
            $('#dTable').append(tblHTML);
        }
        else {
            $('#dTable').append(`<div style="width: 1136px;border: 1px solid #ddd;text-align: center;color: black;background-color:white;">No Record has been found</div>`);
            businesses=[]
        }
        let y = document.getElementById('dTable')
        y.scrollIntoView();
    }

    $('#dTable').on('click', (e) => {
        const { id } = e.target;
        if (id === 'name' || id === 'rating' || id === 'distance') {
            sorttable(e.target.cellIndex)
        }
        else if (id.length > 0) {
            buildCard(id)
        }
    })

    //Used w3Schools
    function sorttable(n){
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById('tid');
        switching = true;
        dir = "asc"; 
        
        while (switching) {
        switching = false;
        rows = table.rows;
          for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
          
            x = rows[i].getElementsByTagName("td")[n];
            y = rows[i +1].getElementsByTagName("td")[n];
            if (dir == "asc") {
              if (x.innerText.toLowerCase() > y.innerText.toLowerCase()) {
                shouldSwitch= true;
                break;
              }
            } else if (dir == "desc") {
              if (x.innerText.toLowerCase() < y.innerText.toLowerCase()) {
                shouldSwitch = true;
                break;
              }
            }
          }
          if (shouldSwitch) {  
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount ++;      
          } else {
            if (switchcount == 0 && dir == "asc") {
              dir = "desc";
              switching = true;
            }
          }
        }
    }

    const getConcat = (value, seprator) => {
        let str = ''
        for (i in value) {
            let temp = (value[i].title ? value[i].title : value[i]) + seprator 
            str += value[i] ? temp : ''
        }
        return str.slice(0, str.length - 3)
    }

    const constructCard = (data) => {
        let hours = data.hours;
        let location = data.location;
        let transactions = data.transactions;
        let categories = data.categories;
        let display_phone = data.display_phone;
        let price = data.price;
        let url = data.url;
        let name = data.name;
        let photos = data.photos;
        let price1 = undefined;
        if(price != undefined)
            price1 = price.length > 0 && price ? price : false;
            let cardContainer = `<div style="border: 1px solid #D4D4D4;background-color: #FEFFFF;width: 1135px ;margin-top: 121px;">`
            let cardHead = `<div style="text-align: center;font-size: 34px;">`
            let cHeading = `<p style="margin-bottom: 21px;">${name}</p>`
            let cHeaderLine = `<div style="border: 1px solid #E7E7E5;width: 1019px;margin: auto;"></div></div>`
        let cardHeader = cardContainer + cardHead + cHeading + cHeaderLine
        let cBody =`<div class="cBody">`
        let cBlock =  `${(`<div class="cBlock"><p class="bHeader">Status</p><div class="bContent"><span class=${ hours && [0] && ['is_open_now'] ? "sGreen" : "sRed"}>${ hours && [0] && ['is_open_now'] ? "Open Now" : "Closed"}</span></div> </div>`)}`
        let locationD = `${getConcat(location.display_address, '   ') && (`<div class="cBlock"><p class="bHeader">Address</p><div class="bContent"><p class="card-details">${getConcat(location.display_address, '   ')}</p></div></div>`)}`
        let displayPhone = `${display_phone && (`<div class="cBlock"><p class="bHeader">Phone No</p><div class="bContent"><p class="card-details">${display_phone}</p></div></div>`)}`
        let transactions1 = `${getConcat(transactions, ' | ') && (`<div class="cBlock"><p class="bHeader">Transactions Supported</p><div class="bContent"><p class="card-details">${getConcat(transactions, ' | ')}</p></div></div>`)}`
        let price11 = `${price1 && (`<div class="cBlock"><p class="bHeader">Price</p><div class="bContent"><p class="card-details">${price1}</p></div> </div>`)}`
        let url1 = `${url && (`<div class="cBlock"><p class="bHeader">More Info</p><div class="bContent"><a href=${url} target="blank">Yelp</a></div></div>`)}</div>`
        if(price1 != undefined)
            cardBody = cBody + cBlock + locationD + displayPhone + transactions1 + price11 + url1
        else
            cardBody = cBody + cBlock + locationD + displayPhone + transactions1  + url1

        let photo1="Photo 1"
        let photo2="Photo 2"
        let photo3="Photo 3"
        cardImages = photos.length > 0 ? (`   <div class="card-photos">${photos[0] !== undefined && photos[0]?`<div class="image-container"><div class="img-hldr"><img src=${photos[0]} class="cardImage" alt ="image"></div><p style='text-align: center;'>${photo1}</p></div>`:(`<div></div>`)}
                ${photos[1] !== undefined && photos[1]? `<div class="image-container"><div class="img-hldr"><img src=${photos[1]} class="cardImage" alt ="image"></div><p style='text-align: center;'>${photo2}</p></div>`:(`<div></div>`)}
                ${photos[2] !== undefined && photos[2]  ? `<div class="image-container"><div class="img-hldr"><img src=${photos[2]} class="cardImage" alt ="image"></div><p style='text-align: center;'>${photo3}</p></div>`:(`<div></div>`)}</div></div>`) : (`<div></div>`)
        $('#dCard').append(cardHeader + cardBody + cardImages);
        let z = document.getElementById('dCard')
        z.scrollIntoView()
    }
    const buildCard = async (id) => {
        $('#dCard').empty()
        let url = 'https://psyched-age-363304.wl.r.appspot.com/getBusinessDets?id=' + id;
        await fetch(url, getAPIObject).then((response) => {return response.json()}).then((data) => {
            if (data) {
                constructCard(data);
            }})
    }
});