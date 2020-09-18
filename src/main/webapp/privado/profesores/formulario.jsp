<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<jsp:include page ="../../include/header.jsp">
	<jsp:param name="pagina" value="profesor" />
	<jsp:param name="titulo" value="Profesor" />
</jsp:include>
	
	<h1>Agregar un nuevo curso</h1>
	
	<form action="crear-curso" method="post" enctype="multipart/form-data">
		
		<div class="form-group">
			<label for="nombre">Nombre:</label>
			<input type="text" name="nombre" id="nombre" value="${curso.nombre}" class="form-control" autofocus placeholder="Escribir nombre del curso">
		</div>
		
		<div class="form-group">
			<label for="identificador">Identificador:</label>
			<input type="text" name="identificador" id="identificador" value="${curso.identificador}" class="form-control" placeholder="Escribir el identificador del curso">
		</div>
		
		<div class="form-group">
			<label for="horas">Horas:</label>
			<input type="text" name="horas" id="horas" value="${curso.horas}" class="form-control" placeholder="Escribir la duracion del curso">
		</div>
		
		<input type="submit" value="Guardar">   
	</form>

<%@include file="../../include/footer.jsp" %>