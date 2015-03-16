
function validateRegister(){
    var errors = ["Errors: "];
    var firstName = document.getElementById("registerForm:firstName").value;
    var lastName = document.getElementById("registerForm:lastName").value;
    var mail = document.getElementById("registerForm:mail").value;
    var ssn = document.getElementById("registerForm:SSN").value;
    var username = document.getElementById("registerForm:username").value;
    var password = document.getElementById("registerForm:password").value;
    
    if (firstName == null || firstName == "") {
        errors.push("First name");
    }
    if (lastName == null || lastName == "") {
        errors.push("Last name");
    }
    if (mail == null || mail == "") {
        errors.push("mail");
    }
    if (ssn == null || ssn == "") {
        errors.push("SSN");
    }
    if (username == null || username == "") {
        errors.push("username");
    }
    if (password == null || password == "") {
        errors.push("password");
    }
    
    if (errors.length == 1){
        return true;
    }else{
        alert(errors);
    }
}
function validateCompetence(){
   var x = document.getElementById("listedCompetence").rows.length;
    if (x < 2){
        alert("You must have at least ONE competence!");
    }else{
       
        return true;
    }
    
}

function validateAddAvailability(){
    var x = document.getElementById("listedAvailability")
}

function validateAvailability(){
    
     var x = document.getElementById("listedAvailability").rows.length;
    if (x < 2){
        alert("You must have at least ONE availability period");
    }else{
        
        return true;
    }
}