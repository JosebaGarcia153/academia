<jsp:include page ="include/header.jsp">
	<jsp:param name="pagina" value="login" />
	<jsp:param name="titulo" value="Login" />
</jsp:include>

	<form action="login" method="post">
	
		<input type="text" name="nombre" autofocus placeholder="Nombre">
		<br/>
		<input type="password" id="password" name="password" placeholder="Contraseņa" required>
		<br/>
	        
		<input type="submit" value="Login">
	</form>

<%@include file="include/footer.jsp" %>