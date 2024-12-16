<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required Meta Tags Always Come First -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title -->
    <title>Lỗi</title>

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
                  <h4>${errorCode }</h4>
                  <div>${errorMessage }</div>
                  <p>${errorDetails }</p>
                </div>
              </div>
              <!-- End Card -->
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
      
       <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      

      
	</body>
</html>