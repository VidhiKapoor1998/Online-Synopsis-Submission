<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action=<%=request.getContextPath() %>/StudentLogin method="post">
	
	
	
		<table>
			<tr>
				<td>RollNumber:</td>
				<td><input type="text" name="rollNum" required/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" required/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"/></td>
			</tr>
		</table>
	
	</form>
	

</body>
</html>