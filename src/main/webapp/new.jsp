<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 07.02.2016
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id="form" name="form" method="post" action="login">
  <h1>Registration</h1>
  <p>Please enter the following information</p>

  <label>First Name
    <span class="small">Enter your first name</span>
  </label>
  <input type="text" name="firstName" id="firstName" />

  <label>Middle Name
    <span class="small">Enter your middle name</span>
  </label>
  <input type="text" name="secondName" id="middleName" />

  <label>Last Name
    <span class="small">Enter your last name</span>
  </label>
  <input type="text" name="Serial" id="lastName" />

  <button type="submit">Register</button>
  <div class="spacer"></div>

</form>
</body>
</html>
