let button = document.getElementById("button");

const url = window.location.href;

const getMessages = () => {
      $("#table > tbody").empty();
      axios.get(url+"lastMessages").then(res=>{
        console.log(res.data);
        res.data.map( message => {
            console.log(message);
            $("#table > tbody").append(
                    "<tr>" +
                    " <td>" + message.data + "</td>" +
                    "<td>" + message.date + "</td> " +
                    "</tr>"
            );
        });
      }).catch(error => {
        console.log(error);
      });
};

const clickButtonHandler = () => {
    let data = document.getElementById("input").value;
    let message = {
        "data": data
    };
    axios.post(url+"addNewMessage",message)
        .then(res => {
            getMessages()
        }).catch(error => {
            console.log(error);
        });
};

button.addEventListener("click", clickButtonHandler);