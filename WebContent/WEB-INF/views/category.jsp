<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories list</title>
<jsp:include page="container/_link-css.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="container/_header.jsp"></jsp:include>
	<main class="main">
		<div class="container">
			<h1 class="d-flex justify-content-center">Список категорій</h1>
			<a class="button" href="${pageContext.request.contextPath}/addCat">Додати категорію</a>
			<br>
			<table class="table table-bordered" modelAttribute="categories">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>urlSlug</th>
					<th>Description</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach var="cat" items="${categories}">
					<tr>
						<td>${cat.id}</td>
						<td>${cat.name}</td>
						<td>${cat.urlSlug}</td>
						<td>${cat.description}</td>
						<td><a href="editCat/${cat.id}">Edit</a></td>
						<td><a href="deleteCat/${cat.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
	<jsp:include page="container/_scripts.jsp"></jsp:include>
</body>
</html>
