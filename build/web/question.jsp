<%-- 
    Document   : question
    Created on : Feb 25, 2021, 3:27:32 PM
    Author     : Vân Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/question.css" rel="stylesheet" type="text/css"/>
        <title>Home</title>
    </head>
    <body onload="countdown(${remain})">
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@ include file="header.jsp" %>    
                <div class="container">
                    <div class="welcome">
                        <div class="green-label">
                            Welcome
                        </div>
                        <div class="user-name blue-label">
                            ${name}
                        </div>
                    </div>
                    <div class="timer">
                        <div>Time remaining </div>
                        <div id="time"></div>
                    </div>
                    <div class="test">
                        <div class="question">${content}</div>
                        <div class="option">
                            <form id="formQues" action="test?question=${number}" method="POST">
                                <c:forEach var="i" items="${options}" varStatus="loop">
                                    <div class="option-item">
                                        <input type="checkbox" 
                                               <c:forEach var="uChoice" items="${chosen}" >
                                                   <c:if test="${(loop.index+1) eq uChoice}">
                                                       checked="true"
                                                   </c:if>
                                               </c:forEach>
                                               id="option${loop.index+1}" name="option${loop.index+1}" value="checked">
                                        <label for="option${loop.index+1}" class="label">${i}</label>
                                    </div>
                                </c:forEach>
                                <input id="submit-btn" type="submit" class="submit-btn" value="Next">
                                <input type="hidden" id="isEnd" name="isEnd" value="">
                                <c:if test="${isLastQuest}">
                                    <input id="submit-btn" type="submit" onclick="return confirmSm()" value="Finish">          
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="./js/question.js"></script>
</html>