<%@ page import="com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>Hello World!</h2>
<p>Please enter your login information
    <br/>New User? <a href="new.jsp">Register</a></p>
<table>
    <thead>
    <tr>
        <th>User ID</th>
        <th>First Name</th>
        <th>Secon Name</th>
        <th>Serial Number</th>
    </tr>
    </thead>
    <tbody>
        <%
					 List<EmployeeEntity> list = (List<EmployeeEntity>) session.getAttribute("all");
					 for (EmployeeEntity u : list) {
				 %>
    <tr>
    <td><%=u.getId()%></td>
        <td><%=u.getFirstName()%></td>
        <td><%=u.getSecondName()%></td>
        <td><%=u.getSerialNumber()%></td>
    </tr>
        <%}%>
    <tbody>
</table>
</body>
</html>
