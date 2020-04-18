<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error 405</title>
    <meta charset="utf-8">
    <link rel="stylesheet"
          href=${pageContext.request.contextPath}/css/mainstyles.css>
</head>
<body>
<div id="content">
    <div class="contentDiv">
        <h4>Error 405</h4>
        <p>An error of code 405 occurred!</p>
        <p>So this error "405 Method Not Allowed" occurred.</p>
        <p>Please check the web address you entered.</p>

        <p>
            Click <a href=${pageContext.request.contextPath}/index>here</a> to
            go to the main page!
        </p>
    </div>
</div>
</body>
</html>
