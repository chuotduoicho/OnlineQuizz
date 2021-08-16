<%-- 
    Document   : register
    Created on : Feb 25, 2021, 9:47:49 PM
    Author     : Vân Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="ground">
            <div class="border-bar"></div>
            <div class="block">
                <%@ include file="header.jsp" %>
                <div class="container">
                    <h5>Registration Form</h5>
                    <div class="login-form">
                        <p class="error" id="noti">${error}</p>
                        <form action="register" method="POST" onSubmit="return validateRegisterForm()">
                            <table>
                                <tr>
                                    <td class="green-label">User Name: </td>
                                    <td><input type="text" name="username" id="username" value="${username}"></td>
                                </tr>
                                <tr>
                                    <td class="green-label">Password: </td>
                                    <td><input type="password" name="password" id="password"></td>
                                </tr>
                                <tr>
                                    <td class="green-label">User Type: </td>
                                    <td>
                                        <select name="role">
                                            <option value="teacher" ${!isStudent?'selected':''}>Teacher</option>
                                            <option value="student" ${isStudent?'selected':''}>Student</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="green-label">Email: </td>
                                    <td><input type="text" name="email" id="email" value="${email}"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="submit" value="Register"></td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="./js/register.js" ></script>
</html>
