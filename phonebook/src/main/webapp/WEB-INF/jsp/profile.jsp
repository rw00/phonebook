<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Profile</title>
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
                <li class="active"><a>Profile</a></li>
                <li><a href=${pageContext.request.contextPath}/logout>Log
                    Out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="content">
    <div class="contentDiv">
        <h4>Profile Information</h4>
        <div data-ng-init='user=${userInfo}'>
            <user-profile info="user"></user-profile>
        </div>
    </div>

    <div class="contentDiv">
        <h4>Delete My Account</h4>
        <p>
            <small> Deleting your account is an irreversible action and
                will remove all your contacts. Type in your current password to
                remove all your account information from our database. </small>
        </p>
        <form class="form-horizontal" action="deleteAccount"
              onsubmit="return confirmDeleteAccount()">
            <div class="form-group">
                <label class="control-label col-sm-1" for="passField">Password:</label>
                <div class="col-sm-3 col-sm-offset-3">
                    <input type="password" name="currentPass" id="passField"
                           class="form-control" required>
                </div>
            </div>
            <button type="submit" id="delAccount" class="btn btn-default">Delete
                Account
            </button>
        </form>
    </div>
</div>
<my-footer></my-footer>

<!-- Modules -->
<script src=${pageContext.request.contextPath}/js/app.js></script>
<!-- Directives -->
<script
        src=${pageContext.request.contextPath}/js/directives/myFooter.js></script>
<script
        src=${pageContext.request.contextPath}/js/directives/userProfile.js></script>
</body>
</html>
