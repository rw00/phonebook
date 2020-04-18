/**
 *
 */
app.directive("userInfo", function () {
    return {
        restrict: 'E',
        scope: {
            info: '='
        },
        templateUrl: '../views/userInfo.html'
    };
});
