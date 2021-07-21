function GotoUpload(){
    if(sessionStorage.length===0){
        alert("Please Login");
        window.location.href = "/login"
    } else{
        window.location.href = "/uploadphoto"
    }
}

function Gotoprofile(){
    var user = JSON.parse(sessionStorage.getItem("user"));
    if(sessionStorage.length===0){
        alert("Please Login");
        window.location.href = "/login"
    } else{
        window.location.href = "/profile/"+user.id;
    }
}

function Logout(){
    if(sessionStorage.getItem("user").length!==0){
        sessionStorage.clear();
        window.location.href = "/login"
        alert("you are logout");
    }else{
        alert("you are not login");
    }
}