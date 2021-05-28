let button = document.getElementById("button");

const url = "https://cceh2xgdx8.execute-api.us-east-2.amazonaws.com/Beta?value=";

const clickButtonHandler = () => {
	let temp = document.getElementById("temp").value;
	axios.get(url+temp)
		.then(res => {
			let result = document.getElementById("result");
			result.innerHTML = "In Celsius that is "+res.data.value;
        })
        .catch(error => {
            alert(error);
        });
};

button.addEventListener("click", clickButtonHandler);