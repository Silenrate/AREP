let button = document.getElementById("button");

const url = "https://ec2-18-215-170-151.compute-1.amazonaws.com:7000";

const clickButtonHandler = () => {
    const user = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };
    const headers = {
        'Content-Type': 'application/json'
    }

    axios.post(url+"/login",user,{headers: headers})
        .then(res => {
            window.location.href = url+"/protected/index.html";
        })
        .catch(error => {
            alert(error);
        });
};

button.addEventListener("click", clickButtonHandler);