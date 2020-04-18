<!--
<%@ page errorPage="errorPage.jsp" %>
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href=${pageContext.request.contextPath}/css/bootstrap.min.css>
    <link rel="stylesheet"
          href=${pageContext.request.contextPath}/css/mainstyles.css>
    <link rel="shortcut icon"
          href=${pageContext.request.contextPath}/images/favicon.ico>
    <script src=${pageContext.request.contextPath}/js/jscript.js></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
</head>
<body>
<nav id="nav" class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"> Welcome to <span class="title">RW!
				</span></a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a>Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href=${pageContext.request.contextPath}/logout>Log
                    Out</a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="content">
    <div class="contentDiv">
        <p id="wel">Welcome ${userName}.</p>
        <h4>List of Contacts</h4>
        <div class="list-group" data-ng-init='contacts=${userContacts}'>
            <div data-ng-repeat="contact in contacts">
                <contact-info info="contact"></contact-info>
            </div>
        </div>
    </div>
    <div class="contentDiv">
        <form:form action="${pageContext.request.contextPath}/addContact"
                   class="form-horizontal" modelAttribute="newContact">
            <h4 class="col-sm-offset-3">Add New Contact</h4>
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
                    Name:</label>
                <div class="col-sm-3">
                    <form:input path="lastName" type="text" class="form-control"
                                id="inputLastName" placeholder="Last Name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputEmail">Email:</label>
                <div class="col-sm-3">
                    <form:input path="email" type="email" class="form-control"
                                id="inputEmail" placeholder="Email Address"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3" for="inputAddress">Address:</label>
                <div class="col-sm-3">
                    <form:input path="address" type="text" class="form-control"
                                id="inputAddress" placeholder="Address"/>
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
                           value="Add Contact">
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
        src=${pageContext.request.contextPath}/js/directives/contactInfo.js></script>
</body>
</html>
