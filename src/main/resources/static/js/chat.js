var ws;

function connect() {
    var username = document.getElementById("username").value;

    var host = document.location.host;
    var pathname = document.location.pathname;
    const url = "ws://" +host  + pathname + "/chat";
    alert(url);
    ws = new WebSocket(url);

    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };
}

function send() {

    var textfield = document.getElementById("msg");
    var content = textfield.value;

    if(content.value === "")
        return;

    var json = JSON.stringify({
        "sender":document.getElementById("username"),
        "receiver":document.getElementById("endpoint"),
        "text":content,
        "timestamp": new Date().getTime()
    });

    textfield.value = "";
    ws.send(json);
}