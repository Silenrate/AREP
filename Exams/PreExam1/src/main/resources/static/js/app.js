let button = document.getElementById("button-calculator");

const url = "https://areppractica1.herokuapp.com/result?values=";
// LOCAL: "http://localhost:4567/result?values="
// REMOTO: "https://areppractica1.herokuapp.com/result?values="

const clickButtonHandler = () => {
    let data = document.getElementById("input").value;
    axios.get(url+data)
        .then(res => {
            let result = res.data;
            document.getElementById("listOrdered").innerHTML = "["+result.values+"]";
            document.getElementById("listSum").innerHTML = result.sum ;
            document.getElementById("listAverage").innerHTML = result.average ;
        })
        .catch(err => {
            console.log(err);
        });
}

button.addEventListener("click", clickButtonHandler);