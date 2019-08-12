<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Money</title>
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
	<br />
	<div class="container">
		<form action="CustomerHomeController?customerId=<c:out value = '${sessionScope.customerPojo.getCustomerId()}'/>&act=addAmount" method="post">
			Add Money: <input type="number" name="amount" />
			<button type="submit">Add</button>
		</form>
	</div>
</body>
</html>