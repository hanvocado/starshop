<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">

<title>Danh sách yêu thích</title>

<body>
	<!-- Flash message -->
	<%@include file="/common/toast-message.jsp"%>

	<nav class="navbar navbar-main navbar-expand-lg border__bottom">
		<div class="container">

			<div class="row">
				<c:choose>
					<c:when test="${!empty wishlist}">
						<div class="row justify-content-start  align-items-start  d-flex flex-wrap">
							<c:forEach var="product" items="${wishlist}">
								<div class="col-md-3">
									<a
										href="${pageContext.request.contextPath}/customer/products/details/${product.id}"
										style="text-decoration: none; color: inherit;">
										<div class="card product-card my-1">
											<c:if test="${product.image.substring(0,5)=='https'}">
												<c:url value="${product.image }" var="imgUrl"></c:url>
											</c:if>
											<c:if test="${product.image.substring(0,5)!='https'}">
												<c:url value="/img/${product.image }" var="imgUrl"></c:url>
											</c:if>
											<img src="${imgUrl}" class="card-img-top product-image"
												alt="${product.name}" height="300">
											<div class="card-body">
												<h5 class="card-title">${product.name}</h5>
												<div class="row d-flex justify-content-between mx-1">
													<!-- Add to Cart Button -->
													<form
														action="${pageContext.request.contextPath}/customer/cart/add/${product.id}"
														method="POST">
														<button class="fs__button custom-btn btn w-max mt-2"
															type="submit">Add to Cart</button>
													</form>

													<!-- Remove Button -->
													<form
														action="${pageContext.request.contextPath}/customer/remove-wishlist/${product.id}"
														method="POST">
														<button class="fs__button custom-btn btn w-max mt-2"
															type="submit">Remove</button>
													</form>
												</div>
											</div>

										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</c:when>
					<c:otherwise>
						<h4>Your wishlist is empty.</h4>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>
</body>

