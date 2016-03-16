<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 15.02.2016
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/dist/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-datetimepicker.min.css">
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


    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.css">--%>
    <%@ include file="/WEB-INF/manager/header.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Order creation
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Choose start time</h3>
                        </div><!-- /.box-header -->
                        <form role="form" action="/page/manager/orders/create" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="datetimepicker2">Date and time</label>
                                    <input type="text" class="form-control" id="datetimepicker2" name="date">
                                </div>
                            </div><!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" name="action" value="time"  class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div><!-- /.row -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <%@ include file="/WEB-INF/manager/footer.jsp" %>


    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/dist/js/moment-with-locales.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/resources/dist/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/dist/js/bootstrap-datetimepicker.min.js"></script>

    <script type="text/javascript">
        $(function () {

            var tomorrow = new Date();
            tomorrow.setDate(tomorrow.getDate() + 1);
            tomorrow.setHours(0, 0, 0, 0);
            $('#datetimepicker2').datetimepicker({defaultDate: tomorrow, minuteStepping: 60});
            $('#datetimepicker2').data("DateTimePicker").setMinDate(tomorrow);
        });
    </script>


    </body>
</html>
