    let stompClient = null;

    function connect() {
        const socket = new SockJS('/chat'); // Connects to /chat WebSocket endpoint
        stompClient = Stomp.over(socket);  // Wraps WebSocket with STOMP protocol

        stompClient.connect({}, () => {
            console.log("Connected to WebSocket");

            // Subscribe to private messages for the current user
            stompClient.subscribe('/user/queue/messages', (message) => {
                const messageBody = JSON.parse(message.body);
                const messageElement = document.createElement('li');
                messageElement.textContent = `From ${messageBody.sender}: ${messageBody.content}`;
                document.getElementById('messages').appendChild(messageElement);
            });
        });
    }

    function sendPrivateMessage() {
        const recipientInput = document.getElementById('recipientInput').value;
        const messageInput = document.getElementById('messageInput').value;

        if (recipientInput && messageInput && stompClient) {
            const message = {
                sender: "Customer", // Replace with dynamically set sender
                recipient: recipientInput,
                content: messageInput
            };
            stompClient.send('/app/private-message', {}, JSON.stringify(message)); // Send message to /app/private-message
        }
    }

    connect(); // Initiate WebSocket connection
