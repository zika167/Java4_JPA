var websocket = null; // biến giữ đối tượng WebSocket

function init() {
    // Mở kết nối đến chat server
    var wsUrl = 'ws://localhost:8080/text/chat';
    console.log("Connecting to: " + wsUrl);
    websocket = new WebSocket(wsUrl);
    
    // Xử lý sự kiện chấp nhận kết nối từ server
    websocket.onopen = function(resp) {
        console.log("onopen", resp);
    }
    
    // Xử lý sự kiện nhận tin nhắn chat từ server
    websocket.onmessage = function(resp) {
        var message = resp.data;
        var html = document.getElementById('messages').innerHTML;
        document.getElementById('messages').innerHTML = `${html}<p>${message}</p>`;
        console.log("onmessage", resp.data);
    }
    
    // Xử lý sự kiện lỗi từ server
    websocket.onerror = function(resp) {
        alert('An error occured, closing application');
        console.log("onerror", resp);
    }
    
    // Xử lý sự kiện đóng kết nối từ server
    websocket.onclose = function(resp) {
        alert(resp.reason || 'Goodbye');
        console.log("onclose", resp);
    }
}

// Gửi tin nhắn chat đến server, được gọi khi nhấp vào nút Send
function send() {
    var message = document.getElementById("message").value;
    if (message.trim() !== "") {
        websocket.send(message);
        document.getElementById("message").value = "";
    }
}

// Xử lý sự kiện nhấn phím Enter để gửi tin nhắn
function handleKeyPress(event) {
    if (event.key === "Enter" || event.keyCode === 13) {
        send();
    }
}
