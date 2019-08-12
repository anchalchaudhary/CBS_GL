<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="#">CBS Bank</a>
		
		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="CustomerHomeController?act=dashboard">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link"
					href="CustomerHomeController?act=addMoney">Add Money</a></li>
				<li class="nav-item"><a class="nav-link"
					href="CustomerHomeController?act=sendMoney">Send Money</a></li>
				<li class="nav-item"><a class="nav-link"
					href="CustomerHomeController?act=logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<% Cookie cookies[] = request.getCookies();
		for(int i=0;i<cookies.length;i++){
			out.print(cookies[i].getName());
		}%>
</body>
</html>