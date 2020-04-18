<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error 404</title>
    <meta charset="utf-8">
    <link rel="stylesheet"
          href=${pageContext.request.contextPath}/css/mainstyles.css>
</head>
<body>
<div id="content">
    <div class="contentDiv">
        <h4>Error 404</h4>
        <p>An error of code 404 occurred.</p>
        <p>The requested page does not exist!</p>
        <p>
            Click <a href=${pageContext.request.contextPath}/index>here</a> to
            go to the main page!
        </p>
    </div>
</div>
</body>
</html>
