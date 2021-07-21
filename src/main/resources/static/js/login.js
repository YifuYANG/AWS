function Login(){
    const info = {
        username : document.getElementById("username").value,
        password : document.getElementById("password").value,
    };
    const xhr= new XMLHttpRequest();
    xhr.open("POST", "/login",false)
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

function Redirect(){
    window.location.href = "/register";
}

function Check(){
    if(sessionStorage.getItem("user").length!==0){
        alert("Logout before you can login new account");
        window.location.href = "/";
    }
}