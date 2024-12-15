<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer class="section__footer" style="position: relative; z-index: -1;">
    <section class="footer__top py-5">
        <div class="container">
            <div class="row">
                <aside class="col-sm-6 col-lg-3">
                    <h6 class="title">Introduction</h6>
                    <ul class="list-unstyled">
                        <li><a href="{% url 'about-page' %}">About the Spring Bloom</a></li>

                        <li><a href="#">Careers</a></li>
                        <li><a href="#">Payment Policies</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                        <li><a href="#">Complaint Resolution</a></li>
                        <li><a href="#">Partnerships</a></li>
                    </ul>
                </aside>
                <aside class="col-sm-6 col-lg-3">
                    <h6 class="title">Customer Support</h6>
                    <ul class="list-unstyled">
                        <li>Hotline: <span class="color__main">1900-XXX-XXX</span></li>
                        <li><a href="#">Frequently Asked Questions</a></li>
                        <li><a href="#">Ordering Guide</a></li>
                        <li><a href="#">Shipping Methods</a></li>
                        <li><a href="#">Return Policy</a></li>
                    </ul>
                </aside>
                <aside class="col-lg-6">
                    <h6 class="title">Subscribe to Newsletter</h6>
                    <form action="#" class="fs__form">
                        <div class="input-group w-100">
                            <input type="text" class="form-control" placeholder="Your Email ...">
                            <button type="button " class="fs__button custom-btn btn">Subscribe</button>
                        </div>
                    </form>
                </aside>
            </div>
            <!-- row.// -->
        </div>
        <!-- container.// -->
    </section>
    <!-- footer__top.// -->

    <section class="footer__bottom text-center" >
        <div class="container-fluid">
            <p class="text-muted">© 2024 — Flower Shop </p>
        </div>
        <!-- container-fluid.// -->
    </section>
    <!-- footer__bottom.// -->
</footer><!-- section__footer.// -->