/**
 *
 */
app.directive("adminInfo", function () {
    return {
        restrict: 'E',
        scope: {
            info: '='
        },
        templateUrl: '../views/adminInfo.html'
    };
});
