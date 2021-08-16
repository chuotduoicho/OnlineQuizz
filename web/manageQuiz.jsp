<%-- 
    Document   : manageQuiz
    Created on : Feb 25, 2021, 9:31:58 PM
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
        <link href="css/manageQuiz.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@ include file="header.jsp" %>    
                <div class="container">
                    <div class="total">
                        <table>
                            <tr>
                                <td class="green-label">Number of question: </td>
                                <td class="blue-label">${total}</td>
                            </tr>
                        </table>
                    </div>
                    <c:if test="${empty questions}">
                        <div class="noti">
                            Don't have data
                        </div>
                    </c:if>
                    <div class="noti">
                        ${messageFail}
                    </div>
                    <c:if test="${not empty questions}">
                        <div class="question-list">
                            <table class="table-question">
                                <tr>
                                    <td class="blue-label">Question </td>
                                    <td class="blue-label">DateCreated</td>
                                    <td class="blue-label">Action</td>
                                </tr>
                                <c:forEach var="i" items="${questions}">
                                    <tr>
                                        <td class="green-label question">${i.questionContent}</td>
                                        <td class="green-label"> ${i.createdAt}</td>
                                        <td><a href="delete?code=${i.questionCode}" onclick="return confirm('Are you sure, you want to delete this question?')" class="green-label">delete</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:if>
                    <div>
                        <ul class="pagination">
                            <c:forEach var="i" begin="1" end="${totalPage}" step="1">
                                <li><a id="page${i}" href="?pageNumber=${i}">${i}</a></li>
                            </c:forEach>
                        </ul>
                    </div>   
                </div>
            </div>
        </div>
    </body>
    <script src="./js/manageQuiz.js" ></script>
</html>
