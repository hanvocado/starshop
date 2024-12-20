<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat</title>
</head>
<body>
<!-- char-area -->
    <section class="message-area">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="row chat-area">
                        <!-- chatlist -->
                        <div class="col-3 chatlist">
                            <div class="modal-dialog-scrollable">
                                <div class="modal-content">
                                    <div class="chat-header">
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item" role="presentation">
                                                <button class="nav-link active" id="Open-tab" data-bs-toggle="tab" data-bs-target="#Open" type="button" role="tab" aria-controls="Open" aria-selected="true">Open</button>
                                            </li>
                                        </ul>
                                    </div>

                                    <div class="modal-body">
                                        <!-- chat-list -->
                                        <div class="chat-lists">
                                            <div class="tab-content" id="myTabContent">
                                                <div class="tab-pane fade show active" id="Open" role="tabpanel" aria-labelledby="Open-tab">
                                                    <!-- chat-list -->
                                                    <div class="chat-list" id="active-user-list">
														
                                                    </div>
                                                    <!-- chat-list -->
                                                </div>
                                            </div>

                                        </div>
                                        <!-- chat-list -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- chatlist -->



                        <!-- chatbox -->
                        <div class="col-9 chatbox">
                            <div class="modal-dialog-scrollable">
                                <div class="modal-content">
                                    <div class="msg-head">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="d-flex align-items-center">
                                                    <span class="chat-icon"><img class="img-fluid" src="https://mehedihtml.com/chatbox/assets/img/arroleftt.svg" alt="image title"></span>
                                                
                                                    <div class="flex-grow-1 ms-3">
                                                        <h3 id="currentRecipient" ></h3>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="modal-body">
                                        <div id="msg-body">
                                            <ul id="msg-list">

                                            </ul>
                                        </div>
                                    </div>


                                    <div class="send-box">
                                        <form action="">
                                            <input type="text" id="messageInput" class="form-control" aria-label="message…" placeholder="Write message…">

                                            <button type="button" onclick="sendPrivateMessage()"><i class="fa fa-paper-plane" aria-hidden="true"></i> Send</button>
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
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs/lib/stomp.min.js"></script>
    <script src="/exec/js/chat.js"></script>
</body>
</html>