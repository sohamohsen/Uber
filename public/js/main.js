//
BASE_URL="http://localhost:8080"

console.log(localStorage.getItem('user_id'));
document.getElementById("log_driver").onclick = function(){
  window.location.href = "logdriver.html";
}


document.getElementById("log_rider").onclick = function(){
  window.location.href = "logrider.html";
}
// header
function logButton() {
    window.location.href = "log.html";
}

function signButton() {
    window.location.href = "sign.html";
}
// header 

window.onscroll = function() {myFunction()};

var header = document.getElementById("myHeader");
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
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

             window.location.assign('nav.html')

        }

    }).fail(function(error){
            alert(error.responseJSON.error);
            console.log(JSON.stringify(error.responseJSON));
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

    console.log(email.value)
    console.log(password.value)
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
        console.log(email.value)
        console.log(password.value)
        signIn(email.value,password.value)
}






