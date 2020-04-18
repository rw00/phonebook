app.directive("contactInfo", function () {
  return {
    restrict: "E", // creates new element
    scope: {
      info: "="
    },
    templateUrl: "../views/contactInfo.html" // location of the template
  };
});
