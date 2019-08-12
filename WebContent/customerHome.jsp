<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Dashboard</title>
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
	<%@ page errorPage="errorPage.jsp"%>
	<%@ include file="header.jsp"%>

	<% if(session.getAttribute( "customerPojo")==null)
{
throw new Exception();
}
%>

	<div class="container">
		<div class="jumbotron">
			<h4 class="display-4">
				Hello,
				<c:out value="${sessionScope.customerPojo.getName()}" />
				!
			</h4>
			<c:out value="${updateMessage}" />
		</div>
		<div class="card border-info mb-3" style="max-width: 100%;">
			<div class="card-header">Personal Details</div>
			<div class="card-body text-info">
				<h5 class="card-title" style="display: inline;">
					<a href="#accountSummary">View Account Summary</a>
				</h5>
				<button type="button" class="btn btn-outline-primary"
					onclick="displayEditForm();" style="display: inline; float: right;">Edit</button>
				<!-- <p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p> -->
				<table id="detailsTable" class="table table-striped"
					style="margin-top: 2%;">
					<tr>
						<th class="w-25">Name</th>
						<td><c:out value="${sessionScope.customerPojo.getName()}" /></td>
					</tr>
					<tr>
						<th class="w-25">Username</th>
						<td><c:out value="${sessionScope.customerPojo.getUsername()}" /></td>
					</tr>
					<tr>
						<th class="w-25">Gender</th>
						<td><c:out value="${sessionScope.customerPojo.getGender()}" /></td>
					</tr>
					<tr>
						<th class="w-25">Age</th>
						<td><c:out value="${sessionScope.customerPojo.getAge()}" /></td>
					</tr>
				</table>
				<div>
					<form
						action="CustomerHomeController?customerId=<c:out value = '${sessionScope.customerPojo.getCustomerId()}'/>&act=editCustomer"
						class="center" id="editDetailsForm"
						style="display: none; margin-top: 2%; margin-left: 2%;"
						method="post">
						<div class="form-group">
							<label for="cname">Name</label> <input type="text"
								class="form-control" id="customername" name="cname"
								value="<c:out
									value='${sessionScope.customerPojo.getName()}' />">
						</div>
						<div class="form-group">
							<label for="username">Username</label> <input type="text"
								readonly class="form-control" id="username" name="username"
								value="<c:out
									value='${sessionScope.customerPojo.getUsername()}' />">
						</div>
						<div class="form-group">
							<label for="gender">Gender</label>
							<div class="form-check form-check-inline">
								<input type="radio" class="form-check-input" name="gender"
									value="Male"
									<c:if test="${sessionScope.customerPojo.getGender()=='Male'}">checked</c:if>>

								<label class="form-check-label" for="male">Male</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="female" value="Female"
									<c:if test="${sessionScope.customerPojo.getGender()=='Female'}">checked</c:if>>
								<label class="form-check-label" for="female">Female</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender"
									id="other" value="Other"
									<c:if test="${sessionScope.customerPojo.getGender()=='Other'}">checked</c:if>>
								<label class="form-check-label" for="other">Other</label>
							</div>
							<div class="form-group">
								<label for="age">Age</label> <input type="text"
									class="form-control" id="age" name="age"
									value="<c:out
									value='${sessionScope.customerPojo.getAge()}' />">
							</div>
						</div>
						<button type="submit" class="btn btn-primary">Save
							Changes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		function displayEditForm() {
			document.getElementById("detailsTable").style.display = "none";
			document.getElementById("editDetailsForm").style.display = "block";
		}
	</script>
</body>
</html>