function Id(){
    if(sessionStorage.length!==0){
        var user = JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("id").value = user.id;
    } else{
        alert("you need to login before upload your photo");
        window.location.href = "/login"
    }
}

function Back(){
    window.location.href = "/"
}