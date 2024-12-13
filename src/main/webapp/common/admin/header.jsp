<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div id="headerMain" class="d-none">
    <header id="header" class="navbar navbar-expand-lg navbar-fixed navbar-height navbar-flush navbar-container navbar-bordered">
        <div class="navbar-nav-wrap">
            <div class="navbar-brand-wrapper">
                <!-- Logo -->
                <a class="navbar-brand" href="/" aria-label="Front">
                    <img class="navbar-brand-logo" src="/logo.svg" alt="Logo">
                    <img class="navbar-brand-logo-mini" src="/logo.svg" alt="Logo">
                </a>
                <!-- End Logo -->
            </div>

            <div class="navbar-nav-wrap-content-left">
                <!-- Navbar Vertical Toggle -->
                <button type="button" class="js-navbar-vertical-aside-toggle-invoker close mr-3">
                    <i class="tio-first-page navbar-vertical-aside-toggle-short-align" data-toggle="tooltip" data-placement="right" title="Thu gọn"></i>
                    <i class="tio-last-page navbar-vertical-aside-toggle-full-align" data-template='<div class="tooltip d-none d-sm-block" role="tooltip"><div class="arrow"></div><div class="tooltip-inner"></div></div>' data-toggle="tooltip" data-placement="right" title="Mở rộng"></i>
                </button>
                <!-- End Navbar Vertical Toggle -->
                
            </div>

            <!-- Secondary Content -->
            <div class="navbar-nav-wrap-content-right">
                <!-- Navbar -->
                <ul class="navbar-nav align-items-center flex-row">
					<li class="nav-item d-md-none">
                        
                    </li>
                    <li class="nav-item">
                        <!-- Account -->
                        <div class="hs-unfold">
                            <!-- @if (!string.IsNullOrEmpty(User.Claims.FirstOrDefault(c => c.Type.Equals("Email"))?.Value))
                            { -->
                                <a class="js-hs-unfold-invoker navbar-dropdown-account-wrapper" href="javascript:;" data-hs-unfold-options='{
                                                "target": "#accountNavbarDropdown",
                                                "type": "css-animation"
                                            }'>
                                    <span class="avatar avatar-sm avatar-circle">
                                        <img class="avatar-img" src="~/theme/front-dashboard/assets/img/160x160/img6.jpg" alt="Image Description">
                                        <span class="avatar-status avatar-sm-status avatar-status-success"></span>
                                    </span>
                                </a>
                            <!-- }
                            else
                            { -->
<!--                                 <a class="js-hs-unfold-invoker navbar-dropdown-account-wrapper" asp-action="Login" asp-controller="Authentication">Đăng Nhập</a>
 -->                            <!-- } -->
                            <div id="accountNavbarDropdown" class="hs-unfold-content dropdown-unfold dropdown-menu dropdown-menu-right navbar-dropdown-menu navbar-dropdown-account" style="width: 16rem;">
                                <a class="dropdown-item" href="#">
                                    <div class="media align-items-center">
                                        <div class="avatar avatar-sm avatar-dark avatar-circle mr-2">
                                            <span class="avatar-initials">H</span>
                                        </div>
                                        <div class="media-body">
                                            <span class="card-title h5">email</span>
                                        </div>
                                    </div>
                                </a>

                                <div class="dropdown-divider"></div>

                                <a class="dropdown-item" asp-action="Detail" asp-controller="Profile">
                                    <span class="text-truncate pr-2" title="Tài Khoản">Tài Khoản</span>
                                </a>

                                <a class="dropdown-item" asp-action="ChangePassword" asp-controller="Account">
                                    <span class="text-truncate pr-2" title="Đổi mật Khẩu">Đổi mật Khẩu</span>
                                </a>

                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" asp-action="Logout" asp-controller="Authentication">
                                    <span class="text-truncate pr-2" title="Đăng Xuất">Đăng Xuất</span>
                                </a>
                            </div>
                        </div>
                        <!-- End Account -->
                    </li>

                </ul>
                <!-- End Navbar -->
            </div>
            <!-- End Secondary Content -->
        </div>
    </header>
</div>
   
<div id="headerFluid" class="d-none">
</div>
<div id="headerDouble" class="d-none">
</div>