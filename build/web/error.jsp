<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/style.css" />
    </head>
    <body>
        
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@ include file="header.jsp" %>    
                <div class="container">
                    <h2 class="error">Error</h2>
                    <c:if test="${empty error}">
                        <h3>404 Not Found</h3>
                    </c:if>
                    <p>${error}</p>
                    <c:if test="${errorAuthen}">
                        <a href="login" class="green-label">Log in</a>
                    </c:if>
                </div>
            </div>
        </div>

    </body>
</html>
