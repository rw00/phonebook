$(document).ready(function () {
  let $btn = $("button");
  $btn.fadeTo("fast", 0.5);
  $btn.attr("disabled", "disabled");
  let $phoneInput = $("#inputPhoneNumber");
  let $passInput = $("#inputPassword");
  $phoneInput.keyup(validateInput);
  $passInput.keyup(validateInput);
});

function validateInput() {
  let $btn = $("button");
  let $phoneInput = $("#inputPhoneNumber");
  let $passInput = $("#inputPassword");
  if ($phoneInput.val().trim() !== "" && $passInput.val().trim() !== "") {
    $btn.fadeTo("fast", 1);
    $btn.removeAttr("disabled");
  } else {
    $btn.fadeTo("fast", 0.5);
    $btn.attr("disabled", "disabled");
  }
}
