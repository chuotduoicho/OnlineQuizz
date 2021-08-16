<%-- 
    Document   : score
    Created on : Feb 25, 2021, 3:46:22 PM
    Author     : Vân Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/score.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@include file="header.jsp" %>
                <div class="container">
                    <div class="score-number">
                        <div class="green-label">
                            Your score
                        </div>
                        <c:if test="${pass}">
                            <div class="score blue-label">
                                ${score}
                            </div>
                        </c:if>
                        <c:if test="${not pass}">
                            <div class="score error">
                                ${score}
                            </div>
                        </c:if>     
                    </div>
                    <div class="again">
                        Take another test
                        <a href="take"><button class="btn">Start</button></a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
