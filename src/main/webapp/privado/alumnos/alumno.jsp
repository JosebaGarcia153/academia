<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="../../include/header.jsp">
	<jsp:param name="pagina" value="profesor" />
	<jsp:param name="titulo" value="Profesor" />
</jsp:include>

	<h2>Tus cursos</h2>
	
	<table id="myTable1">
		<thead>
			<tr>
				<td>Curso</td>
				<td>Identificador</td>
				<td>Horas</td>
				<td>Profesor</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cursosInscrito}" var="c">
				<tr>
					<td>${c.nombre}</td>
					<td>${c.identificador}</td>
					<td>${c.horas}</td>
					<td>${c.profesor.nombre}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h2>Otros cursos</h2>
	
	<table id="myTable2">
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
			<c:forEach items="${cursosNoInscrito}" var="c">
				<tr>
					<td>${c.nombre}</td>
					<td>${c.identificador}</td>
					<td>${c.horas}</td>
					<td>${c.profesor.nombre}</td>
						<td>
						<a href="agregar-curso?id=${c.id}" onclick="confirmar('${c.nombre}')">
							<i class="fas fa-edit fa-2x" title="Unirse al curso"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

<%@include file="../../include/footer.jsp" %>