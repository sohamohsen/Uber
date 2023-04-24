//Global variable
BASE_URL="http://localhost:8080"
cityId = -1
carMakerId = -1
carModelId = -1
year = -1
colorId =-1


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
        console.log(JSON.stringify(data));
        // save user id in browser data to load its data
        localStorage.setItem('user_id',data.id);
        console.log(localStorage.getItem('user_id'));
        //check the type of user to navigate to his/her profile
        if(data.type === "driver"){
            //navigate to driver profile
            window.location.assign('rider-wallet.html', "_self")
        }else{
             //navigate to rider profile

             window.location.assign('takeride.html')

        }

    }).fail(function(error){ //Return backend validation errors
            alert(error.responseJSON.error);
            console.log(JSON.stringify(error.responseJSON));
    });
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
            window.location.assign('rider-wallet.html', "_self")
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

            if(email.value.indexOf('@') === -1 || !email.value.includes(".com")){
                alert("Invalid Email Format.")
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

        if(email.value.indexOf('@') === -1 || !email.value.includes(".com")){
            alert("Invalid Email Format.")
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
    }
    else if(email.value.indexOf('@') === -1 || !email.value.includes(".com")){
         alert("Invalid Email Format.")
         return;
    }
    if (password.length < 8){
            alert("Invalid Password.")
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
    if (email.value === "")
    {
        alert("Please, Enter your mail")
        return;
    }
    else if(email.value.indexOf('@') === -1 || !email.value.includes(".com")){
         alert("Invalid Email Format.")
         return;
    }
    if (password.length < 8){
        alert("Invalid Password.")
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

    if (firstName.value === "" || lastName.value === ""){
        alert("Please, Enter your full name")
        return;
    }
    if (phoneNumber.value === "" ){
            alert("Please, Enter your phone number")
            return;
    }else if (phoneNumber.value.length !== 11 ){
            alert("Invalid phone number")
            return;
    }
    if (birthDate.value === "" ){
                alert("Please, Enter your birthdate")
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

    if (firstName.value === "" || lastName.value === ""){
        alert("Please, Enter your full name")
        return;
    }
    if (phoneNumber.value === "" ){
            alert("Please, Enter your phone number")
            return;
    }else if (phoneNumber.value.length !== 11 ){
            alert("Invalid phone number")
            return;
    }
    if (nationalId.value === "" ){
                alert("Please, Enter your national id")
                return;
    }else if (nationalId.value.length !== 14 ){
                alert("Invalid national id")
                return;
   }

    if (driverLicence.value === ""){
            alert("Please, Enter your driver licence")
            return;
    }else if (driverLicence.value.length !== 7 ){
             alert("Invalid driver licence")
             return;
       }
    if (birthDate.value === "" ){
                alert("Please, Enter your birthdate")
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
    if (vehicleLicence.value.length !== 9){
            alert("Invalid vehicle licence number.")
            return;
    }
    if (licencePlate.value === "")
        {
            alert("Please, Enter your licence plate number")
            return;
        }
        if (licencePlate.value.length < 6 ||  licencePlate.value.length >7 ){
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