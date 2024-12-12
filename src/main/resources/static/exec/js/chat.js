let stompClient = null;
let currentRecipient = null; // Keeps track of the current recipient

function connect() {
	const socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, () => {
		console.log("Connected to WebSocket");

		stompClient.subscribe('/user/queue/messages', (message) => {
			displayMessage(JSON.parse(message.body), 'sender');
		});
	});
}

function displayMessage(message, style) {
	const messageElement = document.createElement('li');
	messageElement.classList.add(style);

	const content = document.createElement('p');
	content.textContent = message.content;
	messageElement.appendChild(content);

	const time = document.createElement('span');
	time.textContent =  formatDateTime(message.timestamp);
	time.classList.add('time');
	messageElement.appendChild(time);
	
	document.getElementById('msg-list').appendChild(messageElement);
}


function sendPrivateMessage() {
	const content = document.getElementById('messageInput').value;

	if (currentRecipient && content && stompClient) {
		const message = {
			recipient: currentRecipient,
			content: content,
			timestamp: new Date()
		};
		stompClient.send('/app/admin/private-message', {}, JSON.stringify(message));
		messageInput.value = '';
		displayMessage(message, 'repaly');
	}
}

function selectRecipient(username) {
	currentRecipient = username;
	document.getElementById('currentRecipient').textContent = currentRecipient;
}

function formatDateTime(time) {
	if (!(time instanceof Date)) {
        time = new Date(time);
    }
	const year = time.getFullYear();
	const month = String(time.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
	const day = String(time.getDate()).padStart(2, '0');
	const hours = String(time.getHours()).padStart(2, '0');
	const minutes = String(time.getMinutes()).padStart(2, '0');

	return `${day}-${month}-${year} ${hours}:${minutes}`;
}

connect();