function checkNumber(id) {
    let v = document.getElementById(id);
    let k = document.getElementById(id + "Valid");
    if (isNaN(v.value)) {
        k.innerHTML = "Please type a number.";
    } else {
        k.innerHTML = "";
    }
}

function checkName(id) {
    let v = document.getElementById(id);
    let k = document.getElementById(id + "Valid");
    if (/^[a-zA-Z]+$/.test(v.value)) {
        k.innerHTML = "";
    } else {
        k.innerHTML = "Please type only letters here.";
    }
}

function checkEmail() {
    let e = document.getElementById("inputEmail");
    let regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    let k = document.getElementById("inputEmailValid");
    if (regex.test(e.value)) {
        k.innerHTML = "";
    } else {
        k.innerHTML = "Please enter a valid email.";
    }
}

function checkPassword() {
    let v = document.getElementById("inputPassword");
    let k = document.getElementById("inputPasswordValid");
    if (v.value.length < 8) {
        k.innerHTML = "Enter a password of at least 8 characters long."
    } else {
        k.innerHTML = "";
    }
}

function checkConfirmPass() {
    let pwd = document.getElementById("inputPassword");
    let confirm = document.getElementById("inputConfirmPass");
    let k = document.getElementById("inputConfirmPassValid");
    if (pwd.value !== confirm.value) {
        k.innerHTML = "Passwords don't match."
    } else {
        k.innerHTML = "";
    }
}

function checkValidForm() {
    let valid = document.getElementsByClassName("valid");
    for (let i = 0; i < valid.length; i++) {
        if (valid[i].innerHTML.trim() !== "") {
            alert("Please enter valid information.");
            return false;
        }
    }
    return true;
}

function checkDays() {
    let days = document.getElementsByClassName("days");
    let all = document.getElementById("allDays");
    if (all.checked) {
        for (let i = 0; i < days.length; i++) {
            days[i].checked = false;
        }
    }
    let allChecked = true;
    for (let i = 0; i < days.length; i++) {
        if (!days[i].checked) {
            allChecked = false;
            break;
        }
    }
    if (allChecked) {
        for (let i = 0; i < days.length; i++) {
            days[i].checked = false;
        }
        all.checked = true;
    }
}

function disabledFeature() {
    alert("This feature has been postponed for version 2.");
    return false;
}

function confirmDeleteAccount() {
    return confirm("Do you really want to delete your account?\nAll your contacts will be removed!");
}

function confirmDelUser() {
    return confirm("Are you sure you want to delete this user?");
}

function confirmDelContact() {
    return confirm("Do you want to delete this contact?");
}

function confirmChanges() {
    return confirm("Do you want to want to save changes?");
}

function checkDeleteBtn() {
    if (document.getElementById("checkDelete").checked
        && document.getElementById("passField").value !== "") {
        document.getElementById("delAccount").disabled = false;
    } else {
        document.getElementById("delAccount").disabled = true;
    }
}
