const time = document.getElementById("time");

time.innerText = "Loading";

const url = "https://ec2-18-215-170-151.compute-1.amazonaws.com:7000";

axios.get(url+"/time")
    .then(res => {
        time.innerText = res.data.actualTime;
    })
    .catch(error => {
        alert(error);
    })