<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Error</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/mainstyles.css">
    <link rel="icon" href="images/favicon.ico">
    <script
            src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
</head>

<body>
<nav id="nav" class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Welcome to <span class="title">RW!</span></a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login">Sign In</a></li>
                <li><a href="signup">Sign Up</a></li>
                <li><a href="about">About</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="content">
    <div class="contentDiv">
        <c:if test="${empty errorMsg}">
            <h4>An unexpected error occurred.</h4>
            <p>No information or details available.</p>
        </c:if>
        <p>${errorMsg}</p>
    </div>
</div>

<my-footer></my-footer>

<!-- Modules -->
<script src="js/app.js"></script>
<!-- Directives -->
<script src="js/directives/myFooter.js"></script>
</body>
</html>
