<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Welcome to RW</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/mainstyles.css">
    <link rel="icon" href="images/favicon.ico">
    <script src="js/jscript.js">

    </script>
    <script src="http://code.jquery.com/jquery-2.1.4.min.js">

    </script>
    <script src="js/janime.js"></script>
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
                <li class="active"><a>Sign In</a></li>
                <li><a href="signup">Sign Up</a></li>
                <li><a href="about">About</a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="content">
    <div id="login">
        <form action="checkLogin" class="form-signin" method="post">
            <h2>Log In</h2>
            <label for="inputPhoneNumber">Phone Number:</label> <input
                name="phoneNumber" type="text" class="form-control"
                id="inputPhoneNumber" placeholder="Phone Number"
                required="required"/> <br> <label for="inputPassword">Password:</label>
            <input name="password" type="password" class="form-control"
                   id="inputPassword" placeholder="Password" required="required"/> <label><a
                href="" onclick="disabledFeature()">Forgot Password?</a></label> <br>
            <br>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign
                In
            </button>
            <br> <label>Don't have an account? Sign up <a
                href="signup">here</a></label>
        </form>
    </div>
</div>
<script>
    // AJAX script for handling new account thing
    var xmlhttp;
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (xmlhttp.responseText.trim() != "") {
                document.getElementById("inputPhoneNumber").value = xmlhttp.responseText;
                alert("New account has been created successfully. Log in now!");
            }
        }
    }

    xmlhttp.open("GET", "getInformation", true);
    xmlhttp.send("");
</script>

<my-footer></my-footer>

<!-- Modules -->
<script src="js/app.js"></script>
<!-- Directives -->
<script src="js/directives/myFooter.js"></script>
</body>
</html>
