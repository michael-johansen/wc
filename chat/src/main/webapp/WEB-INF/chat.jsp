<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java EE 6 chat</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="../resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            Chatter
        </div>
        <div class="span10">

            <table class="table table-striped table-condensed table-hover table-bordered">
                <thead>
                <tr>
                    <th>User</th>
                    <th>Text</th>
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
                <div class="control-group">
                    <label class="control-label">User name</label>

                    <div class="controls">
                        <input type="text" name="user" placeholder="Type something…">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">Message</label>

                    <div class="controls">
                        <input type="text" name="message" placeholder="Type something…">
                    </div>
                </div>

                <button type="submit" class="btn">Submit</button>
            </form>

        </div>
    </div>
</div>

<script src="../resources/jquery-2.0.0.js"></script>
<script src="../resources/bootstrap/js/bootstrap.js"></script>
</body>
</html>