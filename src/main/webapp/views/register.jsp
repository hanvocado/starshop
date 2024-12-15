<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required Meta Tags Always Come First -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>Đăng ký</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="favicon.ico">

    <!-- Font -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap" rel="stylesheet">

    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href="/exec/css/vendor.min.css">
    <link rel="stylesheet" href="/exec/vendor/icon-set/style.css">

    <!-- CSS Front Template -->
    <link rel="stylesheet" href="/exec/css/theme.min.css?v=1.0">
  </head>

  <body>
  <!-- Flash message -->
  <%@include file="/common/flash-message.jsp"%>
  
    <!-- ========== MAIN CONTENT ========== -->
    <main id="content" role="main" class="main">
      <div class="position-fixed top-0 right-0 left-0 bg-img-hero" style="height: 32rem; background-image: url(/exec/svg/components/abstract-bg-4.svg);">
        <!-- SVG Bottom Shape -->
        <figure class="position-absolute right-0 bottom-0 left-0">
          <svg preserveaspectratio="none" xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" viewbox="0 0 1921 273">
            <polygon fill="#fff" points="0,273 1921,273 1921,0 "></polygon>
          </svg>
        </figure>
        <!-- End SVG Bottom Shape -->
        <!-- End SVG Shapes -->
      </div>

      <!-- Content -->
      <div class="container py-5 py-sm-7">
        <a class="d-flex justify-content-center mb-5" href="index.html">
          <img class="z-index-2" src="/exec/svg/logos/logo.svg" alt="Image Description" style="width: 8rem;">
        </a>

        <div class="row justify-content-center">
          <div class="col-md-7 col-lg-5">
            <!-- Card -->
            <div class="card card-lg mb-5">
              <div class="card-body">
                <!-- Form -->
                <form class="js-validate" action="/auth/register" method="post">
                  <div class="text-center">
                    <div class="mb-5">
                      <h1 class="display-4">Create your account</h1>
                      <p>Already have an account? <a href="authentication-signin-basic.html">Sign in here</a></p>
                    </div>

                    <a class="btn btn-lg btn-block btn-white mb-4" href="#">
                      <span class="d-flex justify-content-center align-items-center">
                        <img class="avatar avatar-xss mr-2" src="/exec/svg/brands/google.svg" alt="Image Description">
                        Sign up with Google
                      </span>
                    </a>

                    <span class="divider text-muted mb-4">OR</span>
                  </div>

                  <!-- Form Group -->
                  <div class="form-row">
                    <div class="col-sm-6">
                      <div class="js-form-message form-group">
                        <label class="input-label" for="lastName">Họ</label>
                        <input type="text" class="form-control form-control-lg" name="lastName" id="lastName" placeholder="Mark" aria-label="Mark"  required="" data-msg="Please enter your last name.">
                      </div>
                    </div>
                    
                    <div class="col-sm-6">
                      <div class="js-form-message form-group">
                        <label class="input-label" for="firstName">Tên</label>
                        <input type="text" class="form-control form-control-lg" name="firstName" id="firstName" placeholder="Mark" aria-label="Mark"  required="" data-msg="Please enter your first name.">
                      </div>
                    </div>
                  </div>
                  <!-- End Form Group -->
                  
                  <!-- Form Group -->
                  <div class="js-form-message form-group">
                    <label class="input-label" for="signupSrUserName">User Name</label>

                    <input type="text" class="form-control form-control-lg" name="userName" id="signupSrUserName" placeholder="User Name" aria-label="Markwilliams@example.com" required="">
                  </div>
                  <!-- End Form Group -->

                  <!-- Form Group -->
                  <div class="form-row">
                    <div class="col-sm-6">
	                  <div class="js-form-message form-group">
	                    <label class="input-label" for="signupSrEmail">Email</label>
	
	                    <input type="email" class="form-control form-control-lg" name="email" id="signUpEmail" placeholder="Markwilliams@example.com" aria-label="Markwilliams@example.com" required="" data-msg="Please enter a valid email address.">
	                  </div>
                    </div>
                    
                    <div class="col-sm-2 d-flex align-items-center">
                    	<div class="d-flex align-items-center">
                    	<button class="btn btn-soft-primary" onClick="sendOTP()">Gửi</button>
                    	</div>
                    </div>
                    
                    <div class="col-sm-4">
                    	
                    	<div class="js-form-message form-group">
	                    	<label class="input-label" for="signupSrEmail">OTP</label>
	
	                    	<input type="text" class="form-control form-control-lg" name="otp" id="signupSrEmail" placeholder="OTP" aria-label="OTP" required="" data-msg="Nhập mã xác thực của bạn.">
	                  	</div>
                    </div>
                   </div>
                  <!-- End Form Group -->
                  

                  <!-- Form Group -->
                  <div class="js-form-message form-group">
                    <label class="input-label" for="signupSrPassword">Password</label>

                    <div class="input-group input-group-merge">
                      <input type="password" class="js-toggle-password form-control form-control-lg" name="password" id="signupSrPassword" placeholder="8+ characters required" aria-label="8+ characters required" required="" data-msg="Your password is invalid. Please try again." data-hs-toggle-password-options='{
                               "target": [".js-toggle-password-target-1", ".js-toggle-password-target-2"],
                               "defaultClass": "tio-hidden-outlined",
                               "showClass": "tio-visible-outlined",
                               "classChangeTarget": ".js-toggle-passowrd-show-icon-1"
                             }'>
                      <div class="js-toggle-password-target-1 input-group-append">
                        <a class="input-group-text" href="javascript:;">
                          <i class="js-toggle-passowrd-show-icon-1 tio-visible-outlined"></i>
                        </a>
                      </div>
                    </div>
                  </div>
                  <!-- End Form Group -->

                  <!-- Form Group -->
                  <div class="js-form-message form-group">
                    <label class="input-label" for="signupSrConfirmPassword">Confirm password</label>

                    <div class="input-group input-group-merge">
                      <input type="password" class="js-toggle-password form-control form-control-lg" name="confirmPassword" id="signupSrConfirmPassword" placeholder="8+ characters required" aria-label="8+ characters required" required="" data-msg="Password does not match the confirm password." data-hs-toggle-password-options='{
                               "target": [".js-toggle-password-target-1", ".js-toggle-password-target-2"],
                               "defaultClass": "tio-hidden-outlined",
                               "showClass": "tio-visible-outlined",
                               "classChangeTarget": ".js-toggle-passowrd-show-icon-2"
                             }'>
                      <div class="js-toggle-password-target-2 input-group-append">
                        <a class="input-group-text" href="javascript:;">
                          <i class="js-toggle-passowrd-show-icon-2 tio-visible-outlined"></i>
                        </a>
                      </div>
                    </div>
                  </div>
                  <!-- End Form Group -->

                  <!-- Checkbox -->
                  <div class="js-form-message form-group">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" class="custom-control-input" id="termsCheckbox" name="termsCheckbox" required="" data-msg="Please accept our Terms and Conditions.">
                      <label class="custom-control-label text-muted" for="termsCheckbox"> I accept the <a href="#">Terms and Conditions</a></label>
                    </div>
                  </div>
                  <!-- End Checkbox -->

                  <button type="submit" class="btn btn-lg btn-block btn-primary mb-2">Create an account</button>

                  <button type="submit" class="btn btn-block btn-link">
                    or Start your 30-day trial <i class="tio-chevron-right"></i>
                  </button>
                </form>
                <!-- End Form -->
              </div>
            </div>
            <!-- End Card -->

            <!-- Footer -->
            <div class="text-center">
              <small class="text-cap mb-4">Trusted by the world's best teams</small>

              <div class="w-85 mx-auto">
                <div class="row justify-content-between">
                  <div class="col">
                    <img class="img-fluid" src="/exec/svg/brands/gitlab-gray.svg" alt="Image Description">
                  </div>
                  <div class="col">
                    <img class="img-fluid" src="/exec/svg/brands/fitbit-gray.svg" alt="Image Description">
                  </div>
                  <div class="col">
                    <img class="img-fluid" src="/exec/svg/brands/flow-xo-gray.svg" alt="Image Description">
                  </div>
                  <div class="col">
                    <img class="img-fluid" src="/exec/svg/brands/layar-gray.svg" alt="Image Description">
                  </div>
                </div>
              </div>
            </div>
            <!-- End Footer -->
          </div>
        </div>
      </div>
      <!-- End Content -->
    </main>
    <!-- ========== END MAIN CONTENT ========== -->


    <!-- JS Implementing Plugins -->
    <script src="/exec/js/vendor.min.js"></script>

    <!-- JS Front -->
    <script src="/exec/js/theme.min.js"></script>

    <script src="/shop/js/auth.js"></script>

  </body>
</html>