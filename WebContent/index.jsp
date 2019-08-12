<%@ page language="java" session="false" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login | SignUp</title>
<script src="./validate.js"></script>
<script src="./main.js"></script>
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.10.0/css/all.css">
<link rel="stylesheet" href="./style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<%@ page errorPage="errorPage.jsp" %>
	<div id="bg">
		<div class="module">
			<ul>
				<li id="liLogin" class="tab activeTab"><span><a
						class="menu active" href="#login" onclick="login()"><i
							class="fas fa-sign-in-alt"></i> Login</a></span></li>
				<li id="liRegister" class="tab"><span><a class="menu"
						href="#register" onclick="register()"><i class="fas fa-user"></i>
							Register</a></span></li>
			</ul>
			<div id="login" class="tab-content">
				<form name="customerLogin" class="form"
					action="CustomerInitController?act=login"
					onsubmit="return validateCustomerLogin();" method="post">

					<input type="text" class="textbox" id="username" name="username"
						placeholder="Enter Username"> <input type="password"
						class="textbox" id="password" name="password"
						placeholder="Enter Password">

					<c:forEach items="${messages}" var="entry">
    					Key = ${entry.key}, value = ${entry.value}<br>
					</c:forEach>
					<button type="submit" class="button">Login</button>
				</form>
			</div>
			<div id="register" style="display: none;">
				<form id="register" name="customerRegistration" class="form"
					action="CustomerInitController?act=createUser"
					onsubmit="return validateCustomerRegistration();" method="post">
					<input type="text" class="textbox" id="customername" name="cname"
						placeholder="Enter your name"> <input type="text"
						class="textbox" id="reg_username" name="username"
						placeholder="Enter username"> <input type="password"
						class="textbox" id="reg_password" name="password"
						placeholder="Enter Password"> <input type="number"
						class="textbox" id="age" name="age" placeholder="Enter Age">

					<label for="gender">Gender: </label>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							id="male" value="Male"> <label class="form-check-label"
							for="male">Male</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							id="female" value="Female"> <label
							class="form-check-label" for="female">Female</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender"
							id="other" value="Other"> <label class="form-check-label"
							for="other">Other</label>
					</div>
					<button type="submit" class="button">Submit</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>