<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete category</title>
<jsp:include page="container/_link-css.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="container/_header.jsp"></jsp:include>

	<main class="main">
		<div class="container">
			<h1 class="d-flex justify-content-center">Видалити категорію</h1>
			<form:form name="form"
				action="${pageContext.request.contextPath}/delCat/${catDel.id}"
				method="post" modelAttribute="catDel">
				<div class="card">
					<div class="card-header">Видалення категорії</div>
					<div class="card-body">
						<h5 class="card-title">Ви дійсно хочете видалити цю категорію?</h5>
						<p class="card-text" name="name">${catDel.name}</p>
						<button type="submit" class="btn btn-danger">Видалити</button>
					</div>
				</div>
			</form:form>
		</div>
	</main>

	<jsp:include page="container/_scripts.jsp"></jsp:include>
</body>
</html>