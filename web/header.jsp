<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="menu-bar">
            <nav class="navbar">
                <a class="nav-item" href="home"><div>Home</div></a>
                <a class="nav-item" href="take"><div>Take Quiz</div></a>
                <a class="nav-item" href="make"><div>Make Quiz</div></a>
                <a class="nav-item" href="manage"><div>Manage Quiz</div></a>
                <c:if test="${not empty sessionScope.user}">
                    <a class="nav-item" href="logout" ><div>Log out</div></a>
                </c:if>
            </nav>
        </div>
    </body>
</html>
