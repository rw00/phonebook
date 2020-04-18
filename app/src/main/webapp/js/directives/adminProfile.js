app.directive("adminProfile", function () {
  return {
    restrict: "E",
    scope: {
      info: "="
    },
    templateUrl: "../views/adminProfile.html"
  };
});
