<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
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
 
     <!-- Custom JavaScript -->
     <script src="/shop/js/script.js" type="text/javascript"></script>
 
     <!-- Slick Slider -->
     <link rel="stylesheet" type="text/css" href="/shop/css/slick.css" />
     <script type="text/javascript" src="/shop/js/slick.min.js"></script>
     <!-- Bootstrap Bundle JS (với Popper.js) -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
	
	<meta charset="UTF-8">
	
	<title><sitemesh:write property="title"/></title>
	
</head>

<body>
	<%@include file="/common/header.jsp"%>
	
	<sitemesh:write property="body" />
	
	<%@include file="/common/footer.jsp"%>
</body>

</html>