<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="../../include/header.jsp">
	<jsp:param name="pagina" value="profesor" />
	<jsp:param name="titulo" value="Profesor" />
</jsp:include>

	<h1>Tus cursos</h1>
	
	<table id="myTable1">
		<thead>
			<tr>
				<td>Curso</td>
				<td>Identificador</td>
				<td>Horas</td>
				<td>Profesor</td>
				<td>Operaciones</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cursos}" var="c">
				<tr>
					<td>${c.nombre}</td>
					<td>${c.identificador}</td>
					<td>${c.horas}</td>
					<td>${c.profesor.nombre}</td>
					<td>
						<a href="borrar-curso?id=${c.id}" onclick="confirmar('${c.nombre}')">
							<i class="fas fa-trash fa-2x" title="Borrar Curso"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2><a href="crear-curso">Crear nuevo curso</a></h2>
	

<%@include file="../../include/footer.jsp" %>