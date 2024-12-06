<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Flower Shop</title>

    <link href="/shop/img/favicon.ico" rel="shortcut icon" type="image/x-icon">

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
</head>
<body>

<!--     {% include './header.html' %}
 -->        <nav class="navbar navbar-main navbar-expand-lg border__bottom">
            <div class="container">
               
                <div class="row">
                    <c:choose>
                <c:when test="${!empty wishlist}">
                    <c:forEach var="item" items="${wishlist}">
                        <div class="col-md-3">
                            <div class="card product-card">
                                <img src="${pageContext.request.contextPath}/media/${item.image}" class="card-img-top product-image" alt="${item.name}">
                                <div class="card-body">
                                    <h5 class="card-title">${item.name}</h5>
                                    <a href="${pageContext.request.contextPath}/add_to_cart?productId=${item.id}">
                                        <button class="btn custom-btn mt-2" type="button">Add to Cart</button>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/remove_from_wishlist?productId=${item.id}">
                                        <button class="btn custom-btn mt-2" type="button">Remove</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h4>Your wishlist is empty.</h4>
                </c:otherwise>
            </c:choose>
                </div>
            </div>
        </nav>

<!--     {% include './footer.html' %}
 --></body>

</html>
