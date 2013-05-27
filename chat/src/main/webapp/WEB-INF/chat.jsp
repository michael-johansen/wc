<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Java EE 6 chat</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css"/>" rel="stylesheet">
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            Chatter
        </div>

        <div class="span10">

            <div class="input-prepend">
                <button type="button" class="btn" onclick="chat.changeName()">Change username to</button>
                <input id="username" type="text" name="user" placeholder="What's your name?" value="${user}">
            </div>

            <table class="table table-striped table-condensed table-hover table-bordered">
                <thead>
                <tr>
                    <th class="span2">User</th>
                    <th class="span10">Text</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${messages}" var="message">
                    <tr>
                        <td>${message.user}</td>
                        <td>${message.text}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <form class="form-horizontal" method="post">
                <div class="input-append">
                    <input id="message" type="text" name="text" class="input-xxlarge" placeholder="Say something…">
                    <button type="submit" class="btn">Submit</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script src="<c:url value="/resources/jquery-2.0.0.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/chat.js"/>"></script>
</body>
</html>