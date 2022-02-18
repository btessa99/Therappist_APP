let ws;

function connect(username, chatter) {

    let host = document.location.host;
    let pathname = document.location.pathname;
    const url = "ws://" +host  + pathname + "/chat";
    ws = new WebSocket(url);
    let rightNow = new Date().getTime();

    ws.onmessage = function(event) {
        let log = document.getElementById("chat-holder");
        let message = JSON.parse(event.data);
        if(message.sender !== chatter){
            alert("new message from " + message.sender);
            return;
        }
        //create a new div element for the received message and put the text int it
        let message_div = document.createElement('div');
        message_div.className = "message_received";
        let message_text = document.createElement('p');
        message_text.className = "message_text";
        message_text.textContent = message.text;
        let message_label = document.createElement('label');
        message_label.textContent = message.sender;
        message_label.className = "color"
        message_div.append(message_label)
        message_div.append(message_text)
        log.append(message_div);
    };

    ws.onopen = function() {
        let json = JSON.stringify({
            "sender":username,
            "receiver":username,
            "text":"My username is " + username,
            "timestamp": rightNow
        });
        ws.send(json);
    }



}

function send(sender, receiver) {

    let textfield = document.getElementById("msg");
    let content = textfield.value;

    if(content.value === "")
        return;

    let json = JSON.stringify({
        "sender":sender,
        "receiver":receiver,
        "text":content,
        "timestamp": new Date().getTime()
    });

    textfield.value = "";
    ws.send(json);
    let log = document.getElementById("chat-holder");
    let message_div = document.createElement('div');
    message_div.className = "message_sent";
    let message_text = document.createElement('p');
    message_text.className = "message_text";
    message_text.textContent = content;
    let message_label = document.createElement('label');
    message_label.textContent = "Me";
    message_label.className = "color"
    message_div.append(message_label)
    message_div.append(message_text);
    log.append(message_div);
}