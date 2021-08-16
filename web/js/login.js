//Validate form before submit
function validateLoginForm() {
    var userName = document.getElementById("username");
    var password = document.getElementById("password");
    var message = document.getElementById("noti");
    //Check input blank
    if (userName.value.trim() == "") {
        message.textContent = "Username can not be blank";
    }
    else if (password.value.trim() == "") {
        message.textContent = "Password can not be blank";
    }
    else {
        message.textContent = "";
        return true;
    }
    return false;
}


