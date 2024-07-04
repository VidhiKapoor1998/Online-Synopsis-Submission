<html>
<body>
<h2>University</h2>

<form action="WelcomeServlet" method="get">
		<a href="<%=request.getContextPath() %>/WelcomeServlet?page=StudentLogin">Student login</a></br>
		<a href="<%=request.getContextPath() %>/WelcomeServlet?page=FacultyLogin">Faculty login</a></br>
		<a href="<%=request.getContextPath() %>/WelcomeServlet?page=AdminLogin">Admin login</a></br>
</form>

</body>
</html>
