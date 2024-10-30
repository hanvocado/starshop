<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>


<div id="sidebarMain" class="d-none">
        <aside class="js-navbar-vertical-aside navbar navbar-vertical-aside navbar-vertical navbar-vertical-fixed navbar-expand-xl navbar-bordered  ">
            <div class="navbar-vertical-container">
                <div class="navbar-vertical-footer-offset">
                    <div class="navbar-brand-wrapper justify-content-between">
                        <!-- Logo -->


                        <a class="navbar-brand" href="<c:url value="/admin"/>" aria-label="Front">
                            <img class="navbar-brand-logo" src="/exec/svg/logos/logo.svg" alt="Logo">
                            <img class="navbar-brand-logo-mini" src="/exec/svg/logos/logo-short.svg" alt="Logo">
                        </a>

                        <!-- End Logo -->
                        <!-- Navbar Vertical Toggle -->
                        <button type="button" class="js-navbar-vertical-aside-toggle-invoker navbar-vertical-aside-toggle btn btn-icon btn-xs btn-ghost-dark">
                            <i class="tio-clear tio-lg"></i>
                        </button>
                        <!-- End Navbar Vertical Toggle -->
                    </div>

                    <!-- Content -->
                    <div class="navbar-vertical-content">
                        <ul class="navbar-nav navbar-nav-lg nav-tabs">
                        
                        	<!-- E-COMMERCE  -->
                            <li class="nav-item">
                                <small class="nav-subtitle" title="E-commerce">E-commerce</small>
                                <small class="tio-more-horizontal nav-subtitle-replacer"></small>
                            </li>

                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="<c:url value="/admin/categories"/>" title="Categories" data-placement="left">
                                    <i class="tio-layers-outlined nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Categories</span>
                                </a>
                            </li>

                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="<c:url value="/admin/products"/>" title="Products" data-placement="left">
                                    <i class="tio-pages-outlined nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Products</span>
                                </a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="orders" title="Orders" data-placement="left">
                                    <i class="tio-money nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Orders</span>
                                </a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="dashboard" title="Dashboard" data-placement="left">
                                    <i class="tio-chart-bar-1 nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Dashboard</span>
                                </a>
                            </li>
                            <!-- END E-COMMERCE  -->

                            <li class="nav-item">
                                <div class="nav-divider"></div>
                            </li>

							<!-- USERS -->
                            <li class="nav-item">
                                <small class="nav-subtitle" title="Users">Users</small>
                                <small class="tio-more-horizontal nav-subtitle-replacer"></small>
                            </li>

                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="customers" title="Customers" data-placement="left">
                                    <i class="tio-user nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Customers</span>
                                </a>
                            </li>

                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="admins" title="Admins" data-placement="left">
                                    <i class="tio-settings-outlined nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Admins</span>
                                </a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="shippers" title="Shippers" data-placement="left">
                                    <i class="tio-motocycle nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Shippers</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- End Content -->
                    
                </div>
            </div>
        </aside>
    </div>
    <div id="sidebarCompact" class="d-none">
        <aside class="js-navbar-vertical-aside navbar navbar-vertical-aside navbar-vertical navbar-vertical-fixed navbar-expand-xl navbar-bordered  ">
            <div class="navbar-vertical-container">
                <div class="navbar-brand d-flex justify-content-center">
                    <!-- Logo -->


                    <a class="navbar-brand" href="index.html" aria-label="Front">
                        <img class="navbar-brand-logo-short" src="/exec/svg/logos/logo-short.svg" alt="Logo">
                    </a>

                    <!-- End Logo -->
                </div>

                <!-- Content -->
                <div class="navbar-vertical-content">
                    <ul class="navbar-nav nav-compact">
                        <!-- Dashboards -->
                        <li class="navbar-vertical-aside-has-menu nav-item">
                            <a class="js-navbar-vertical-aside-menu-link nav-link " href="javascript:;" title="Dashboards">
                                <i class="tio-home-vs-1-outlined nav-icon"></i>
                                <span class="nav-compact-title text-truncate">Dashboards</span>
                            </a>

                        </li>
                        <!-- End Dashboards -->


                        <li class="nav-item">
                            <a class="nav-link " href="welcome-page.html" title="Welcome Page">
                                <i class="tio-visible-outlined nav-icon"></i>
                                <span class="nav-compact-title text-truncate">Welcome Page</span>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link " href="layouts/layouts.html" title="Layouts">
                                <i class="tio-dashboard-vs-outlined nav-icon"></i>
                                <span class="nav-compact-title text-truncate">Layouts</span>
                            </a>
                        </li>

                        <li class="nav-item ">
                            <a class="nav-link" href="documentation/index.html" title="Documentation">
                                <i class="tio-book-opened nav-icon"></i>
                                <span class="nav-compact-title text-truncate">Documentation</span>
                                <span class="badge badge-primary badge-pill">v1.1</span>
                            </a>
                        </li>

                        <li class="nav-item ">
                            <a class="nav-link" href="documentation/typography.html" title="Components">
                                <i class="tio-layers-outlined nav-icon"></i>
                                <span class="nav-compact-title text-truncate">Components</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- End Content -->
            </div>
        </aside>
    </div>

    