<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@ include file="header.jsp" %>
                <div class="container">
                    <c:if test="${not empty sessionScope.user}">
                        Hello ${sessionScope.user.userName}
                    </c:if>
                    <p>${success}</p>
                    <c:if test="${empty sessionScope.user}">
                        <a href="login" class="green-label">Login</a>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
