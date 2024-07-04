<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
			System.out.println("INSIDE SCRIPTLETS...");
			Boolean fileStatus=(Boolean)session.getAttribute("status");
			System.out.println("Value of file status:"+fileStatus);
			boolean status=(fileStatus!=null)?fileStatus:false;
			System.out.println(status);
		%>
		
		 <a href="<%=request.getContextPath() %>/Logout" onclick="logout()">Logout</a><br/>
		
	<script>
							function logout() {
							  alert("Click ok if you want to log out");
							}
					
							const status=<%=status%>;
							if(!status){
								alert('file not downloaded');
								location.href='FileNotDownloaded.jsp';
								
							}
					</script>
	
	 
	
	<form action="updateResult" method="get">
		  <!-- <h1>ID: <%=session.getAttribute("faculty") %></h1>
		<h4>ID: <%=session.getAttribute("task") %></h4> -->
		<input type="radio" id="result" name="result" value=true >
		<label for="result" id="child" >Approved</label><br>
		
		
		<input type="radio" id="result" name="result" value=false >
		<label for="result" id="child" >Rejected</label><br>
		
		<textarea id="feedback" name="feedback" rows="10" cols="100" ></textarea>
		
					
		<br/>
		
		<input type="submit" value ="Submit" >
		
	</form>
	
	
	
</body>
</html>