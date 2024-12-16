<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required Meta Tags Always Come First -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>Quên mật khẩu</title>

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
  <!-- Flash message -->
  <%@include file="/common/flash-message.jsp"%>
  
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
                <form class="js-validate" action="/auth/validate-email" method="post">
                  <div class="text-center">
                    <div class="mb-5">
                      <h1 class="display-4">Forgot password?</h1>
                      <p>Enter the email address you used when you joined and we'll send you instructions to reset your password.</p>
                    </div>
                  </div>

                  <!-- Form Group -->
                  <div class="js-form-message form-group">
                    <label class="input-label" for="resetPasswordSrEmail" tabindex="0">Your email</label>

                    <input type="email" class="form-control form-control-lg" name="email" id="resetPasswordSrEmail" tabindex="1" placeholder="Enter your email address" aria-label="Enter your email address" required="" data-msg="Please enter a valid email address.">
                  </div>
                  <!-- End Form Group -->

                  <button type="submit" class="btn btn-lg btn-block btn-primary">Submit</button>

                  <div class="text-center mt-2">
                    <a class="btn btn-link" href="<c:url value="/auth/login" />">
                      <i class="tio-chevron-left"></i> Back to Sign in
                    </a>
                  </div>
                </form>
                <!-- End Form -->
              </div>
            </div>
            <!-- End Card -->

         
          </div>
        </div>
      </div>
      <!-- End Content -->

      <!-- JS Implementing Plugins -->
      <script src="/exec/js/vendor.min.js"></script>

      <!-- JS Front -->
      <script src="/exec/js/theme.min.js"></script>
      
       <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      
      <!-- JS store jwt -->
 

      <!-- JS Plugins Init. -->
      <script>
        $(document).on('ready', function () {
          // INITIALIZATION OF SHOW PASSWORD
          // =======================================================
          $('.js-toggle-password').each(function () {
            new HSTogglePassword(this).init()
          });


          // INITIALIZATION OF FORM VALIDATION
          // =======================================================
          $('.js-validate').each(function() {
            $.HSCore.components.HSValidation.init($(this));
          });
        });
      </script>

      <!-- IE Support -->
      <script>
        if (/MSIE \d|Trident.*rv:/.test(navigator.userAgent)) document.write('<script src=".//exec/vendor/babel-polyfill/polyfill.min.js"><\/script>');
      </script>
      
	</body>
</html>