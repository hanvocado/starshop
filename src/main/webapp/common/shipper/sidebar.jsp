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
                            <img class="navbar-brand-logo" src="/logo.svg" alt="Logo">
                            <img class="navbar-brand-logo-mini" src="/logo.svg" alt="Logo">
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
                            <li class="nav-item">
                                <small class="nav-subtitle" title="E-commerce"></small>
                                <small class="tio-more-horizontal nav-subtitle-replacer"></small>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="<c:url value="/shipper/orders"/>" title="Orders" data-placement="left">
                                    <i class="tio-money nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Orders</span>
                                </a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="js-nav-tooltip-link nav-link" href="<c:url value="/shipper/dashboard"/>" title="Dashboard" data-placement="left">
                                    <i class="tio-chart-bar-1 nav-icon"></i>
                                    <span class="navbar-vertical-aside-mini-mode-hidden-elements text-truncate">Dashboard</span>
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

                    </ul>
                </div>
                <!-- End Content -->
            </div>
        </aside>
    </div>

    