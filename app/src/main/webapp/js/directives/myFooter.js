app.directive("myFooter", function () {
  return {
    restrict: "E",
    scope: {},
    template: '<footer class="footer"><div class="container-fluid">'
        + '<p class="footer-text">All rights reserved. '
        + '<span class="title">RW! </span> 2015 &copy;</p>'
        + '<p class="footer-text">Developed by '
        + '<a href="mailto:raafatwahb@gmail.com">Raafat Waheb</a></p>'
        + '</div></footer>'
  };
});
