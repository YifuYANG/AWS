function Userinfo(){
    if(sessionStorage.length!==0){
        var user=JSON.parse(sessionStorage.getItem("user"));
        document.getElementById("username").innerHTML = user.username;
        document.getElementById("email").innerHTML = user.email;
    }
}

function Id(){
    var userid = JSON.parse(sessionStorage.getItem("user"));
    document.getElementById("id").value = userid.id;
}