let sendToServer; //yay gobal variables are great
function upload() {
    const file = document.querySelector("#PFPInput").files[0];
    const reader = new FileReader();
    if (file) {
        reader.readAsDataURL(file);
    }
    reader.onloadend = function () {
        //get img data
        const lastIndex = reader.result.lastIndexOf(',');
        const toSave = reader.result.slice(lastIndex + 1);
        sendToServer = toSave
        const img = reader.result;
        //construct img 
        const uploadedImg = document.createElement("img");
        uploadedImg.src = img;
        uploadedImg.className = "uploaded-img"
        //apend img
        const form = document.querySelector(".upload");
        form.prepend(uploadedImg);
        //delete input and button
        document.querySelector(".form-button").remove();
        document.querySelector("#PFPInput").remove();
        //append send button and message container
        const titleAndSendContainer = document.createElement("div");
        titleAndSendContainer.className = "send-container"
        form.append(titleAndSendContainer)
        const sendButton = document.createElement("button");
        sendButton.className = "form-button";
        sendButton.setAttribute("onclick", "send()");
        sendButton.innerHTML = "SEND"
        titleAndSendContainer.append(sendButton);
        const titleInputField = document.createElement("input");
        titleInputField.id = "titleInput";
        titleAndSendContainer.prepend(titleInputField)
    }
}
async function send(memetitle) {
    //change button so user sees that button was clicked
   document.querySelector(".form-button").innerHTML = "sending..."
    fetch("https://check23.lcarilla.de/memes/meme", {
        method: "POST",
        body: JSON.stringify({ image: sendToServer, title: document.querySelector("#titleInput").value }),
        headers: { "Content-type": "application/json" }
    }).then(e => {
        //reset
        location.reload()
    })
}
