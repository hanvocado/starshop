<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">

<title>Danh sách yêu thích</title>

 <!-- Flash message -->
<%@include file="/common/flash-message.jsp"%>

<body>
<nav class="navbar navbar-main navbar-expand-lg border__bottom">
	<div class="container">

		<div class="row">
			<c:choose>
				<c:when test="${!empty wishlist}">
					<c:forEach var="product" items="${wishlist}">
						<div class="col-md-3">
							<div class="card product-card">
								<img
									src="${pageContext.request.contextPath}/media/${product.image}"
									class="card-img-top product-image" alt="${product.name}">
								<div class="card-body">
									<h5 class="card-title">${product.name}</h5>
									<a
										href="${pageContext.request.contextPath}/add_to_cart?productId=${product.id}">
										<button class="btn custom-btn mt-2" type="button">Add
											to Cart</button>
									</a> <a
										href="${pageContext.request.contextPath}/remove-wishlist/${product.id}">
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
</body>