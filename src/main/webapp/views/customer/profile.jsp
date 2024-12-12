<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

    <title>Profile</title>

<section>
    <figure class="icontext">
        <div class="icon">
            <img class="rounded-circle img-sm border" src="${pageContext.request.contextPath}/img/${customer.profileImg}" alt="Profile Image">
        </div>
        <div class="text">
            <strong>${customer.lastName} ${customer.firstName}</strong><br>
            <p class="mb-2">${customer.email}</p>
            <a href="${pageContext.request.contextPath}/customer/account/edit/${customer.id}" class="btn btn-light btn-sm">Edit Information</a>
        </div>
    </figure>
    <hr>
    <p>
        <i class="fa fa-map-marker text-muted"></i>&nbsp; Address:
        <br> Default Address ID: ${customer.defaultAddressId}&nbsp;
        <a href="${pageContext.request.contextPath}/customer/account/address/edit/${customer.id}">Edit information</a>
    </p>
    <article class="card-group card-stat">
        <figure class="card bg-light">
            <div class="p-3 text-center">
                <h4 class="title">${customer.orders.size()}</h4>
                <span>Orders</span>
            </div>
        </figure>
        <figure class="card bg-light">
            <div class="p-3 text-center">
                <h4 class="title">${customer.wishlists.size()}</h4>
                <span>Favorite Products</span>
            </div>
        </figure>
        <figure class="card bg-light">
            <div class="p-3 text-center">
                <h4 class="title">10</h4>
                <span>Products in Transit</span>
            </div>
        </figure>
        <figure class="card bg-light">
            <div class="p-3 text-center">
                <h4 class="title">42</h4>
                <span>Received Products</span>
            </div>
        </figure>
    </article>
    <hr>
    <article class="card mb-3">
        <div class="card-body">
            <h5 class="card-title mb-4">Recent Orders</h5>
            <div class="row">
                <c:forEach items="${customer.orders}" var="order" varStatus="status">
                    <div class="col-md-6">
                        <figure class="itemside mb-3">
                            <div class="aside">
                                <img src="${pageContext.request.contextPath}/img/product-${status.index + 1}.jpg" class="border img-sm" alt="Product Image">
                            </div>
                            <figcaption class="info">
                                <time class="text-muted"><i class="fa fa-calendar-alt"></i> ${order.getFormattedOrderDate()}</time>
                                <p>${order.getTotalItems()}</p>
<%--                                 <span class="text-success">${order.status}</span>
 --%>                            </figcaption>
                        </figure>
                    </div>
                </c:forEach>
            </div>
            <a href="${pageContext.request.contextPath}/customer/orders" class="btn btn-block btn-outline-primary">
                View All Orders <i class="fa fa-chevron-down"></i>
            </a>
        </div>
    </article>
</section>
