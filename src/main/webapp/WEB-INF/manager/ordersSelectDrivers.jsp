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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.css">

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
                Select drivers for order
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Suitable drivers. You need to select ${driverNumber} drivers.</h3>
                        </div><!-- /.box-header -->
                        <form role="form" action="/page/manager/orders/create" method="post">
                            <div class="box-body">
                                <p class="text-danger">${error}</p>
                                <table id="example1" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Check</th>
                                        <th>First Name</th>
                                        <th>Second Name</th>
                                        <th>Serial number</th>
                                        <th>Work time</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="driver" items="${driverList}">
                                        <tr>
                                            <td><input type="checkbox" name="drivers" value="${driver.id}"></td>
                                            <td>${driver.employee.firstName}</td>
                                            <td>${driver.employee.secondName}</td>
                                            <td>${driver.serialNumber}</td>
                                            <td>${driver.workTime}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div><!-- /.box-body -->
                            <div class="box-footer">
                                <p id="message" class="text-danger">Please select ${driverNumber} drivers. Or create new drivers and get back.</p>
                                <button id="button" type="submit" name="action" value="selectDrivers"  class="btn btn-primary">Submit</button>
                                <br><br>
                                <button id="button1" type="submit" name="action" value="createDrivers"  class="btn btn-primary">Create drivers</button>
                            </div>
                        </form>
                    </div><!-- /.box -->
                </div><!-- /.col -->
            </div><!-- /.row -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <%@ include file="/WEB-INF/manager/footer.jsp" %>
    <!-- DataTables -->
    <script src="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#example1").DataTable();
        });
    </script>

    <script type="text/javascript">
        var countChecked = function() {
            var n = $("input:checked").length;
            if(n == ${driverNumber}){
                $("#button").show("slow");
                $("#message").hide("slow");
            } else{
                $("#button").hide("slow");
                $("#message").show("slow");
            }
        }
        $( "input[type=checkbox]" ).on( "click", countChecked );
        $(document).ready(function(){
            $("#button").hide("fast");
            $("#message").show("fast");
        });
    </script>

    </body>
</html>