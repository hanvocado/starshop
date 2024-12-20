<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

    <link href="/shop/css/custom-style.css" type="stylesheet" />
    <link href="/shop/images/favicon.ico " rel="shortcut icon" type="image/x-icon">
    
     <!-- jQuery -->
     <script src="/shop/js/jquery-3.5.1.slim.min.js" type="text/javascript"></script>
 
     <!-- Bootstrap -->
     <script src="/shop/js/bootstrap.min.js" type="text/javascript"></script>
     <script src="/shop/js/popper.min.js" type="text/javascript"></script>
     <link href="/shop/css/bootstrap.min.css" type="text/css" rel="stylesheet">
 
     <!-- FontAwesome 5 -->
     <link href="/shop/fonts/fontawesome/css/all.min.css" type="text/css" rel="stylesheet">
 
     <!-- Google Fonts -->
     <link href="//fonts.googleapis.com/css2?family=EB+Garamond:ital,wght@0,400;0,500;0,600;0,615;0,700;0,800;1,400;1,500;1,600;1,615;1,700;1,800&family=Quicksand:wght@300;400;500;600;700&display=swap" rel="stylesheet">
 
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
	
	<title><sitemesh:write property="title"/></title>
	
</head>

<body>
	<%@include file="/common/header.jsp"%>
	
	<sitemesh:write property="body" />
	
	<c:if test="${not empty user }">
		<a class="btn btn-primary open-chat-btn" target="_blank" href="<c:url value='/chat' />">Chat</a>
	</c:if>
	
	<%@include file="/common/footer.jsp"%>
     
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- Popper.js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	
	<!-- Bootstrap 4 JS -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
</body>

</html>