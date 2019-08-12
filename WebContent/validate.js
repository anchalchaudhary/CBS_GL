function validateCustomerLogin() {
    'use strict';
    var validationStatus = true;
    var username = document.forms["customerLogin"]["username"].value;
    var password = document.forms["customerLogin"]["password"].value;
    
    
    
    if(username == "") {
        validationStatus = false;
    } else if(password == "") {
        validationStatus = false;
    } 
    if(!validationStatus) {
        alert("Form validation fails");
    } else{
        alert("Success");
    }
    return validationStatus;
}
function validateCustomerRegistration() {
    'use strict';
    var validationStatus = true;
    var cname = document.forms["customerRegistration"]["cname"].value;
    var username = document.forms["customerRegistration"]["username"].value;
    var password = document.forms["customerRegistration"]["password"].value;
    var age = document.forms["customerRegistration"]["age"].value;
    var radios = document.getElementsByName("gender");
    
    var passwordRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");

    var passwordValidation = false;
    if(passwordRegex.test(password))
    	passwordValidation = true;
    
    if(cname == "") {
        validationStatus = false;
    } else if(username == "") {
        validationStatus = false;
    } else if(password == "") {
        validationStatus = false;
    } else if(age == "") {
        validationStatus = false;
    }
    var len = radios.length;
    var chosen = "";
    for(var i = 0; i < len; i++) {
        var gen = false;
        if(radios[i].checked) {
            gen = true;
            chosen = radios[i].value;
            break;
        }
    }
    if(!gen) {
        validationStatus = false;
    }
    if(!validationStatus && !passwordValidation) {
        alert("Form validation fails");
    } else if(validationStatus && passwordValidation){
        alert("Success");
    } else{
    	alert("Error");
    }
    return validationStatus && passwordValidation;
}