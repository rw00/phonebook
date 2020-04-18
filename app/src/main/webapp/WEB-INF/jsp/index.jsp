<%@ page errorPage="errorPage.jsp" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Welcome to RW</title>
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
<div class="jumbotron">
    <div class="container">
        <div class="container">
            <h2 id="greeting_msg"></h2>
            <br>
            <p id="disclaimer_msg">${disclaimer}</p>
        </div>
    </div>
</div>
<script>
  let greetingMsg = "Welcome to Contacts App!";
  let array = greetingMsg.split("");
  let element = document.getElementById("greeting_msg");

  function animateTyping() {
    if (array.length > 0) {
      if (array.length === greetingMsg.length) {
        element.innerHTML = "";
      }
      element.innerHTML += array
      .shift();
      setTimeout("animateTyping()", 80);
    } else {
      setTimeout("animateTyping()", 10000);
      array = greetingMsg.split("");
    }
  }

  animateTyping();
</script>
<my-footer></my-footer>

<!-- Modules -->
<script src="js/app.js"></script>
<!-- Directives -->
<script src="js/directives/myFooter.js"></script>
</body>
</html>
