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
    <title><sitemesh:write property="title" /></title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="favicon.ico">

    <!-- Font -->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap" rel="stylesheet">
    
    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href="/exec/css/vendor.min.css">
    <link rel="stylesheet" href="/exec/vendor/icon-set/style.css">

    <!-- CSS Front Template -->
    <link rel="stylesheet" href="/exec/css/theme.min.css?v=1.0">
    <link rel="stylesheet" href="/chat.css">
    
</head>

<body class="footer-offset">

    <script src="/exec/vendor/hs-navbar-vertical-aside/hs-navbar-vertical-aside-mini-cache.js"></script>

	<%@include file="/common/shipper/header.jsp"%>
    
	<%@include file="/common/shipper/sidebar.jsp"%>    
    
    <script src="/exec/js/demo.js"></script>

    <!-- ========== MAIN CONTENT ========== -->
    <!-- Navbar Vertical -->
    <!-- End Navbar Vertical -->

    <main id="content" role="main" class="main pointer-event">
        <!-- Content -->
        <div class="content container-fluid">
            <sitemesh:write property="body" />
        </div>
        <!-- End Content -->
        
        <!-- Footer -->
        <%@include file="/common/admin/footer.jsp"%>        
        <!-- End Footer -->
    </main>
    <!-- ========== END MAIN CONTENT ========== -->
    <!-- ========== SECONDARY CONTENTS ========== -->

    
    <!-- ========== END SECONDARY CONTENTS ========== -->
    <!-- JS Implementing Plugins -->
    <script src="/exec/js/vendor.min.js"></script>
    <script src="/exec/vendor/chart.js/dist/Chart.min.js"></script>
    <script src="/exec/vendor/chart.js.extensions/chartjs-extensions.js"></script>
    <script src="/exec/vendor/chartjs-plugin-datalabels/dist/chartjs-plugin-datalabels.min.js"></script>

    <!-- JS Front -->
    <script src="/exec/js/theme.min.js"></script>

    <!-- JS Plugins Init. -->
    <script src="/exec/js/init.js"></script>
</body>
</html>