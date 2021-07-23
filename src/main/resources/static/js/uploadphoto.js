function Id(){
    if(sessionStorage.length!==0){
        var user = JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("id").value = user.id;
    } else{
        alert("you need to login before upload your photo");
        window.location.href = "/login"
    }

    if(JSON.parse(sessionStorage.getItem("user")).type===0){
        alert("User can not view this page")
        window.location.href = "/"
    }
}

function Back(){
    window.location.href = "/"
}