<%@ page errorPage="errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-ng-app="PhoneBookApp">
<head>
    <title>Admin: Emailing System</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/default.css">
    <link rel="shortcut icon"
          href="${pageContext.request.contextPath}/images/favicon.ico">
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="https://code.jQuery.com/jquery-latest.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.8.2/angular.min.js"></script>
    <script>
      $(document).ready(function () {
        let year = new Date().getFullYear();
        // load years
        for (let i = year; i <= 2040; i++) {
          $("#syear").append("<option value=" + i + ">" + i + "</option>");
          $("#eyear").append("<option value=" + i + ">" + i + "</option>");
        }
        // load months
        for (let i = 1; i <= 12; i++) {
          $("#smonth").append("<option value=" + i + ">" + i + "</option>");
          $("#emonth").append("<option value=" + i + ">" + i + "</option>");
        }
        // load days
        for (let i = 1; i <= 31; i++) {
          $("#sday").append("<option value=" + i + ">" + i + "</option>");
          $("#eday").append("<option value=" + i + ">" + i + "</option>");
        }

        let today = new Date();
        $("#syear").val(today.getFullYear());
        $("#smonth").val(today.getMonth() + 1);
        $("#sday").val(today.getDate());
        $("#eyear").val(today.getFullYear());
        $("#emonth").val(today.getMonth() + 1);
        $("#eday").val(today.getDate());
      });
      $(function () {
        $("#syear").change(function () {
          checkMonth("s");
        });
        $("#smonth").change(function () {
          checkMonth("s");
        });
        $("#eyear").change(function () {
          checkMonth("e");
        });
        $("#emonth").change(function () {
          checkMonth("e");
        });
      });

      function checkMonth(se) {
        let month = $("#" + se + "month").val();
        let year = $("#" + se + "year").val();
        let days = 31;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
            || month == 10 || month == 12) {
          days = 31;
        } else if (month == 2 && ((year % 4 == 0) && (year % 100 != 0))
            || (year % 400 == 0)) {
          days = 29;
        } else if (month == 2) {
          days = 28;
        } else {
          days = 30;
        }
        $("#" + se + "day").empty();
        for (let i = 1; i <= days; i++) {
          $("#" + se + "day").append(
              "<option value=" + i + ">" + i + "</option>");
        }
      }
    </script>
</head>
<body>
<nav id="nav" class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"> Welcome to <span class="title">RW!</span></a>
        </div>
        <div id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="profile">Profile</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Log
                    Out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="content">
    <div class="container">
        <form class="form-horizontal" action="setSchedule" method="post">
            <h5>Select start date and time:</h5>
            <div class="form-group" id="sdatetime">
                <div class="col-sm-2">
                    <select name="syear" id="syear" class="form-control"></select>
                </div>
                <div class="col-sm-2">
                    <select name="smonth" id="smonth" class="form-control"></select>
                </div>
                <div class="col-sm-2">
                    <select name="sday" id="sday" class="form-control"></select>
                </div>
                <div class="col-sm-offset-1 col-sm-3">
                    <input name="stime" type="text" class="form-control"
                           placeholder="Time  (e.g. 08:30)" required>
                </div>
            </div>
            <br>
            <h5>Select end date and time:</h5>
            <div class="form-group" id="edatetime">
                <div class="col-sm-2">
                    <select name="eyear" id="eyear" class="form-control"></select>
                </div>
                <div class="col-sm-2">
                    <select name="emonth" id="emonth" class="form-control"></select>
                </div>
                <div class="col-sm-2">
                    <select name="eday" id="eday" class="form-control"></select>
                </div>
                <div class="col-sm-offset-1 col-sm-3">
                    <input name="etime" type="text" class="form-control"
                           placeholder="Time  (e.g. 16:45)" required>
                </div>
            </div>
            <br>
            <h5>Select the days:</h5>
            <div class="form-group" onchange="checkDays()">
                <div class="checkbox">
                    <label for="mon" class="col-sm-1"><input type="checkbox" id="mon" class="days"
                                                             name="mon">Mon</label>
                    <label for="tue" class="col-sm-1"><input type="checkbox" id="tue" class="days"
                                                             name="tue">Tue</label>
                    <label for="wed" class="col-sm-1"><input type="checkbox" id="wed" class="days"
                                                             name="wed">Wed</label>
                    <label for="thu" class="col-sm-1"><input type="checkbox" id="thu" class="days"
                                                             name="thu">Thu</label>
                    <label for="fri" class="col-sm-1"><input type="checkbox" id="fri" class="days"
                                                             name="fri">Fri</label>
                    <label for="sat" class="col-sm-1"><input type="checkbox" id="sat" class="days"
                                                             name="sat">Sat</label>
                    <label for="sun" class="col-sm-1"><input type="checkbox" id="sun" class="days"
                                                             name="sun">Sun</label>
                    <label for="allDays" class="col-sm-2"><input type="checkbox" id="allDays"
                                                                 name="allDays" checked>All
                        Days</label>
                </div>
            </div>
            <br>
            <h5>Enter the interval of time between emails:</h5>
            <div class="form-group">
                <div class="col-sm-2">
                    <input name="hrs" type="number" class="form-control"
                           placeholder="Hours" required>
                </div>
                <div class="col-sm-2">
                    <input name="mins" type="number" class="form-control"
                           placeholder="Minutes" required>
                </div>
            </div>
            <br> <input type="hidden" name="uid" value="${param.uid}">
            <div class="form-group">
                <div class="col-sm-2">
                    <button class="btn btn-primary btn-block" type="submit">Submit</button>
                </div>
            </div>
        </form>
        <c:if test="${not empty schedule}">
            <form id="cancelSchedule" class="form-inline ctrls"
                  action="cancelSchedule" method="post">
                <input type="hidden" name="uid" value="${param.uid}">
                <div class="col-sm-2">
                    <button class="btn btn-link" type="submit">Cancel</button>
                </div>
            </form>
        </c:if>
    </div>
</div>
<my-footer></my-footer>

<!-- Modules -->
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<!-- Directives -->
<script src="${pageContext.request.contextPath}/js/directives/myFooter.js"></script>
</body>
</html>
