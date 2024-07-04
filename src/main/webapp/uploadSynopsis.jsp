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
		<h1>RollNumber:<%= session.getAttribute("student") %></h1>
	  <a href="<%=request.getContextPath() %>/Logout">Logout</a>
	
	<form action="UploadSynopsis" method="post" enctype='multipart/form-data'>
		<h3>Select file</h3><br/>
		(File format should be: .doc,.txt,.pdf)<br/>
		<input type="file" name="synopsis" accept=".doc,.txt,.pdf" required/><br/>
		<input type="submit">
	</form>

</body>
</html>