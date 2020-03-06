<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Homepage</title>
<jsp:include page="container/_cleanblog-css.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="container/_header.jsp"></jsp:include>

	<main class="main">
		<!-- Page Header -->
		<header class="masthead"
			style="background-image: url('resources/clean-blog/img/home-bg.jpg')">
			<div class="overlay"></div>
			<div class="container">
				<div class="row">
					<div class="col-lg-8 col-md-10 mx-auto">
						<div class="site-heading">
							<h1>Наші новини</h1>
							<span class="subheading">От така курсова робота</span>
						</div>
					</div>
				</div>
			</div>
		</header>

		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-10 mx-auto" modelAttribute="home">
					<c:forEach var="post" items="${home}">
						<div class="post-preview">
							<a href="home.jsp">
								<h2 class="post-title">${post.title}</h2>
								<h3 class="post-subtitle">${post.shortDescription}</h3>
							</a>
							<p class="post-meta">Posted on ${post.postedOn}</p>
						</div>
						<hr>
					</c:forEach>
					<hr>
				</div>
			</div>
		</div>
		<hr>
	</main>
	<jsp:include page="container/_scripts.jsp"></jsp:include>
</body>
</html>