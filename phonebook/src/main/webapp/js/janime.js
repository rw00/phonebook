$(document).ready(function () {
    $("button").fadeTo('fast', 0.5);
    $('button').attr('disabled', 'disabled');
    var phoneInput = $("#inputPhoneNumber");
    var passInput = $("#inputPassword");
    phoneInput.keyup(function () {
        if (phoneInput.val().trim() !== "" && passInput.val().trim() !== "") {
            $('button').fadeTo('fast', 1);
            $('button').removeAttr('disabled');
        } else {
            $('button').fadeTo('fast', 0.5);
            $('button').attr('disabled', 'disabled');
        }
    });
    passInput.keyup(function () {
        if (phoneInput.val().trim() !== "" && passInput.val().trim() !== "") {
            $('button').fadeTo('fast', 1);
            $('button').removeAttr('disabled');
        } else {
            $('button').fadeTo('fast', 0.5);
            $('button').attr('disabled', 'disabled');
        }
    });
});
