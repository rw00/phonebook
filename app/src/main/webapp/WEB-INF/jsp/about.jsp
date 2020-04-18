<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>About RW</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/default.css">
    <link rel="icon" href="images/favicon.ico">
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.8.2/angular.min.js"></script>
</head>
<body>
<nav id="nav" class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href=""> Welcome to <span class="title">RW!</span>
            </a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="login">Sign In</a></li>
                <li><a href="signup">Sign Up</a></li>
                <li class="active"><a>About</a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="content">
    <div class="container">
        <p>
            Welcome to <span class="title">RW!</span>
        </p>
        <p>Using our free service, you can store your contacts
            information and details on our servers, and manage them easily and
            swiftly just like in your phone book.</p>
        <p>Note that this information will be kept private and secure.</p>
        <p>
            Please, refer to the <a href="#"> Terms and Conditions </a> and the
            <a href="#"> Privacy Policy </a> that apply by using our service.
        </p>
        <p>
            If you don't have an account, <a href="signup">sign up</a> is free
            and always will be!
        </p>
    </div>
</div>
<my-footer></my-footer>

<!-- Modules -->
<script src="js/app.js"></script>
<!-- Directives -->
<script src="js/directives/myFooter.js"></script>
</body>
</html>
