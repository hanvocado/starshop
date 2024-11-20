<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
    
<title>Carts</title>

<body>
    <h2 class="text-center">My Cart</h2>
    <nav class="navbar navbar-main navbar-expand-lg border__bottom">
        <div class="container">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Item Price</th>
                        <th>Delivery Charge</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>
                                ${item.product.name}
                                <img src="${pageContext.request.contextPath}/media/${item.product.image}" class="img-fluid align-items-center" style="width: 120px;" alt="Product Image">
                            </td>
                            <td>${item.quantity}</td>
                            <td>&#8377;${item.product.price}</td>
                            <td>&#8377;${item.product.deliveryCharge}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/remove/${item.id}/" class="btn btn-danger">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3" class="text-right">Grand Total</td>
                        <td>&#8377;${totalPrice}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </nav>

    <form id="payment-form" class="container" method="POST" action="${pageContext.request.contextPath}/initiate-payment">
        <textarea name="address" id="address" cols="30" rows="3" class="form-control" placeholder="Enter Delivery Address"></textarea>
        <input type="hidden" id="amount" name="amount" value="${totalPrice}">
        <button type="button" id="pay-button" class="btn custom-btn">Pay Now</button>
    </form>

    <script>
        $(document).ready(function() {
            $("#pay-button").click(function(e) {
                e.preventDefault();

                const amount = $("#amount").val();
                const address = $("#address").val();

                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/initiate-payment",
                    data: { amount: amount, address: address },
                    dataType: "json",
                    beforeSend: function(xhr) {
                        xhr.setRequestHeader("X-CSRFToken", $("meta[name='_csrf']").attr("content"));
                    },
                    success: function(data) {
                        const options = {
                            key: data.key,
                            amount: data.amount,
                            currency: data.currency,
                            order_id: data.id,
                            name: data.name,
                            description: data.description,
                            image: data.image,
                            handler: function(response) {
                                if (response.razorpay_payment_id) {
                                    window.location.href = "${pageContext.request.contextPath}/payment-success";
                                } else {
                                    window.location.href = "${pageContext.request.contextPath}/payment-failed";
                                }
                            },
                            prefill: {
                                name: "Card Holder Name",
                                email: "Customer@example",
                                contact: "Customer_Contact",
                            },
                        };

                        const rzp = new Razorpay(options);
                        rzp.open();
                    },
                    error: function(error) {
                        console.error("Error initiating payment:", error);
                    }
                });
            });
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <style>
        .custom-border {
            border: 2px solid #dd3577 !important;
            box-sizing: border-box;
        }
        .custom-btn {
            background-color: #dd3577;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .custom-btn:hover {
            background-color: #ff66a3;
        }
    </style>
</body>
