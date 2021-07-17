function Check_1() {
    var check = false;
    var username = document.getElementById("username").value;
    if (username===""){
        document.getElementById("text_1").innerHTML = "  × username must not be empty";
        check = false;
    } else if (username.length > 10) {
        document.getElementById("text_1").innerHTML = "  × username must not longer than 10";
        check = false;
    } else {
        document.getElementById("text_1").innerHTML = "  √";
        check = true;
    }
    return check;
}
function Check_2() {
    var check = false;
    var password = document.getElementById("password").value;
    if (password.length < 6) {
        document.getElementById("text_2").innerHTML = "  × password must not less than 6";
        check = false;
    } else {
        document.getElementById("text_2").innerHTML = "  √";
        check = true;
    }
    return check;
}

function Check_3() {
    var check = false;
    var password = document.getElementById("password").value;
    var pwdc = document.getElementById("passwordcheck").value;
    if (password === pwdc) {
        check=true;
        document.getElementById("text_3").innerHTML = "  √";
    } else {
        document.getElementById("text_3").innerHTML = "  × different password re-enter";
        check = false;
    }
    return check;
}

function Check_4() {
    var check = false;
    var type = document.getElementById("type").value;
    if (type === "none") {
        document.getElementById("text_4").innerHTML = "  × type must not be empty";
        check = false;
    } else {
        document.getElementById("text_4").innerHTML = "  √";
        check=true;
    }
    return check;
}
function Check_5() {
    var check = false;
    var email = document.getElementById("email").value;
    if (email==="") {
        document.getElementById("text_5").innerHTML = "email must not be empty";
        check = false;
    } else {
        document.getElementById("text_5").innerHTML = "  √";
        check=true;
    }
    return check;
}

function Register()
{
    var ck = Check_1() && Check_2() && Check_3() && Check_4() && Check_5;
    if(ck===false) {
        window.location.href = "/register"
        alert("Please complete he form");
    } else {
        const info = {
            username : document.getElementById("username").value,
            password : document.getElementById("password").value,
            type : document.getElementById("type").value,
            name : document.getElementById("name").value,
            sex : document.getElementById("sex").value,
            email : document.getElementById("email").value,
            phone : document.getElementById("phone").value
        };
        const xhr= new XMLHttpRequest();
        xhr.open("POST", "/register",false)
        xhr.setRequestHeader("Content-Type","application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.getResponseHeader('content-type') === 'application/json') {
                    var result = JSON.parse(xhr.responseText);
                    if (result.status === 'success') {
                        let user = result.data
                        sessionStorage.setItem('user', JSON.stringify(user))
                        window.location.href = "/"
                    } else {
                        alert(result.msg);
                    }
                }
            }
        }
        xhr.send(JSON.stringify(info));
    }
}