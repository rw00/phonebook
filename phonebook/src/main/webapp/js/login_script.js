$(document).ready(function () {
    var $btn = $("button");
    $btn.fadeTo("fast", 0.5);
    $btn.attr("disabled", "disabled");
    var phoneInput = $("#inputPhoneNumber");
    var passInput = $("#inputPassword");
    phoneInput.keyup(function () {
        if (phoneInput.val().trim() !== "" && passInput.val().trim() !== "") {
            $btn.fadeTo("fast", 1);
            $btn.removeAttr("disabled");
        } else {
            $btn.fadeTo("fast", 0.5);
            $btn.attr("disabled", "disabled");
        }
    });
    passInput.keyup(function () {
        if (phoneInput.val().trim() !== "" && passInput.val().trim() !== "") {
            $btn.fadeTo("fast", 1);
            $btn.removeAttr("disabled");
        } else {
            $btn.fadeTo("fast", 0.5);
            $btn.attr("disabled", "disabled");
        }
    });
});
