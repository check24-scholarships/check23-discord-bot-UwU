let sendToServer;
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
        //append send button
        const sendButton = document.createElement("button");
        sendButton.className = "form-button";
        sendButton.setAttribute("onclick", "send()");
        sendButton.innerHTML = "SEND"
        form.append(sendButton);
    }
}
async function send() {
    //change button so user sees that button was clicked
    let button = document.querySelector(".form-button").innerHTML = "sending..."
    fetch("https://check23.lcarilla.de/memes/meme", {
        method: "POST",
        body: JSON.stringify({ image: sendToServer, title: "bruh" }),
        headers: { "Content-type": "application/json" }
    }).then(e => {
        //reset
        document.querySelector(".upload").innerHTML = `
                <a class = "form-button">UPLOAD</a>
                <input type="file" style="display:none" oninput="upload()" id="PFPInput">
                `;
    })
}
