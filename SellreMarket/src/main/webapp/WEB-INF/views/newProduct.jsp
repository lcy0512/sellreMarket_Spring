<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!--
	1. Date : 2024.02.10
	2. Author : Woody Jo
	3. Version : v1.0.0
	4. Description : new 제품들 가져오는 jsp
-->
<head>
<meta charset="UTF-8">
<title>Sellre Market New</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="css/style.css" rel="stylesheet">

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iK7t1TIq0IYzcqL/tnuU6J1bYWFIVXutUPnN0PBKpZQUMuXAfmgt5L9a1"
	crossorigin="anonymous">

<!-- Bootstrap JavaScript (requires Popper.js) -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofNol/wwZQqjW9M4aPskD5S/R1HD87Hjr"
	crossorigin="anonymous">
</script>

<!-- // js 따로 관리한다. -->
<script src="js/newProductPage.js"></script>

</head>
<body>
    
	<!-- Topbar Start -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- Topbar End -->

	<!-- Ad Start -->
	<div class="container" style="width: 100%">
		<a href="#"><img class="img-fluid w-100"
			src="${pageContext.request.contextPath}/image/event/new/${img}"
			alt="Event Image">
		</a>
	</div>
	<br>
	<br>
	<br>
	<!-- Ad End -->

	<h3 align="center">신상품</h3>

	
	<!-- Align by Category Start -->
	<div class="setAlign" style="margin-left: 70%; color: #919492;">
		<c:if test="${alignCategory eq '신상품순'}">
			<span style="color: black; font-weight: bold">신상품순</span>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="alignNewLowPrice.do">낮은 가격순</a> 
			&nbsp;&nbsp;|&nbsp;&nbsp; 
			<a href="alignNewHighPrice.do">높은 가격순</a>
		</c:if>
		<c:if test="${alignCategory eq '낮은 가격순'}">
			<a href="mainPage.do">신상품순</a>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<span style="color: black; font-weight: bold">낮은 가격순</span>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="alignNewHighPrice.do">높은 가격순</a>
		</c:if>
		<c:if test="${alignCategory eq '높은 가격순'}">
			<a href="mainPage.do">신상품순</a>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="alignNewLowPrice.do">낮은 가격순</a>
			&nbsp;&nbsp;|&nbsp;&nbsp;
			<span style="color: black; font-weight: bold">높은 가격순</span>
		</c:if>
	</div>
	<!-- Align by Category End -->
	
	
	<!-- Products Start -->
	
	<div class="container-fluid pt-5 pb-3">
		<div class="row px-xl-5 justify-content-center"
			style="margin-left: 10%; margin-right: 0%;">
			<c:if test="${not empty newProducts}">
				<c:forEach items="${newProducts}" var="dto">
					<div class="col-lg-4 col-md-4 col-sm-6 pb-10 mx-auto">
						<div class="product-item bg-light mb-4"
							style="width: 300px; height: 350px; display: flex; flex-direction: column; justify-content: center;">
							<div class="product-img position-relative overflow-hidden">
									<a href="productDetail.do?productId=${dto.productid}" ><img class="img-fluid w-100"
										src="${pageContext.request.contextPath}/image/product/${dto.pimage}"
										alt="Product Image"
										 style="object-fit: cover; width: 100%; height: 100%;">
									</a>
							</div>
							<div
								style="margin-top: 7px; margin-left: 1%; border: 1px solid lightgray; border-radius: 5px; width: 98%;">
									<button
										onclick="sendProductInfo(${dto.productid}); return false;"
										class="btn btn-primary btn-light align-items-center"
										style="width: 100%;">장바구니</button>
									<input type="hidden" id="productid" value="${dto.productid}">
									<input type="hidden" id="id" value="${id}">
							</div>
							
							<div class="text-center py-4"
								style="display: flex; flex-direction: column; justify-content: center;">
								<a class="h6 text-decoration-none text-truncate" href="productDetail.do?productId=${dto.productid}" style="font-weight: bold;">${dto.pname}</a>
								<div class="d-flex align-items-center justify-content-center mt-2">
									<h7 class="text-muted ml-2">
										<c:if test="${dto.price ne dto.dPrice}">
											<del>${dto.price}</del>
											<h7>원</h7>
										</c:if>
									</h7>
								</div>
								<div class="d-flex align-items-center justify-content-center mt-2">
									<span class="h6" style="color: red; font-weight: bold;">
										<c:if test="${dto.price ne dto.dPrice}">
											${dto.salerate}%
										</c:if>
									</span>
									&nbsp;&nbsp;
									<h6>${dto.dPrice}</h6>
									<h6>원</h6>
									
								</div>
								<div
									class="d-flex align-items-center justify-content-center mb-1">
									<img
										src="https://cdn-icons-png.flaticon.com/128/535/535234.png"
										style="width: 12px; height: 12px;">&nbsp; <small>${dto.plikecount}</small>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<!-- Products End -->


	<!-- Paging Start -->
	<script src="js/paging.js"></script>
	<div id="showPaging" style="text-align: center;"></div>
	<input type="hidden" value="${curPage}" id="curPage">
	<!-- Paging End -->

	


	<!-- Footer Start -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Footer End -->


</body>
</html>