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
                Create the ${number+1} item of the order. Select or create city.
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Select city</h3>
                        </div><!-- /.box-header -->
                        <form role="form" action="/page/manager/orders/create" method="post">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="city0">From the city:</label>
                                <input type="hidden" class="form-control" value="${cityFrom.id}" name="cityFrom">
                                <input id="city0" type="text" class="form-control" value="${cityFrom.name}"  readonly>
                            </div>
                            <div class="form-group">
                                <label for="inputCity">To the city:</label>
                                <select id="inputCity" class="form-control" name="cityTo">
                                    <c:forEach var="city" items="${cityList}">
                                        <option value="${city.id}"}>${city.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="box-footer">
                            <input type="hidden" name="number" value="${number}">
                            <button type="submit" name="action" value="chooseCity" class="btn btn-primary">Submit</button>
                        </div>
                        </form>
                    </div>
                    <div class="box" id="interval" ${visible eq true ? '': 'hidden'}>
                        <div class="box-header with-border">
                            <h5 class="box-title">It is not known the distance between cities, enter it or select a different city.</h5>
                        </div><!-- /.box-header -->
                        <form role="form" action="/page/manager/orders/create" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="city1">From the city:</label>
                                    <input type="hidden" class="form-control" value="${cityFrom.id}" name="cityFrom">
                                    <input id="city1" type="text" class="form-control" value="${cityFrom.name}"  readonly>
                                </div>
                                <div class="form-group">
                                    <label for="city2">To the city:</label>
                                    <input type="hidden" class="form-control" value="${cityTo.id}" name="cityTo">
                                    <input id="city2" type="text" class="form-control" value="${cityTo.name}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="interval1">Interval(hours):</label>
                                    <input id="interval1" name="interval" type="number" min="1" class="form-control" required>
                                </div>
                            </div>
                            <div class="box-footer">
                                <input type="hidden" name="number" value="${number}">
                                <button type="submit" name="action" value="createInterval" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">Create city</h3>
                        </div><!-- /.box-header -->
                        <form role="form" action="/page/manager/orders/create" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="city3">From the city:</label>
                                    <input type="hidden" class="form-control" value="${cityFrom.id}" name="cityFrom">
                                    <input id="city3" type="text" class="form-control" value="${cityFrom.name}"  readonly>
                                </div>
                                <div class="form-group">
                                    <label for="cityName1">To the city:</label>
                                    <input id="cityName1" type="text" class="form-control"  name="cityName" required>
                                </div>
                                <div class="form-group">
                                    <label for="interval2">Interval(hours):</label>
                                    <input id="interval2" type="number" min="1" class="form-control" name="interval" required>
                                </div>
                                <p class="text-danger">${errorCreateCity}</p>
                            </div>
                            <div class="box-footer">
                                <input type="hidden" name="number" value="${number}">
                                <button type="submit" name="action" value="createCity" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-2">
                    <form role="form" action="/page/manager/orders/create" method="post">
                        <p class="text-danger">${errorWithOrder}</p>
                        <input type="hidden" name="number" value="${number}">
                        <button type="submit" name="action" value="completeOrder" class="btn btn-primary">Complete order</button>
                    </form>
                </div>
            </div>



            <!-- Your Page Content Here -->

        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->

    <%@ include file="/WEB-INF/manager/footer.jsp" %>

    <script type="text/javascript">
        function showDiv(elem){
            if(elem.value == "TRANSIT"){
                $("#TRANSIT").show("slow");
                $("#LOADING").hide("slow");
                $("#UNLOADING").hide("slow");
            }
            if(elem.value == "LOADING"){
                $("#TRANSIT").hide("slow");
                $("#LOADING").show("slow");
                $("#UNLOADING").hide("slow");
            }
            if(elem.value == "UNLOADING"){
                $("#TRANSIT").hide("slow");
                $("#LOADING").hide("slow");
                $("#UNLOADING").show("slow");
            }
        }
        $(document).ready(function(){
            $("#TRANSIT").show("fast");
            $("#LOADING").hide("fast");
            $("#UNLOADING").hide("fast");
        });
    </script>

    </body>
</html>