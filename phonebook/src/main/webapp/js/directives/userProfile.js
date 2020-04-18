/**
 *
 */
app.directive("userProfile", function () {
    return {
        restrict: 'E',
        scope: {
            info: '='
        },
        templateUrl: '../views/userProfile.html'
    };
});
