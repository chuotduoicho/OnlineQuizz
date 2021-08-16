<%-- 
    Document   : makeQuiz
    Created on : Feb 25, 2021, 3:55:46 PM
    Author     : Vân Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/makeQuiz.css" rel="stylesheet" type="text/css"/>
        <title>Home</title>
    </head>
    <body>
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@ include file="header.jsp" %>    
                <div class="container">
                    <div class="submit-form">
                        <p class="error" id="noti"></p>
                        <c:if test="${success}">
                            <p class="success" id="success-noti">Question added successful</p>
                        </c:if>
                        <c:if test="${unsuccess}">
                            <p class="error">Question added failed</p>
                        </c:if>    
                        <form action="" method="POST" onSubmit="return validateMakeQuizForm()">
                            <table>
                                <tr>
                                    <td class="label">Question: </td>
                                    <td><textarea id="question" name="question"></textarea></td>
                                </tr>
                                <tr>
                                    <td class="label">Option 1: </td>
                                    <td><textarea id="option1TextArea" class="option" name="option1Content"></textarea></td>
                                </tr>
                                <tr>
                                    <td class="label">Option 2: </td>
                                    <td><textarea id="option2TextArea" class="option" name="option2Content"></textarea></td>
                                </tr>
                                <tr>
                                    <td class="label">Option 3: </td>
                                    <td><textarea id="option3TextArea" class="option" name="option3Content"></textarea></td>
                                </tr>
                                <tr>
                                    <td class="label">Option 4: </td>
                                    <td><textarea id="option4TextArea" class="option" name="option4Content"></textarea></td>
                                </tr>
                                <tr>
                                    <td class="label">Answer(s): </td>
                                    <td>
                                        <div class="answer">
                                            <div class="option-answer">
                                                <input type="checkbox" id="option1" name="option1Check" value="checked">
                                                <label for="option1" class="label">Option 1</label>
                                            </div>
                                            <div class="option-answer">
                                                <input type="checkbox" id="option2" name="option2Check" value="checked">
                                                <label for="option2" class="label">Option 2</label>
                                            </div>
                                            <div class="option-answer">
                                                <input type="checkbox" id="option3" name="option3Check" value="checked">
                                                <label for="option3" class="label">Option 3</label>
                                            </div>
                                            <div class="option-answer">
                                                <input type="checkbox" id="option4" name="option4Check" value="checked">
                                                <label for="option4" class="label">Option 4</label>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" value="Save"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="./js/makeQuiz.js" ></script>
</html>
