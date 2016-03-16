<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->

<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a href="/page/home" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>LW</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>LogiWeb</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li><a href="/page/logout">Sign out</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${pageContext.request.contextPath}/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${sessionScope.user.getFirstName()} ${sessionScope.user.getSecondName()}</p>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">MENU</li>
                <!-- Optionally, you can add icons to the links -->
                <li><a href="/page/manager/map"><i class="fa fa-building"></i> <span>Map</span></a></li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-users"></i> <span>Users</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="/page/manager/drivers"><i class="fa fa-circle-o"></i>Drivers</a></li>
                        <li><a href="/page/manager/newUsers"><i class="fa fa-circle-o"></i>New users</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-truck"></i> <span>Trucks</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="/page/manager/trucks"><i class="fa fa-circle-o"></i> View all</a></li>
                        <li><a href="/page/manager/trucks/create"><i class="fa fa-circle-o"></i> Create</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-reorder"></i> <span>Orders</span> <i class="fa fa-angle-left pull-right"></i></a>
                    <ul class="treeview-menu">
                        <li><a href="/page/manager/orders"><i class="fa fa-circle-o"></i> View all</a></li>
                        <li><a href="/page/manager/orders/createStart"><i class="fa fa-circle-o"></i> Create</a></li>
                    </ul>
                </li>
            </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>
