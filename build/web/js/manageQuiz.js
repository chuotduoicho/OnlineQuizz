// Fuction to active page number
    var pages = document.querySelectorAll(".pagination");
    
    // Get all params in URL
    var searchParams = new URLSearchParams(window.location.search);
    // Get value of pageNumber in URL
    var pageNumber = searchParams.get('pageNumber');
    
    // If pageNumber does not exist, server will set pageNumber equal 1. Therefore, active page 1
    if (!pageNumber) {
        document.getElementById("page1").classList.add("active");
    } 
    // In other cases, active page equals pageNumber params in URL 
    else {
         document.getElementById("page"+pageNumber).classList.add("active");
    }

