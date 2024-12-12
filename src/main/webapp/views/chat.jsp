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
    <div id="chatWindow" class="chat-window"></div>
    <input type="hidden" id="senderInput" value="${currentUser }" readonly />
    <c:if test="${currentUser == 'admin'}">
    	<input type="text" id="recipientInput" placeholder="Recipient (e.g., admin)" />    
    </c:if>
    <input type="text" id="messageInput" placeholder="Type your message..." />
    <button onclick="sendPrivateMessage()">Send</button>

    <script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
    <script src="/chat.js"></script>
</body>
</html>