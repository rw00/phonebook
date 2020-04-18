<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Internal Error or Exception</title>
    <meta charset="utf-8">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/default.css">
</head>
<body>
<div id="content">
    <script>
      function redirecting() {
        window.location = "${pageContext.request.contextPath}/index";
      }

      let text = document.getElementById("content");
      text.innerHTML = text.innerHTML
          + "<h2>An internal error occurred while processing.</h2>"
          + "<h3>Redirecting to home page in 5 seconds...</h3>";
      setTimeout("redirecting()", 5000);
    </script>
    <h4>Here is a description of the error:</h4>
    <c:choose>
        <c:when test="${url != null && exception != null}">
            <p>Error occurred at page: ${url}</p>
            <p>
                Exception details:
                <c:out value="${exception}"/>
            </p>
        </c:when>
        <c:otherwise>
            <p>Error occurred at page: ${pageContext.errorData.requestURI}</p>
            <p>
                Error status code:
                <c:out value="${pageContext.errorData.statusCode}"/>
            </p>
            <p>
                Exception details:
                <c:out value="${pageContext.exception}"/>
            </p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
