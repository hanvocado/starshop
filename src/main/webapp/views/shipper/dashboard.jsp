<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông kê</title>
</head>
<body>
	<div class="content container-fluid">
		<!-- Page Header -->
		<div class="page-header">
			<div class="row align-items-center">
				<div class="col-sm mb-2 mb-sm-0">
					<h1 class="page-header-title">Dashboard</h1>
				</div>
			</div>
		</div>
		<!-- End Page Header -->
		
		<!-- Stats -->
            <div class="row gx-2 gx-lg-3">

                <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                    <!-- Card -->
                    <a class="card card-hover-shadow h-100" href="#">
                        <div class="card-body">
                            <h6 class="card-subtitle">Tổng số đơn</h6>

                            <div class="row align-items-center gx-2 mb-1">
                                <div class="col-6">
                                    <span class="card-title h2">${record.shipper.getNumberOfOrders() }</span>
                                </div>

                            </div>
                            <!-- End Row -->
                        </div>
                    </a>
                    <!-- End Card -->
                </div>

                <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                    <!-- Card -->
                    <a class="card card-hover-shadow h-100" href="#">
                        <div class="card-body">
                            <h6 class="card-subtitle">Giao thành công</h6>

                            <div class="row align-items-center gx-2 mb-1">
                                <div class="col-12">
                                    <span class="card-title h2">${record.successCount }</span>
                                </div>
                            </div>
                            <!-- End Row -->

                        </div>
                    </a>
                    <!-- End Card -->
                </div>

                <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                    <!-- Card -->
                    <a class="card card-hover-shadow h-100" href="#">
                        <div class="card-body">
                            <h6 class="card-subtitle">Đang giao</h6>

                            <div class="row align-items-center gx-2 mb-1">
                                <div>
                                    <span class="card-title h2">${record.shippingCount }</span>
                                </div>

                            </div>
                            <!-- End Row -->

                        </div>
                    </a>
                    <!-- End Card -->
                </div>
                
                <div class="col-sm-6 col-lg-3 mb-3 mb-lg-5">
                    <!-- Card -->
                    <a class="card card-hover-shadow h-100" href="#">
                        <div class="card-body">
                            <h6 class="card-subtitle">Tỷ lệ thành công</h6>

                            <div class="row align-items-center gx-2 mb-1">
                                <div>
                                    <span class="card-title h2">${successRate } %</span>
                                </div>

                            </div>
                            <!-- End Row -->

                        </div>
                    </a>
                    <!-- End Card -->
                </div>
            </div>
            <!-- End Stats -->
		
		<!-- Stats -->
		<div class="row gx-2 gx-lg-3 mx-1 mb-3">
			<!-- Card -->
			<div class="card h-100">
				<!-- Header -->
				<div class="card-header container">
					<div class="row container">
						<div class="col-4 d-flex align-items-center"><h5 class="card-header-title">Biểu đồ</h5></div>
						<div class="col-8">
							<form action="<c:url value="/shipper/dashboard"/>" method="get">
								<div class="input-group" id="yearInput">
						            <input type="text" class="form-control" name="year" value="${selectedYear }" placeholder="Enter Year" >
						            <span class="input-group-text">
						                <i class="tio-clock"></i>
						            </span>							
						        </div>
							</form>
						</div>
					</div>
					
				</div>
				<!-- End Header -->
				<!-- Body -->
				<div class="card-body">
					<div class="row mb-4">
						<div class="col-sm mb-2 mb-sm-0">
							<div class="d-flex align-items-center">
								
							</div>
						</div>

						<div class="col-sm-auto align-self-sm-end">
							<!-- Legend Indicators -->
							<div class="row font-size-sm">
								<div class="col-auto">
									<span class="legend-indicator bg-primary"></span> Tổng số đơn
								</div>
								<div class="col-auto">
									<span class="legend-indicator bg-info"></span> Số đơn thành công
								</div>
							</div>
							<!-- End Legend Indicators -->
						</div>
					</div>
					<!-- End Row -->
					<!-- Bar Chart -->
					<div class="chartjs-custom">
						<canvas id="updatingData" style="height: 20rem;"
							data-hs-chartjs-options='{
                            "type": "bar",
                            "data": {
                              "labels": ${labels },
                              "datasets": [{
                                "data": ${totalCounts },
                                "backgroundColor": "#dd3577",
                                "hoverBackgroundColor": "#dd3577",
                                "borderColor": "#dd3577"
                              },
                              {
                                "data": ${successCounts },
                                "backgroundColor": "#00c9db",
                                "borderColor": "#00c9db"
                              }]
                            },
                            "options": {
                              "scales": {
                                "yAxes": [{
                                  "gridLines": {
                                    "color": "#e7eaf3",
                                    "drawBorder": false,
                                    "zeroLineColor": "#e7eaf3"
                                  },
                                  "ticks": {
                                    "beginAtZero": true,
                                    "stepSize": 10000,
                                    "fontSize": 12,
                                    "fontColor": "#97a4af",
                                    "fontFamily": "Open Sans, sans-serif",
                                    "padding": 10,
                                    "postfix": ""
                                  }
                                }],
                                "xAxes": [{
                                  "gridLines": {
                                    "display": false,
                                    "drawBorder": false
                                  },
                                  "ticks": {
                                    "fontSize": 12,
                                    "fontColor": "#97a4af",
                                    "fontFamily": "Open Sans, sans-serif",
                                    "padding": 5
                                  },
                                  "categoryPercentage": 0.5,
                                  "maxBarThickness": "10"
                                }]
                              },
                              "cornerRadius": 2,
                              "tooltips": {
                                "prefix": "",
                                "hasIndicator": true,
                                "mode": "index",
                                "intersect": false
                              },
                              "hover": {
                                "mode": "nearest",
                                "intersect": true
                              }
                            }
                          }'></canvas>
					</div>
					<!-- End Bar Chart -->
				</div>
				<!-- End Body -->
			</div>
			<!-- End Card -->
		</div>
	</div>
</body>
</html>