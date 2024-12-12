<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>
<body>
    <h1>Private Chat</h1>
    <div id="chat">
        <ul id="messages"></ul>
    </div>
    <input type="hidden" id="senderInput" value="${currentUser }" />
    <c:if test="${currentUser == admin}">
    	<input type="text" id="recipientInput" placeholder="Recipient (e.g., admin)" />    
    </c:if>
    <input type="text" id="messageInput" placeholder="Type your message..." />
    <button onclick="sendPrivateMessage()">Send</button>

    <script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
    <script>
        let stompClient = null;

        function connect() {
            const socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, () => {
                console.log("Connected to WebSocket");

                // Subscribe to private messages
                stompClient.subscribe('/user/queue/messages', (message) => {
                    const messageBody = JSON.parse(message.body);
                    const messageElement = document.createElement('li');
                    messageElement.textContent = `From ${messageBody.sender}: ${messageBody.content}`;
                    document.getElementById('messages').appendChild(messageElement);
                });
            });
        }

        function sendPrivateMessage() {
        	const recipientInput = document.getElementById('recipientInput');
        	
            const sender = document.getElementById('senderInput').value;
            const recipient = (recipientInput === null) ? "admin" : recipientInput.value;
            const content = document.getElementById('messageInput').value;

            if (recipient && content && stompClient) {
                const message = {
                    sender: sender, // Replace with dynamically set sender
                    recipient: recipient,
                    content: content
                };
                stompClient.send('/app/private-message', {}, JSON.stringify(message));
                messageInput.value = '';
            }
        }

        // Connect on page load
        connect();
    </script>
</body>
</html>