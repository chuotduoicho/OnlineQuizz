function validateRegisterForm() {
    var userName = document.getElementById("username");
    var password = document.getElementById("password");
    var email = document.getElementById("email");
    var message = document.getElementById("noti");
    // Check blank data
    if (userName.value.trim()=="") {
        message.textContent = "Username can not be blank";
    }
    else if (password.value.trim()=="") {
        message.textContent = "Password can not be blank";
    }
    else if (email.value.trim()=="") {
        message.textContent = "Email can not be blank";
    }
    else {
        message.textContent = "";
        return true;
    }
    return false;
}

