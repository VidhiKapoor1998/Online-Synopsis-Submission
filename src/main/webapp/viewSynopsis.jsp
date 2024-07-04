<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="root">
	
	
	
		<h1>Faculty ID:<%= session.getAttribute("faculty") %></h1><br>
		  <a href="<%=request.getContextPath() %>/Logout">Logout</a>
	<form action="DownloadSynopsis" method="post">
	
		<h4>Task ID:<%= session.getAttribute("task") %></h4><br>
		
		Download:
			
				<button  id="submit" onclick="addButton()">↓</button>
				
			
		<!-- <a href="www.google.com">
			<button id="updateStatus" disabled>Update Result</button>
		</a>  -->


	</form>
	
	
</div>
	<script type="text/javascript">
	function logout() {
		  alert("Click ok if you want to log out");
		}

	
	
		function addButton(){
			console.log('Inside addButton function...');
	

		
			const container=document.getElementById("root");
			console.log(container+ "container found");

			const updateStatusButton=document.createElement('a');
			updateStatusButton.innerHTML='<button>Next</button>';
			updateStatusButton.setAttribute('href','result.jsp');
			container.appendChild(updateStatusButton);
			
				
		
		}
	</script>
			
	

</body>
</html>