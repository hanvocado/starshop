<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>

<link href="/shop/css/custom-style.css" type="stylesheet" />
<link href="/shop/images/favicon.ico " rel="shortcut icon"
	type="image/x-icon">

<!-- jQuery -->
<script src="/shop/js/jquery-3.5.1.slim.min.js" type="text/javascript"></script>

<!-- Bootstrap -->
<script src="/shop/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/shop/js/popper.min.js" type="text/javascript"></script>
<link href="/shop/css/bootstrap.min.css" type="text/css"
	rel="stylesheet">

<!-- FontAwesome 5 -->
<link href="/shop/fonts/fontawesome/css/all.min.css" type="text/css"
	rel="stylesheet">

<!-- Google Fonts -->
<link
	href="//fonts.googleapis.com/css2?family=EB+Garamond:ital,wght@0,400;0,500;0,600;0,615;0,700;0,800;1,400;1,500;1,600;1,615;1,700;1,800&family=Quicksand:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Custom Style -->
<link href="/shop/css/style.css" type="text/css" rel="stylesheet">

<link rel="stylesheet" href="/chat.css">

<!-- Custom JavaScript -->
<script src="/shop/js/script.js" type="text/javascript"></script>

<!-- Slick Slider -->
<link rel="stylesheet" type="text/css" href="/shop/css/slick.css" />
<script type="text/javascript" src="/shop/js/slick.min.js"></script>

<!-- Toast Message Style -->
<link href="/shop/css/bootoast.min.css" type="text/css" rel="stylesheet">

<meta charset="UTF-8">

<title>Chat</title>

</head>

<body>
	<input type="hidden" id="senderInput" value="${currentUser }" readonly />

	<!-- char-area -->
	<section class="message-area">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="chat-area">
						<!-- chatbox -->
						<div class="chatbox">
							<div class="modal-dialog-scrollable">
								<div class="modal-content">

									<div class="modal-body">
										<div id="msg-body">
											<ul id="msg-list">

											</ul>
										</div>
									</div>


									<div class="send-box">
										<form action="">
											<input type="text" id="messageInput" class="form-control"
												aria-label="message…" placeholder="Write message…">

											<button type="button" onclick="sendPrivateMessage()">
												<i class="fa fa-paper-plane" aria-hidden="true"></i> Send
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- chatbox -->


				</div>
			</div>
		</div>
		</div>
	</section>
	<!-- char-area -->
	<script
		src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
	<script src="/shop/js/chat.js"></script>

	<a class="btn btn-primary open-chat-btn" target="_blank"
		href="<c:url value='/chat' />">Chat</a>

	<%@include file="/common/footer.jsp"%>

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- Popper.js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>

	<!-- Bootstrap 4 JS -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>