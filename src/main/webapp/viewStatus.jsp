<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table,th,td{
		border:2px solid black;
	}
</style>
</head>
<body>
	<div>
						
							<script>
								function logout() {
								  alert("Click ok if you want to log out");
								}

								console.log('inside script');
								const status=<%=session.getAttribute("status") %>;
								const date=<%=session.getAttribute("date") %>
								const rollNumber=<%=session.getAttribute("student")%>;

								document.getElementById("status").innerHTML=status;
								document.getElementById("date").innerHTML=date;
								document.getElementById("student").innerHTML=rollNumber;
								const msg="Submitted";

								if(status==msg){

										const container=document.getElementById("root");
										console.log("status is submitted");
										const link=document.createElement('a');
										link.innerHTML="Click here to check result";
										link.setAttribute('href','checkResult.jsp');
								}
								
							</script>
							
							
							
							
	  <a href="<%=request.getContextPath() %>/Logout" onclick="logout()">Logout</a><br/>
	
		 <a href="Student.jsp">Back</a><br/>	
			
	<table >
		<tr>
			<th>Roll number</th>
			<th>Date of submission</th>
			<th>Status</th> 	
		</tr>
		
		<tr>
			<td id="student"></td>
			<td id="date"></td>
			<td id="status"></td>
		</tr>
		
	</table>
	</div>
</body>
</html>