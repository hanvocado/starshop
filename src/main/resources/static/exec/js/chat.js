let stompClient = null;
let currentRecipient = null;
let conversations = {};
let chatContainer = null;
let userList = null;
const class_btn_status_info = 'btn-status-info';
const class_btn_ghost_secondary = 'btn-ghost-secondary';
const class_btn_ghost_primary = 'btn-ghost-primary';

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

		stompClient.subscribe('/topic/active-users', (message) => {
			const messageBody = JSON.parse(message.body);
			if (messageBody.action === 'CONNECTED') {
				addActiveUser(messageBody.user);
			} else {
				removeActiveUser(messageBody.user);
			}
		});
	});
}

function addActiveUser(username) {
	const chatUser = document.getElementById(`chat-${username}`);
	if (chatUser == null) {
		const link = document.createElement('a');
		link.href = "javascript:;";
		link.onclick = () => selectRecipient(username);
		link.classList.add('recipient', 'd-flex', 'btn', class_btn_ghost_secondary);
		link.innerText = username;
		link.id = `chat-${username}`;

		userList.appendChild(link);
	}
}

function removeActiveUser(username) {
	const chatUser = document.getElementById(`chat-${username}`);
	if (chatUser) {
		chatUser.remove();
	}
}

function indicateNewMessage(user) {
	if (user != currentRecipient) {
		const chat_user = document.getElementById(`chat-${user}`);
		const indicator = document.createElement('span');
		indicator.classList.add('btn-status', class_btn_status_info);
		indicator.innerText = '+';
		chat_user.appendChild(indicator);
	}
}

function removeIndicatorNewMessage(user) {
	const linkElement = document.getElementById(`chat-${user}`);
	const spanElement = linkElement.querySelector('span');
	if (spanElement) {
		spanElement.remove();
	}
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

	const client = message.recipient == 'admin' ? message.sender : message.recipient;

	const messages_list = document.getElementById(`msg-list-${client}`);
	if (messages_list == null) {
		createConversation(client);
	}
	document.getElementById(`msg-list-${client}`).appendChild(messageElement);
}

function createConversation(username) {
	const conversation = document.createElement("ul");
	conversation.id = `msg-list-${username}`;
	chatContainer.appendChild(conversation);
	conversations[username] = conversation;
	conversation.style.display = 'none';
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
	updateCurrentRecipient(username);

	for (let key in conversations) {
		if (!key.includes(username)) {
			conversations[key].style.display = 'none';
		}
	}

	if (!conversations[username]) {
		createConversation(username);
	}
	conversations[username].style.display = '';
	removeIndicatorNewMessage(username);
}

function updateCurrentRecipient(newRecipient) {
	document.getElementById('currentRecipient').textContent = newRecipient;
	
	const linkElement = document.getElementById(`chat-${newRecipient}`);	
	if (linkElement.classList.contains(class_btn_ghost_secondary)) {
        linkElement.classList.replace(class_btn_ghost_secondary, class_btn_ghost_primary);
    }
    
    const currentActiveLink = document.getElementById(`chat-${currentRecipient}`);	
	if (currentActiveLink && currentActiveLink.classList.contains(class_btn_ghost_primary)) {
        currentActiveLink.classList.replace(class_btn_ghost_primary, class_btn_ghost_secondary);
    }
    
	currentRecipient = newRecipient;
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

document.addEventListener('DOMContentLoaded', () => {
	chatContainer = document.getElementById('msg-body');
	userList = document.getElementById('active-user-list');
	connect();
});
