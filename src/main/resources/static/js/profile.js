function Userinfo(){
    if(sessionStorage.length!==0){
        var user=JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("username").innerHTML = user.username;
        document.getElementById("email").innerHTML = user.email;
    }
}

function AddPhoto(){
    alert(JSON.parse(sessionStorage.getItem("user")).id);
    const photo = {
        name : document.getElementById("name").value,
        ownerid : JSON.parse(sessionStorage.getItem("user").id),
        image : document.getElementById("customFile").files,
        description : document.getElementById("description").value
    };
    alert(document.getElementById("customFile").value);
    const xhr= new XMLHttpRequest();
    xhr.open("POST", "/addphoto",false)
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onreadystatechange = function() {
        alert("I am working2");
        if (xhr.readyState === 4) {
            if (xhr.getResponseHeader('content-type') === 'application/json') {
                var result = JSON.parse(xhr.responseText);
                if (result.status === 'success') {
                    alert("Upload completed");
                } else {
                    alert(result.msg);
                }
            }
        }
    }
    xhr.send(JSON.stringify(photo));
}