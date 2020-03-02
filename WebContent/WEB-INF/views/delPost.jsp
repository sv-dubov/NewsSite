<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete post</title>
<jsp:include page="container/_link-css.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="container/_header.jsp"></jsp:include>

	<main class="main">
		<div class="container">
			<h1 class="d-flex justify-content-center">Видалити пост</h1>
			<form:form name="form" action="${pageContext.request.contextPath}/delPost/${postDel.id}"
				method="post" modelAttribute="postDel">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<p class="card-text" name="title">${postDel.title}</p>
						<p class="card-text" name="shortDescription">${postDel.shortDescription}</p>
						<p class="card-text" name="description">${postDel.description}</p>
						<p class="card-text" name="meta">${postDel.meta}</p>
						<p class="card-text" name="urlSlug">${postDel.urlSlug}</p>
						<p class="card-text" name="published">${postDel.published}</p>
						<p class="card-text" name="postedOn">${postDel.postedOn}</p>
						<p class="card-text" name="categories">${postDel.categories}</p>
					</div>
				</div>
				<button type="submit" class="btn btn-danger">Видалити</button>
			</form:form>
		</div>
	</main>

	<jsp:include page="container/_scripts.jsp"></jsp:include>
</body>
</html>