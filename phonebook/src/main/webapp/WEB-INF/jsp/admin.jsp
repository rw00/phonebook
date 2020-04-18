<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Welcome to RW</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href=${pageContext.request.contextPath}/css/bootstrap.min.css>
    <link rel="stylesheet"
          href=${pageContext.request.contextPath}/css/mainstyles.css>
    <link rel="icon"
          href=${pageContext.request.contextPath}/images/favicon.ico>
    <script src=${pageContext.request.contextPath}/js/jscript.js></script>
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
                <li class="active"><a>Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href=${pageContext.request.contextPath}/logout> Log
                    Out </a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="content">
    <div class="contentDiv">
        <p>Welcome Admin: ${adminInfo.email}</p>
        <h4>
            <a href="contacts"> My Contacts </a>
        </h4>
        <h4>List of Administrators</h4>
        <div class="list-group" data-ng-init='admins=${admins}'>
            <div data-ng-repeat="admin in admins">
                <admin-info info="admin"></admin-info>
            </div>
        </div>

        <h4>List of Users</h4>
        <div class="list-group" data-ng-init='users=${users}'>
            <div data-ng-repeat="user in users">
                <user-info info="user"></user-info>
            </div>
        </div>
        <form:form action="addUser" class="form-horizontal" method="post"
                   modelAttribute="newUser">
            <h4 class="col-sm-offset-3">Add New User</h4>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputPhoneNumber">Phone
                    Number:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <form:input path="phoneNumber" id="inputPhoneNumber" type="text"
                                class="form-control" placeholder="Phone Number"
                                required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputPassword">Password:<span
                        class="required"> * </span></label>
                <div class="col-sm-3">
                    <form:input path="password" type="password" class="form-control"
                                id="inputPassword" placeholder="Password" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputConfirmPassword">Confirm
                    Password:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <input name="confirmPass" type="password" class="form-control"
                           id="inputConfirmPassword" placeholder="Password" required/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputFirstName">First
                    Name:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <form:input path="firstName" type="text" class="form-control"
                                id="inputFirstName" placeholder="First Name" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputLastName">Last
                    Name:<span class="required"> * </span>
                </label>
                <div class="col-sm-3">
                    <form:input path="lastName" type="text" class="form-control"
                                id="inputLastName" placeholder="Last Name" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputEmail">Email:<span
                        class="required"> * </span></label>
                <div class="col-sm-3">
                    <form:input path="email" type="email" class="form-control"
                                id="inputEmail" placeholder="Email Address" required="required"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputAddress">Address:<span
                        class="required"> * </span></label>
                <div class="col-sm-3">
                    <form:input path="address" type="text" class="form-control"
                                id="inputAddress" placeholder="Address" required="required"/>
                </div>
            </div>
            <div class="col-sm-offset-3">
                <p>
                    <span class="required"> * </span>fields are required.
                </p>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-3">
                    <input class="btn btn-lg btn-block btn-primary" type="submit"
                           value="Add User">
                </div>
            </div>
        </form:form>
    </div>
</div>
<my-footer></my-footer>

<!-- Modules -->
<script src=${pageContext.request.contextPath}/js/app.js></script>
<!-- Directives -->
<script
        src=${pageContext.request.contextPath}/js/directives/myFooter.js></script>
<script
        src=${pageContext.request.contextPath}/js/directives/userInfo.js></script>
<script
        src=${pageContext.request.contextPath}/js/directives/adminInfo.js></script>
</body>
</html>
