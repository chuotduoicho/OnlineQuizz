<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/takeQuiz.css" rel="stylesheet" type="text/css"/>
        <title>Home</title>
    </head>
    <body>
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
                    <div class="error">
                        ${error}
                    </div>
                    <div class="input-form green-label">
                        Enter number of question:
                        <div>
                            <form action="take" method="POST" class="form">
                                <input type="text" name="number" class="text">
                                <input type="submit" value="Start" class="submit">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
