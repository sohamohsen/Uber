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






