<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit category</title>
<jsp:include page="container/_link-css.jsp"></jsp:include>
</head>
<body>
<jsp:include page="container/_header.jsp"></jsp:include>

	<main class="main">
		<div class="container">
			<h1 class="d-flex justify-content-center">Редагувати категорію</h1>
			<form:form name="form" action="${pageContext.request.contextPath}/editCat/${catEdit.id}" method="post" modelAttribute="catEdit">
				<div class="form-group">
					<form:label path="name">Назва</form:label>
					<form:input type="text" class="form-control" path="name" id="name" />
				</div>

				<div class="form-group">
					<form:label path="urlSlug">url Slug</form:label>
					<form:input type="text" class="form-control" path="urlSlug" id="urlSlug" />
				</div>

				<div class="form-group">
					<form:label path="description">Опис</form:label>
					<form:textarea type="text" class="form-control" path="description" id="description" />
				</div>

				<br>
				<button type="submit" class="btn btn-primary">Редагувати</button>
			</form:form>
		</div>
	</main>
	<jsp:include page="container/_scripts.jsp"></jsp:include>
</body>
</html>