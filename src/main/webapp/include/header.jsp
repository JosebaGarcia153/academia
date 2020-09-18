<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!doctype html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<!-- Todas las rutas relativas comienzan por el href indicado -->
	<!--  ${pageContext.request.contextPath} == http://localhost:8080/academia -->
	<base href="${pageContext.request.contextPath}/" />
	
	<!-- fontawesome 5 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="css/styles.css">
	
	<!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    
    <title> ${param.titulo} | Academia</title>
</head>
<body>