let stompClient = null;
let chatContainer = null;
let sender = null;

function connect() {
	const socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, () => {
		console.log("Connected to WebSocket");

		stompClient.subscribe('/user/queue/messages', (message) => {
			const messageObject = JSON.parse(message.body);
			const sender = messageObject.sender;
			displayMessage(messageObject, 'sender');
			indicateNewMessage(sender);
		});
	});
}

function sendPrivateMessage() {
	const content = document.getElementById('messageInput').value;
	
	if (content && stompClient) {
		const message = {
			sender: sender,
			recipient: "admin",
			content: content,
			timestamp: new Date().toISOString()
		};
		stompClient.send('/app/private-message', {}, JSON.stringify(message));
		messageInput.value = '';
		displayMessage(message, 'repaly');
	}
}

function displayMessage(message, style) {
	const messageElement = document.createElement('li');
	messageElement.classList.add(style);

	const content = document.createElement('p');
	content.textContent = message.content;
	messageElement.appendChild(content);

	const time = document.createElement('span');
	time.textContent = formatISOString(message.timestamp);
	time.classList.add('time');
	messageElement.appendChild(time);

	chatContainer.appendChild(messageElement);
}

function formatISOString(isoString) {
	if (!isoString.includes('Z')) {
		isoString += 'Z';
	}
	const date = new Date(isoString);
	return date.toLocaleString();
}

document.addEventListener('DOMContentLoaded', () => {
	chatContainer = document.getElementById('msg-list');
	userList = document.getElementById('active-user-list');
	sender = document.getElementById('senderInput').value;
	connect();
});
