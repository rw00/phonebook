<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Welcome: Sign Up</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/default.css">
    <link rel="icon" href="images/favicon.ico">
    <script src="js/main.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.8.2/angular.min.js"></script>
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
                <li class="active"><a>Sign Up</a></li>
                <li><a href="about">About</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="content">
    <div class="container">
        <form:form modelAttribute="newUser" class="form-horizontal"
                   action="checkSignup" onsubmit="return checkValidForm()">
            <h3 class="col-sm-offset-2">Create New Account</h3>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputPhoneNumber">Phone
                    Number:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <form:input path="phoneNumber" id="inputPhoneNumber" type="text"
                                onkeyup="checkNumber(this.id)" class="form-control"
                                placeholder="Phone Number" required="required"/>
                </div>
                <span id="inputPhoneNumberValid" class="col-sm-4 valid"></span>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="inputPassword">Password:<span
                        class="required"> * </span></label>
                <div class="col-sm-3">
                    <form:input path="password" onkeyup="checkPassword() "
                                type="password" class="form-control" id="inputPassword"
                                placeholder="Password" required="required"/>
                </div>
                <span id="inputPasswordValid" class="col-sm-6 valid"></span>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputConfirmPass">Confirm
                    Password:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <input name="confirmPass" onkeyup="checkConfirmPass()"
                           type="password" class="form-control" id="inputConfirmPass"
                           placeholder="Confirm Password" required>
                </div>
                <span id="inputConfirmPassValid" class="col-sm-4 valid"></span>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputFirstName">First
                    Name:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <form:input path="firstName" onkeyup="checkName(this.id)"
                                type="text" class="form-control" id="inputFirstName"
                                placeholder="First Name" required="required"/>
                </div>
                <span id="inputFirstNameValid" class="col-sm-4 valid"></span>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputLastName">Last
                    Name:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <form:input path="lastName" onkeyup="checkName(this.id)"
                                type="text" class="form-control" id="inputLastName"
                                placeholder="Last Name" required="required"/>
                </div>
                <span id="inputLastNameValid" class="col-sm-4 valid"></span>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputEmail">Email:<span
                        class="required"> * </span></label>
                <div class="col-sm-3">
                    <form:input path="email" onkeyup="checkEmail()" type="email"
                                class="form-control" id="inputEmail" placeholder="Email Address"
                                required="required"/>
                </div>
                <span id="inputEmailValid" class="col-sm-4 valid"></span>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputAddress">Address:<span
                        class="required"> * </span></label>
                <div class="col-sm-3">
                    <form:input path="address" type="text" class="form-control"
                                id="inputAddress" placeholder="Address" required="required"/>
                </div>
                <span id="inputAddressValid" class="col-sm-4 valid"></span>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3">
                    <p>
                        <span class="required"> * </span>fields are required.
                    </p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-3">
                    <input class="btn btn-lg btn-block btn-primary" type="submit"
                           value="Sign Up">
                </div>
            </div>
        </form:form>
    </div>
</div>
<my-footer></my-footer>

<!-- Modules -->
<script src="js/app.js"></script>
<!-- Directives -->
<script src="js/directives/myFooter.js"></script>
</body>
</html>
