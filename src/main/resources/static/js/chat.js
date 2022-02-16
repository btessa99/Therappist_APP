var ws;

function connect(username) {

    var host = document.location.host;
    var pathname = document.location.pathname;
    const url = "ws://" +host  + pathname + "/chat";
    //alert(url);
    ws = new WebSocket(url);
    rightNow = new Date().getTime();

    ws.onmessage = function(event) {
        var log = document.getElementById("chat-holder");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.sender + " : " + message.text + "\n";
    };

    ws.onopen = function() {
        json = JSON.stringify({
            "sender":username,
            "receiver":username,
            "text":"My username is " + username,
            "timestamp": rightNow
        });
        alert(json);
        ws.send(json);
    }



}

function send(sender, receiver) {

    var textfield = document.getElementById("msg");
    var content = textfield.value;

    if(content.value === "")
        return;

    var json = JSON.stringify({
        "sender":sender,
        "receiver":receiver,
        "text":content,
        "timestamp": new Date().getTime()
    });

    textfield.value = "";
    ws.send(json);
}