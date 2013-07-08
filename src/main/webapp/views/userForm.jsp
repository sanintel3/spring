<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>

	<form:form method="POST" commandName="user" action="/spring/user/add">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>First Name :</td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email ID :</td>
				<td><form:input path="emailId" /></td>
				<td><form:errors path="emailId" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Employee ID :</td>
				<td><form:input path="employeeId" /></td>
				<td><form:errors path="employeeId" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Role :</td>
				<td><form:input path="role" /></td>
				<td><form:errors path="role" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>
	
	<div id="logout">
	                   <span><a href="/spring/j_spring_security_logout">Log Out</a></span>
    </div>

</body>
</html>