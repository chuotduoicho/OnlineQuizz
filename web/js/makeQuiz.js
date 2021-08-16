// Validate data before submit
function validateMakeQuizForm() {
    var message = document.getElementById("noti"),
        successNoti = document.getElementById("success-noti"),
        question = document.getElementById("question"),
        option1 = document.getElementById("option1TextArea"),
        option2 = document.getElementById("option2TextArea"),
        option3 = document.getElementById("option3TextArea"),
        option4 = document.getElementById("option4TextArea"),
        check1 = document.getElementById("option1"),
        check2 = document.getElementById("option2"),
        check3 = document.getElementById("option3"),
        check4 = document.getElementById("option4");
    // check if message success exist
    if (successNoti!=null) successNoti.textContent="";
    // check blank textarea 
    if (question.value.trim()=="") {
        message.textContent = "Question content can not be blank";
        return false;
    }
    else if (option1.value.trim()=="") {
        message.textContent = "Option 1 can not be blank";
        return false;
    }
    else if (option2.value.trim()=="") {
        message.textContent = "Option 2 can not be blank";
        return false;
    }
    else if (option3.value.trim()=="") {
        message.textContent = "Option 3 can not be blank";
        return false;
    }
    else if (option4.value.trim()=="") {
        message.textContent = "Option 4 can not be blank";
        return false;
    }
    // check no option chosen
    if (!check1.checked&&!check2.checked&&!check3.checked&&!check4.checked) {
        message.textContent = "Select one or more option";
        return false;
    }
    // check all option chosen
    if (check1.checked&&check2.checked&&check3.checked&&check4.checked) {
        message.textContent = "Can't select all options";
        return false;
    }
    return true;
}