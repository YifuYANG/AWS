function Register()
{
    //alert("Hello! I am step 1");
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
    xhr.open("POST", "/register").then(res => {
        let data = res.data
        if (data.status === 'success') {
            let user = data.data
            sessionStorage.setItem('user', JSON.stringify(user))
            window.location.href = "/"
        } else {
            alert(data.msg);
        }
    })
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.send(JSON.stringify(info));

    let data=res.data

    // let axiosConfig = {
    //     headers: {
    //         'Content-Type': 'application/json;charset=UTF-8',
    //         "Access-Control-Allow-Origin": "*",
    //     }
    // };
    // axios.post("/register", info,axiosConfig)
    //     .then(res => {
    //         alert("Hello! I am step max");
    //         let data = res.data
    //         if (data.status === 'success') {
    //             let user = data.data
    //             sessionStorage.setItem('user', JSON.stringify(user))
    //             window.location.href = "/"
    //         } else {
    //             alert(data.msg);
    //         }
    //     })
}

// const vm = new Vue({
//
// method:{
//     register(){
//         let _this = this
//         this.$refs['form'].validate((valid) => {
//             if (valid) {
//                 axios.post('/register', this.form)
//                     .then(res => {
//                         let data = res.data
//                         if (data.status === 'success') {
//                             let user = data.data
//                             sessionStorage.setItem('user', JSON.stringify(user))
//                             window.location.href = "/"
//                         } else {
//                             this.$message.error(data.msg)
//                         }
//
//                     })
//             }
//         });
//     }
// }
// })