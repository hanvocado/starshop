let stompClient = null;

function connect() {
	const socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, () => {
		console.log("Connected to WebSocket");

		stompClient.subscribe('/user/queue/messages', (message) => {
			displayMessage(JSON.parse(message.body));
		});
	});
}

function displayMessage(message) {
    const chatWindow = document.getElementById("chatWindow");

    // Create a new message element
    const messageElement = document.createElement("div");
    messageElement.classList.add("message");

    // Add sender name
    const senderElement = document.createElement("span");
    senderElement.classList.add("message-sender");
    senderElement.textContent = message.sender + ": ";

    // Add message content
    const contentElement = document.createElement("span");
    contentElement.classList.add("message-content");
    contentElement.textContent = message.content;

    messageElement.appendChild(senderElement);
    messageElement.appendChild(contentElement);

    chatWindow.appendChild(messageElement);

    // Scroll to the bottom of the chat window
    chatWindow.scrollTop = chatWindow.scrollHeight;
}


function sendPrivateMessage() {
	const recipientInput = document.getElementById('recipientInput');

	const sender = document.getElementById('senderInput').value;
	const recipient = (recipientInput === null) ? "admin" : recipientInput.value;
	const content = document.getElementById('messageInput').value;

	if (recipient && content && stompClient) {
		const message = {
			sender: sender,
			recipient: recipient,
			content: content,
			timestamp: new Date()
		};
		stompClient.send('/app/private-message', {}, JSON.stringify(message));
		messageInput.value = '';
		displayMessage(message);
	}
}

connect();