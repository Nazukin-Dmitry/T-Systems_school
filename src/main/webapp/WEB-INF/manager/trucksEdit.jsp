<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>LogiWeb</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/skin-blue.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
    <%@ include file="/WEB-INF/manager/header.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Edit Truck
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
        <div class="row">
            <div class="col-md-6">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">Edit truck information</h3>
                    </div><!-- /.box-header -->
                    <form role="form" action="/page/manager/trucks/edit" method="post">
                        <div class="box-body">
                            <%--<div class="form-group">--%>
                                <%--<label for="inputId">Id</label>--%>
                                <input id="inputId" type="hidden" class="form-control" value="${truck.id}" name="id" >
                            <%--</div>--%>
                            <div class="form-group">
                                <label for="inputReg">Registration Number(example:  AE23123)</label>
                                <input id="inputReg" type="text" pattern="[A-Z]{2}[0-9]{5}" class="form-control" value="${truck.regNumber}" name="regNumber" required>
                            </div>
                            <div class="form-group">
                                <label for="inputState">State</label>
                                <select id="inputState" class="form-control" name="state">
                                    <c:forEach var="state" items="${stateList}">
                                        <option value="${state}" ${truck.state eq state ? 'selected': ''}>${state}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputCapacity">Capacity(kg)</label>
                                <input id="inputCapacity" type="number" min="0" class="form-control" value="${truck.capacity}" name="capacity" required>
                            </div>
                            <div class="form-group">
                                <label for="inputCount">Driver count</label>
                                <input id="inputCount" type="number" min="0" class="form-control" value="${truck.driverCount}" name="driverCount" required>
                            </div>
                            <div class="form-group">
                                <label for="inputCity">Current city</label>
                                <input type="hidden" name="pastCurrentCity" value="${truck.currentCity.id}">
                                <select id="inputCity" class="form-control" name="currentCity">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}" ${truck.currentCity.id eq city.id ? 'selected': ''}>${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="inputOrder">Current order</label>
                                <input id="inputOrder" type="text" class="form-control" value="${truck.currentOrder.id}" name="currentOrder" readonly>
                            </div>
                            <p class="text-danger">${error}</p>
                        </div><!-- /.box-body -->
                        <div class="box-footer">
                            <button type="submit" name="action" value="update" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                    <form role="form" action="/page/manager/trucks/edit" method="post">
                        <div class="box-body">
                            <input type="hidden" name="pastCurrentCity" value="${truck.currentCity.id}">
                            <input type="hidden" class="form-control" value="${truck.id}" name="id">
                            <button type="submit" name="action" value="delete" class="btn btn-primary">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-6">
            </div>
        </div>



            <!-- Your Page Content Here -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <%@ include file="/WEB-INF/manager/footer.jsp" %>

    </body>
</html>