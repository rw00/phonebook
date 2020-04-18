<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Edit Contact</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainstyles.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <script src="${pageContext.request.contextPath}/js/jscript.js"></script>
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
                <li><a href="home">Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href="logout">Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>
<div id="content">
    <div class="contentDiv">
        <h4>Contact Information</h4>
        <form class="form-horizontal" action="editContact"
              onsubmit="return confirmChanges()" method="post">
            <div class="form-group">
                <label class="control-label col-sm-3">Phone Number: </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="phoneNumber"
                           name="inputPhoneNumber" value="${contactInfo.phoneNumber}"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">First Name: </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="firstName"
                           name="inputFirstName" value="${contactInfo.firstName}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Last Name: </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="lastName"
                           name="inputLastName" value="${contactInfo.lastName}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Email: </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="email"
                           name="inputEmail" value="${contactInfo.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-3">Address: </label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="address"
                           name="inputAddress" value="${contactInfo.address}">
                </div>
            </div>
            <input type="hidden" name="cid" value="${param.cid}"/>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-3">
                    <input class="btn btn-lg btn-block btn-primary" type="submit"
                           value="Save Changes">
                </div>
            </div>
        </form>
    </div>
</div>
<my-footer></my-footer>

<!-- Modules -->
<script src="js/app.js"></script>
<!-- Directives -->
<script src="js/directives/myFooter.js"></script>
</body>
</html>
