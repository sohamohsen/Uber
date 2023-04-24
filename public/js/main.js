//
BASE_URL="http://localhost:8080"
cityId = -1
carMakerId = -1
carModelId = -1

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
        data: JSON.stringify({
            //key: value
            email: emailValue,
            password: passwordValue
        })
    }).then(function(data, status, jqxhr) { // response
        // Update UI
        // Navigate to profile
        console.log(JSON.stringify(data));
        // save user id in browser data
        localStorage.setItem('user_id',data.id);
        console.log(localStorage.getItem('user_id'));
        if(data.type === "driver"){
            //navigate to driver profile
            window.location.assign('index.html', "_self")
        }else{
             //navigate to rider profile

             window.location.assign('rider-wallet.html')

        }

    }).fail(function(error){
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
            for (var i = 0; i<=data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].cityName;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
            alert(JSON.stringify(error.responseJSON.error));
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
            carMakerId = data[0].id;
            for (var i = 0; i<=data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].maker;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
            alert(JSON.stringify(error.responseJSON.error));
        });
}


function onCarMakerSelectItem(event){
    Id = event.target.value;
    console.log(Id);
}

function getCarModels(){
      $.ajax({ // request
            url: BASE_URL+"/carmodels",
            type: 'GET',
            headers:{
                "Access-Control-Allow-Origin": '*' // recommended
            }
        }).then(function(data, status, jqxhr) { // response
            // Update UI
            select = document.getElementById('model');
            carModelId = data[0].id;
            for (var i = 0; i<=data.length; i++){
                var opt = document.createElement('option');
                opt.value = data[i].id;
                opt.innerHTML = data[i].name;
                select.appendChild(opt);
            }

        }).fail(function(error){
            console.log(error.responseJSON);
            alert(JSON.stringify(error.responseJSON.error));
        });
}


function onCarModelSelectItem(event){
    Id = event.target.value;
    console.log(Id);
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
            window.location.assign('signup-driver.html', "_self")
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