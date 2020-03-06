<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<body>
	<h1>form</h1>
	<form:form method="POST" enctype="utf8" modelAttribute="user">
		<div>
			<form:label path="username">Username</form:label>
			<form:input path="username" />
		</div>

		<div>
			<form:label path="password">Password</form:label>
			<form:input path="password" />
			<form:label path="role">Role</form:label>
			<form:input path="role" />
		</div>

		<input type="submit" value="Submit" />
	</form:form>


</body>
</html>