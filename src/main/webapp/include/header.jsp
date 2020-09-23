<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!doctype html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <title>MF0226_3: Ipartek by Elier Otero</title>
	<meta name="description" content="Ipartek formación"> 
    <meta name="viewport" content="width=device-width, user-scalable=no">
    
	<!-- Todas las rutas relativas comienzan por el href indicado -->
	<!--  ${pageContext.request.contextPath} == http://localhost:8080/academia -->
	<base href="${pageContext.request.contextPath}/" />
	
	<!-- fontawesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="css/style.css">
	
	<!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    
     <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    
</head>
<body>
	<header>
	    <div id="header_title">
	        <div id="header_img">
	            <img src="img/logo.png" alt="Maleta" title="Musix">
	        </div>
	    </div>
	</header>
	<nav id="menu_principal">
	    <ul>
	        <li><a href="index.jsp"><span class="icono home"></span>Inicio</a></li>
	    </ul>
	</nav>
	
	<jsp:include page="alertas.jsp"></jsp:include>
	
	