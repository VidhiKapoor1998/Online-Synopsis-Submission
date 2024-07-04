<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <script>
		function logout() {
		  alert("Click ok if you want to log out");
		}
	</script>
	  <a href="<%=request.getContextPath() %>/Logout" onclick="logout()">Logout</a><br/>
	
	<a href="<%=request.getContextPath() %>/uploadSynopsis.jsp">Upload Synopsis</a>
	<br/>
	<a href="<%=request.getContextPath() %>/viewStatus">View Status</a>
	
	
	
	
</body>
</html>