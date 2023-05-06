//Global variable
BASE_URL="http://localhost:8080"
cityId = -1
carMakerId = -1
carModelId = -1
year = -1
colorId =-1
let map;
var isPickupSelected = false
var isDropSelected = false
let pickLocation = {};
let dropLocation = {}

//validation
var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
var letterNumber = /^[0-9a-zA-Z]+$/;
var letters = /^[a-zA-Z]+$/;
var Numbers = /^\d{11}$/;
var nationalIdRegex = /^\d{14}$/;
var driverLicenceRegex = /^\d{7}$/;
var vehicleLicenceRegex = /^\d{9}$/;
var password=  /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;



//Navigation of buttons
function navigateToDriverLogIn(){
  window.location.href = "login-driver.html";
}

function navigateToRiderLogIn(){
  window.location.href = "login-rider.html";
}

// header
function logButton() {
    window.location.href = "login.html";
}

function signButton() {
    window.location.href = "signup-option.html";
}



//Account Sign in --http request
function signIn(emailValue,passwordValue){
      $.ajax({ // request
        url: BASE_URL+"/sign_in",
        type: 'POST',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
            'Content-Type' : "application/json"
        },
        //put data user entered in json file to send it to backend then database
        data: JSON.stringify({
            //key: value
            email: emailValue,
            password: passwordValue
        })
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to profile
        //console.log(JSON.stringify(data));
        console.log(JSON.stringify(data.driver));
        // save user id in browser data to load its data
        localStorage.setItem('user_id',data.id);
        console.log(localStorage.getItem('user_id'));
        //check the type of user to navigate to his/her profile
        if(data.type === "driver"){
            //navigate to driver profile
            if(data.driver === null){
                // navigate user to create driver account
                window.location.assign('signup-driver.html', "_self")
            }else if(data.driver.vehicle === null){
                // navigate user to create vehicle
                window.location.assign('create-vehicle.html', "_self")
            }else{
                // navigate user to profile page
                window.location.assign('rider-wallet.html', "_self")
            }
        }else{
             //navigate to rider profile
            if(data.rider === null){
                // navigate user to create driver account
                window.location.assign('signup-rider.html', "_self")
            }else{
                // navigate user to profile page
             window.location.assign('takeride.html')
            }
        }

    }).fail(function(error){ //Return backend validation errors
            alert(error.responseJSON.error);
            console.log(JSON.stringify(error.responseJSON));
    });
}

function getAccountByUserId(){
    $.ajax({
        url: BASE_URL+"/accounts/"+localStorage.getItem('user_id'),
        type: 'GET',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
        }
    }).then(function(data, status, jqxhr){
        // Update UI

        updateProfileUi(data);


    }).fail(function(error){
    console.log(JSON.stringify(error));
    })
}

function updateProfileUi(profile){
    // declare ui elements
    const balanceView = document.getElementById('wallet')
    if(profile.driver !== null){
       balanceView.innerHTML = "EGP "+profile.driver.wallet.balance
    }
    if(profile.rider !== null){
        balanceView.innerHTML = "EGP "+profile.rider.wallet.balance
    }
}


function signUp(object){
      $.ajax({ // request
        url: BASE_URL+"/sign_up",
        type: 'POST',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
            'Content-Type' : "application/json"
        },
        data: JSON.stringify(object)
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to create rider and driver profile
        console.log(JSON.stringify(data));
        // save user id in browser data
        localStorage.setItem('user_id',data.id);
        console.log(localStorage.getItem('user_id'));
        if(data.type === "driver"){
            //navigate to create driver profile
            window.location.assign('signup-driver.html', "_self")
        }else{
             //navigate to create rider profile

             window.location.assign('signup-rider.html')

        }

    }).fail(function(error){
        console.log(error.responseJSON.error);
        alert(JSON.stringify(error.responseJSON.error));
    });
}

function getCities(){
      $.ajax({ // request
            url: BASE_URL+"/cities",
            type: 'GET',
            headers:{
                "Access-Control-Allow-Origin": '*' // recommended
            }
        }).then(function(data, status, jqxhr) { // response
            // Update UI
            select = document.getElementById('city');
            cityId = data[0].id;
            for (var i = 0; i<data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].cityName;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
        });
}

function onCitySelectItem(event){
    cityId = event.target.value;
    console.log(cityId);
}

function getCarMakers(){
      $.ajax({ // request
            url: BASE_URL+"/carmakers",
            type: 'GET',
            headers:{
                "Access-Control-Allow-Origin": '*' // recommended
            }
        }).then(function(data, status, jqxhr) { // response
            // Update UI
            select = document.getElementById('car_maker');
            const length = data.length
            if(length > 0){
                carMakerId = data[0].id;
                getCarModels()
            }
            for (var i = 0; i<data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].maker;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
        });
}


function onCarMakerSelectItem(event){
    carMakerId = event.target.value;
    getCarModels()
}

function removeAll(selectBox) {

    while (selectBox.options.length > 0) {
        selectBox.remove(0);
    }
}

function getCarModels(){
      $.ajax({ // request
            url: BASE_URL+"/car_model/"+carMakerId,
            type: 'GET',
            headers:{
                "Access-Control-Allow-Origin": '*' // recommended
            }
        }).then(function(data, status, jqxhr) { // response
            // Update UI
            select = document.getElementById('model');
            const length = data.length
            if(length > 0){
                removeAll(select);
                carModelId = data[0].id;
            }
            for (var i = 0; i<data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].name;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
        });
}


function onCarModelSelectItem(event){
    carModelId = event.target.value;
    console.log(carModelId);
}


function getYears(){
      $.ajax({ // request
            url: BASE_URL+"/release_years",
            type: 'GET',
            headers:{
                "Access-Control-Allow-Origin": '*' // recommended
            }
        }).then(function(data, status, jqxhr) { // response
            // Update UI
            select = document.getElementById('year');
            year = data[0].year;
            for (var i = 0; i<data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].year;
                opt.innerHTML = data[i].year;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
        });
}

function onColorSelectItem(event){
    color = event.target.value;
    console.log(color);
}



function getColors(){
      $.ajax({ // request
            url: BASE_URL+"/colors",
            type: 'GET',
            headers:{
                "Access-Control-Allow-Origin": '*' // recommended
            }
        }).then(function(data, status, jqxhr) { // response
            // Update UI
            select = document.getElementById('color');
            colorId = data[0].id;
            for (var i = 0; i<data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].color;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
        });
}

function onColorSelectItem(event){
    year = event.target.value;
    console.log(year);
}


function createRiderProfile(object){
      $.ajax({ // request
        url: BASE_URL+"/rider",
        type: 'POST',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
            'Content-Type' : "application/json"
        },
        data: JSON.stringify(object)
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to create rider and driver profile
        console.log(JSON.stringify(data));
            //navigate to create driver profile
            window.location.assign('takeride.html', "_self")
    }).fail(function(error){
        console.log(JSON.stringify(error.responseJSON.error));
        alert(JSON.stringify(error.responseJSON.error));
    });
}

function createDriverProfile(object){
      $.ajax({ // request
        url: BASE_URL+"/driver",
        type: 'POST',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
            'Content-Type' : "application/json"
        },
        data: JSON.stringify(object)
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to create rider and driver profile
        console.log(JSON.stringify(data));
            //navigate to create driver profile
            window.location.assign('create-vehicle.html', "_self")
    }).fail(function(error){
        console.log(error.responseJSON.error);
        alert(JSON.stringify(error.responseJSON.error));
    });
}


function createVehicleProfile(object){
      $.ajax({ // request
        url: BASE_URL+"/vehicle",
        type: 'POST',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
            'Content-Type' : "application/json"
        },
        data: JSON.stringify(object)
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to create rider and driver profile
        console.log(JSON.stringify(data));
        //navigate to create driver profile
        window.location.assign('rider-wallet.html', "_self")
    }).fail(function(error){
        console.log(error.responseJSON.error);
        alert(JSON.stringify(error.responseJSON.error));
    });
}


function createTrip(object){
      $.ajax({ // request
        url: BASE_URL+"/trip",
        type: 'POST',
        headers:{
            "Access-Control-Allow-Origin": '*', // recommended
            'Content-Type' : "application/json"
        },
        data: JSON.stringify(object)
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to create rider and driver profile
        console.log(JSON.stringify(data));
            //navigate to create driver profile
            window.location.assign('waiting_Request.html', "_self")
    }).fail(function(error){
        console.log(JSON.stringify(error.responseJSON.error));
        alert(JSON.stringify(error.responseJSON.error));
    });
}


function driverSignIn(){
    /***
    * 1. get email and password from input
    * 2. make request
    **/

    const email = document.getElementById('email_driver') // view element
    const password = document.getElementById('pass_driver') // view element

    if(email.value === ""){
                alert("Please enter your email.")
                return;
             }

            if(password.value === ""){
                alert("Please enter your Password.")
                return;
            }

    signIn(email.value,password.value)
}

function riderSignIn(){
    /***
        * 1. get email and password from input
        * 2. make request
        **/

        const email = document.getElementById('email_rider') // view element
        const password = document.getElementById('pass_rider') // view element
        if(email.value === ""){
            alert("Please enter your email.")
            return;
         }

        if(password.value === ""){
            alert("Please enter your Password.")
            return;
        }
        signIn(email.value,password.value)
}



function attemptCreateRiderAccount(){
    /**
    * get values from html fields
    * check for values validation
    * show errors if founded
    * if all inputs valid make an api request
    **/
    const email = document.getElementById("create_rider_email")
    const password = document.getElementById("create_Rider_password")
    const repeatPassword = document.getElementById("create_Rider_RepeatPassword")
    if (email.value === "")
    {
        alert("Please, Enter your mail")
        return;
    }else if(!email.value.match(emailFormat)){
         alert("Invalid Email Format.")
         return;
    }
    if (password.value.length < 8 || password.value.length > 20 || !password.value.match(letterNumber)){
            alert("Your password should Contain letters, numbers only and at least 8 chars.")
            return;
    }
    if(password.value === ""){
        alert("Please enter your Password.")
        return;
    } else if(password.value != repeatPassword.value){
        alert("Your password doesn't match.")
        return;
    }

    const json = {
        email: email.value,
        password: password.value,
        type: "rider"
    }

    signUp(json);
}


function attemptCreateDriverAccount(){
    /**
    * get values from html fields
    * check for values validation
    * show errors if founded
    * if all inputs valid make an api request
    **/
    const email = document.getElementById("create_driver_email")
    const password = document.getElementById("create_driver_password")
    const repeatPassword = document.getElementById("create_driver_password-repeat")
    var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (email.value === "")
    {
        alert("Please, Enter your mail")
        return;
    }
    else if(!emailFormat.test(email.value)){
         alert("Invalid Email Format.")
         return;
    }
    if (password.value.length < 8 || password.value.length > 20 || !password.value.match(letterNumber)){
        alert("Your password should Contain letters, numbers only at least 8 chars")
        return;
    }
    if(password.value === ""){
        alert("Please enter your Password.")
        return;
    } else if(password.value != repeatPassword.value){
        alert("Your password doesn't match.")
        return;
    }

    const json = {
        email: email.value,
        password: password.value,
        type: "driver"
    }

    signUp(json);
}

function attemptCreateRiderProfile(){
    /**
    * get values from html fields
    * check for values validation
    * show errors if founded
    * if all inputs valid make an api request
    **/
    const firstName = document.getElementById("rider-first-name")
    const lastName = document.getElementById("rider-last-name")
    const phoneNumber = document.getElementById("rider-phone-number")
    const birthDate = document.getElementById("rider-birthday")
    let currentDate = new Date();
    let birthdate = new Date(birthDate.value);
    let diff = new Date(currentDate - birthdate);
    let age = Math.abs(diff.getUTCFullYear() - 1970);
    if (firstName.value === "" || lastName.value === ""){
        alert("Please, Enter your full name")
        return;
    }else if (firstName.value.length > 100 || lastName.value.length > 100 || !firstName.value.match(letters) || !lastName.value.match(letters)){
             alert("Please, Enter your first name or last name only")
             return;
    }
    if (phoneNumber.value === "" ){
            alert("Please, Enter your phone number")
            return;
    }else if (!Numbers.test(phoneNumber.value)){
            alert("Invalid phone number")
            return;
    }
    if (birthDate.value === "" ){
                alert("Please, Enter your birthdate")
                return;
    }else if(age < 10){
            alert("You can't create Account if your age under 10 years")
            return;
    }
    const json = {
            firstName: firstName.value,
            lastName: lastName.value,
            phoneNumber: phoneNumber.value,
            birthdate: birthDate.value,
            cityId: cityId,
            accountId: localStorage.getItem("user_id")
    }
    createRiderProfile(json);

}



function attemptCreateDriverProfile(){
    /**
    * get values from html fields
    * check for values validation
    * show errors if founded
    * if all inputs valid make an api request
    **/
    const firstName = document.getElementById("driver_first_name")
    const lastName = document.getElementById("driver_last_name")
    const nationalId = document.getElementById("driver_national_id")
    const driverLicence = document.getElementById("driver_licence")
    const phoneNumber = document.getElementById("driver_phone_number")
    const birthDate = document.getElementById("driver_birthday")
    const isMale = document.getElementById('male').checked;
    let currentDate = new Date();
    let birthdate = new Date(birthDate.value);
    let diff = new Date(currentDate - birthdate);
    let age = Math.abs(diff.getUTCFullYear() - 1970);


    if (firstName.value === "" || lastName.value === ""){
        alert("Please, Enter your full name")
        return;
    }else if (firstName.value.length > 100 || lastName.length > 100 || !letters.test(firstName.value) || !letters.test(lastName.value)){
        alert("Please, Enter your first name or last name only");
        return;
    }
    if (phoneNumber.value === "" ){
            alert("Please, Enter your phone number")
            return;
    }else if (!Numbers.test(phoneNumber.value)){
            alert("Invalid phone number")
            return;
    }
    if (nationalId.value === "" ){
                alert("Please, Enter your national id")
                return;
    }else if (!nationalIdRegex.test(nationalId.value)){
                alert("Maybe your National id is wrong or is already in use.")
                return;
   }

    if (driverLicence.value === ""){
            alert("Please, Enter your driver licence.")
            return;
    }else if (!driverLicenceRegex.test(driverLicence.value)){
             alert("Maybe your driver licence is wrong or is already in use.")
             return;
       }
    if (birthDate.value === "" ){
                alert("Please, Enter your birthdate")
                return;
    }else if(age < 10){
                 alert("You can't create Account if your age under 18 years")
                 return;
    }

    const json = {
            firstName: firstName.value,
            lastName: lastName.value,
            nationalId: nationalId.value,
            driverLicence: driverLicence.value,
            phoneNumber: phoneNumber.value,
            birthdate: birthDate.value,
            cityId: cityId,
            available: true,
            accountId: localStorage.getItem("user_id"),
            gender: isMale ? 1 : 2
    }
    console.log(JSON.stringify(json));
    createDriverProfile(json);

}

function attemptCreateVehicleAccount(){
    /**
    * get values from html fields
    * check for values validation
    * show errors if founded
    * if all inputs valid make an api request
    **/
    const vehicleLicence = document.getElementById("vehicle-licence")
    const licencePlate = document.getElementById("licence_plate")
    if (vehicleLicence.value === "")
    {
        alert("Please, Enter your vehicle licence number")
        return;
    }
    if (!vehicleLicenceRegex.test(vehicleLicence.value)){
            alert("Invalid vehicle licence number.")
            return;
    }
    if (licencePlate.value === "")
        {
            alert("Please, Enter your licence plate number")
            return;
        }
        if (licencePlate.value.length < 6 ||  licencePlate.value.length > 7 || !licencePlate.value.match(letterNumber) ){
                alert("Invalid licence plate number.")
                return;
        }

    const json = {
        vehicleLicence: vehicleLicence.value,
        licencePlate: licencePlate.value,
        colorId: colorId,
        releaseYear: year,
        carModelId: carModelId,
        carMakerId: carMakerId,
        accountId: localStorage.getItem("user_id")

    }

    createVehicleProfile(json)
}

function bookTrip(){
    /**
    * get values from html fields
    * check for values validation
    * show errors if founded
    * if all inputs valid make an api request
    **/

    if (!isPickupSelected)
    {
        alert("Please, choose your location")
        return;
    }

    if (!isDropSelected)
    {
        alert("Please, choose your destination")
        return;
    }
    if (getDistanceFromLatLonInKm(pickLocation.lat,pickLocation.lng,dropLocation.lat,dropLocation.lng)>50){
        alert("Invalid location")
                return;
    }

        const json = {
            pickLocLat: pickLocation.lat,
            pickLocLng: pickLocation.lng,
            dropLocLat: dropLocation.lat,
            dropLocLng: dropLocation.lng,
            accountId: localStorage.getItem("user_id")
        }
        createTrip(json);
}



function initMap() {
  const myLatlng = { lat: 30.0595563, lng: 31.2995782 };
  map = new google.maps.Map(document.getElementById("map"), {
    zoom: 11,
    center: myLatlng,
  });
  // Create the initial InfoWindow.
  let infoWindow = new google.maps.InfoWindow({
    content: "Click the map to get Lat/Lng!",
    position: myLatlng,
  });

  infoWindow.open(map);
  // Configure the click listener.
  map.addListener("click", (mapsMouseEvent) => {
    // Close the current InfoWindow.

    infoWindow.close();
    if(isDropSelected && isPickupSelected){
      return
    }

  console.log(mapsMouseEvent.latLng.toJSON())

  if(!isPickupSelected){
    const marker = new google.maps.Marker({
      // The below line is equivalent to writing:
      // position: new google.maps.LatLng(-34.397, 150.644)
      position: mapsMouseEvent.latLng,
      label: 'Pick',
      map: map,
    });
    isPickupSelected = true
    pickLocation = mapsMouseEvent.latLng.toJSON()
    return
  }

  if(isPickupSelected && !isDropSelected){
    const marker = new google.maps.Marker({
      // The below line is equivalent to writing:
      // position: new google.maps.LatLng(-34.397, 150.644)
      position: mapsMouseEvent.latLng,
      label: 'Drop',
      title: 'Drop Location',
      map: map,
    });
    dropLocation = mapsMouseEvent.latLng.toJSON()
    isDropSelected = true
  }

    document.getElementById("pickLocation").innerHTML = "<span style=\"color:black\">"+ "Longitude: "+ pickLocation.lng +
  " </span> <br> <br> <span style=\"color:black\">"+ "Latitude: " + pickLocation.lat;

    document.getElementById("dropLocation").innerHTML = "<span style=\"color:black\">"+ "Longitude: "+ dropLocation.lng +
    "</span> <br> <br> <span style=\"color:black\">"+ "Latitude: " + dropLocation.lat;
    //TODO delete 
    console.log(pickLocation.lat)
    console.log(pickLocation.lng)
    console.log(dropLocation.lat)
    console.log(dropLocation.lng)
  });

}

window.initMap = initMap;


function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
  var R = 6371; // Radius of the earth in km
  var dLat = deg2rad(lat2-lat1);  // deg2rad below
  var dLon = deg2rad(lon2-lon1);
  var a =
    Math.sin(dLat/2) * Math.sin(dLat/2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
    Math.sin(dLon/2) * Math.sin(dLon/2)
    ;
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
  var d = R * c; // Distance in km
  return d;
}

function deg2rad(deg) {
  return deg * (Math.PI/180)
}



