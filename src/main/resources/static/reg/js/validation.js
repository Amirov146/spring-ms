function checkPasswordStrength(password) {
    if (password.length < 8) {
        return 'Too short password';
    }
    if (!/[a-z]/.test(password) || !/[0-9]/.test(password)) {
        return 'Password must contain at least one letter and one number';
    }
    if (!/[A-Z]/.test(password)) {
        return 'Password must contain at least one uppercase letter';
    }
    if (!/[!@#\$%\^&\*]/.test(password)) {
        return 'Password must contain at least one special character';
    }
    return 'Password is strong enough';
}

// document.getElementById('regForm').addEventListener('submit', function(event) {
//     event.preventDefault();
//
//     var password = document.getElementById('password').value;
//     var passwordStrength = checkPasswordStrength(password);
//
//     if (passwordStrength === 'Strong') {
//         // Submit the form
//         this.submit();
//     } else {
//         // Display an error message
//         alert(passwordStrength);
//     }
// });


function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (!re.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    if (!email.endsWith("@mail.ru") && !email.endsWith("@gmail.com")) {
        alert("Please enter a valid email address. Only @mail.ru and @gmail.com are allowed.");
        return false;
    }
    return true;
}

document.getElementById('regForm').addEventListener('submit', function(event){
   event.preventDefault();

   var email = document.getElementById('email').value;
   var isValidatedEmail = validateEmail(email);
    var password = document.getElementById('password').value;
    var passwordStrength = checkPasswordStrength(password);

   if(passwordStrength === 'Password is strong enough' && isValidatedEmail === true){
       this.submit();
   }
   else{
       alert(passwordStrength);
   }
});



