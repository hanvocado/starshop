let stompClient = null;
let currentRecipient = null;
let conversations = {};
let chatContainer = document.getElementById('msg-body');

function connect() {
	const socket = new SockJS('/chat');
	stompClient = Stomp.over(socket);

	stompClient.connect({}, () => {
		console.log("Connected to WebSocket");

		stompClient.subscribe('/user/queue/messages', (message) => {
			displayMessage(JSON.parse(message.body), 'sender');
		});

		stompClient.subscribe('/topic/active-users', (message) => {
			const activeUsers = JSON.parse(message.body);
			const userList = document.getElementById('active-user-list');
			userList.innerHTML = '';

			activeUsers.forEach(username => {
				const link = document.createElement('a');
				link.href = "javascript:;";
				link.onclick = () => selectRecipient(username);
				link.classList.add('recipient', 'd-flex', 'align-items-center');

				const innerDiv = document.createElement('div');
				innerDiv.classList.add('flex-grow-1', 'ms-3');

				const nameHeader = document.createElement('h3');
				nameHeader.textContent = username;
				innerDiv.appendChild(nameHeader);

				link.appendChild(innerDiv);
				userList.appendChild(link);
			});
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
	time.textContent = formatDateTime(message.timestamp);
	time.classList.add('time');
	messageElement.appendChild(time);
	
	const id = message.recipient == 'admin' ? message.sender : message.recipient;

	document.getElementById(`msg-list-${id}`).appendChild(messageElement);
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
	document.getElementById('currentRecipient').textContent = username;
	
	for (let key in conversations) {
		if (!key.includes(username)) {
			conversations[key].style.display = 'none';
		}
	}
	
	if (!conversations[username]) {
        // Create a new conversation for the recipient
        const conversation = document.createElement("ul");
        conversation.id = `msg-list-${username}`;
        chatContainer.appendChild(conversation);
        conversations[username] = conversation;
    } else {
		conversations[username].style.display = '';
	}
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