// Change time ever second, auto submit when timeout
function countdown(timeRemain) {
    // repeat function every second
    setInterval(function () {   
        var timeShow = document.getElementById("time");
        var minutes = Math.floor((timeRemain / (1000 * 60)));
        var seconds = Math.floor(timeRemain/1000 - minutes*60);
        var mmText = "";
        var ssText = "";
        mmText = (minutes<10)?"0"+minutes:""+minutes; // minute format: mm
        ssText = (seconds<10)?"0"+seconds:""+seconds; // second format: ss
        timeShow.textContent = mmText + ":" + ssText;
        // submit when time out
        if (timeRemain <= 0) {
            timeShow.textContent = "Time is up";
            document.getElementById("isEnd").value = "true";
            document.getElementById("submit-btn").click();
        }
        // decrease 1 second
        timeRemain -= 1000;
    }, 1000)
}

function confirmSm() {
    var r = confirm('Are you sure finish quiz now?');
    if(r) {
        document.getElementById("isEnd").value = "true";
        return true;
    }
    return false;
}


