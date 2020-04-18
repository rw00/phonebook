<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Admin Profile</title>
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
        <div data-ng-init='admin=${adminInfo}'>
            <admin-profile info="admin"></admin-profile>
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
        src=${pageContext.request.contextPath}/js/directives/adminProfile.js></script>
</body>
</html>
