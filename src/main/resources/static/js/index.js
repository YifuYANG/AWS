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

function hideBoth()  {
     if(sessionStorage.length!==0){
         if(JSON.parse(sessionStorage.getItem("user")).type===0){
             //alert(sessionStorage.getItem("user").type===0)
             document.getElementById("one").style.visibility="hidden";
             document.getElementById("two").style.visibility="hidden";
             document.getElementById("four").style.visibility="hidden";
         } else{
             document.getElementById("four").style.visibility="hidden";
         }
    } else {
         document.getElementById("one").style.visibility="hidden";
         document.getElementById("two").style.visibility="hidden";
         document.getElementById("three").style.visibility="hidden";
     }
}

function Login(){
    window.location.href = "/login"
}