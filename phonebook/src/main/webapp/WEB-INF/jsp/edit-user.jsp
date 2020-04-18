<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Edit User</title>
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
            <a class="navbar-brand" href="#"> Welcome to <span class="title">RW!
				</span></a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href=${pageContext.request.contextPath}/home>Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href=${pageContext.request.contextPath}/logout> Log
                    Out </a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="content">
    <div class="contentDiv">
        <div class="pull-left">
            <h4>User Information</h4>
            <form class="form-horizontal" action="updateUserInfo" method="post"
                  onsubmit="return confirmChanges()">
                <div class="form-group">
                    <label class="control-label col-sm-5">Phone Number: </label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="phoneNumber"
                               name="phoneNumber" value="${userInfo.phoneNumber}"
                               required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5">First Name: </label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="firstName"
                               name="firstName" value="${userInfo.firstName}"
                               required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5">Last Name: </label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="lastName"
                               name="lastName" value="${userInfo.lastName}" required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5">Email: </label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="email" name="email"
                               value="${userInfo.email}" required="required"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5">Address: </label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="address"
                               name="address" value="${userInfo.address}" required="required"/>
                    </div>
                </div>
                <input type="hidden" name="uid" value="${param.uid}">
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-7">
                        <input class="btn btn-lg btn-block btn-primary" type="submit"
                               value="Save Changes">
                    </div>
                </div>
            </form>
        </div>
        <div class="pull-right">
            <form action="addUserContact" class="form-horizontal" method="post">
                <h4 class="col-sm-offset-3">Add Contact For This User</h4>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="inputPhoneNumber">Phone
                        Number:</label>
                    <div class="col-sm-7">
                        <input name="phoneNumber" id="inputPhoneNumber" type="text"
                               class="form-control" placeholder="Phone Number" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="inputFirstName">First
                        Name:</label>
                    <div class="col-sm-7">
                        <input name="firstName" type="text" class="form-control"
                               id="inputFirstName" placeholder="First Name" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="inputLastName">Last
                        Name:</label>
                    <div class="col-sm-7">
                        <input name="lastName" type="text" class="form-control"
                               id="inputLastName" placeholder="Last Name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="inputEmail">Email:</label>
                    <div class="col-sm-7">
                        <input name="email" type="email" class="form-control"
                               id="inputEmail" placeholder="Email Address">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-5" for="inputAddress">Address:</label>
                    <div class="col-sm-7">
                        <input name="address" type="text" class="form-control"
                               id="inputAddress" placeholder="Address">
                    </div>
                </div>
                <c:choose>
                    <c:when test="${empty param.uid}">
                        <input type="hidden" name="uid"
                               value='${session.getAttribute("uid")}'>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="uid" value="${param.uid}">
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <div class="col-sm-offset-5 col-sm-7">
                        <input class="btn btn-lg btn-block btn-primary" type="submit"
                               value="Add Contact">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <br>
    <div class="contentDiv clearAll">
        <h4 class="clearAll">List Of Contacts For This User</h4>
        <div class="list-group" data-ng-init='contacts=${userContacts}'>
            <div data-ng-repeat="contact in contacts">
                <contact-info info="contact"></contact-info>
            </div>
        </div>
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
