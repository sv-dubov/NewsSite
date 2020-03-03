<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add category</title>
<jsp:include page="container/_link-css.jsp"></jsp:include>
</head>
<body>
<jsp:include page="container/_header.jsp"></jsp:include>

	<main class="main">
		<div class="container">
			<h1 class="d-flex justify-content-center">Додати категорію</h1>
			<form:form name="form" method="post" modelAttribute="cat">
				<div class="form-group">
					<form:label path="name">Назва</form:label>
					<form:input type="text" class="form-control" path="name" />
				</div>

				<div class="form-group">
					<form:label path="urlSlug">url Slug</form:label>
					<form:input type="text" class="form-control" path="urlSlug" />
				</div>

				<div class="form-group">
					<form:label path="description">Опис</form:label>
					<form:textarea type="text" class="form-control" path="description" />
				</div>

				<br>
				<button type="submit" class="btn btn-primary">Додати</button>
			</form:form>
		</div>
	</main>
	<jsp:include page="container/_scripts.jsp"></jsp:include>
</body>
</html>