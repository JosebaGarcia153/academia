<h1><a href="login.jsp">Login</a></h1>


<h2>Para Hoy y entregar: LOGIN</h2>
<ol>
	<li>Enlace para formulario de login, o ponerlo en esta misma pagina</li>
	<li>formulario, que llame a un conrolador con ACTION y GET, parametros [usuario,password] </li>
	<li>Controlador que escuche en la url de "/login"</li>
	<li>Comprobar si existe usuario en la bbdd, declarar nuevo metodo en DAO e implementarlo</li>
	<ol>
		<li>Si no existe, volver al login y mensaje al usurio</li>
		<li>si es PROFESOR, que vaya a una jsp "privado/profesor.jsp"</li>
		<li>si es ALUMNO, que vaya a una jsp "privado/alumno.jsp"</li>
	</ol>
	
</ol>

<h2>TAREAS</h2>
<ol>
	<li>Maquetar pagina web, css e includes para reutilizar</li>
	<li>miercoles: Si se logea un PROFESOR que muestre sus curso, desde la misma JSP podria crear un nuevo curso y eliminarlo</li>
	<li>jueves: Si se logea un ALUMNO que muestre sus curso a los que esta inscrito, desde la misma JSP apuntarse a un nuevo curso</li>	
</ol>

<h2>EXTRAS</h2>
<ol>
	<li>Trazas de LOG</li>
	<li>Singleton</li>
	<li>Gestion de errores TRY y CACTH sobre todo en los controladore</li>
	<li>Validacion de datos de los formulario con javax.validation</li>
	<li>Gestion de Seguridad, que los profesores y alumnos solo puedan modificar sus datos, No poder saltarse el Login</li>	
	<li>Un ALUMNO puede escribir una reseña de un curso/profesor, calificando del 1 al 5 y escribiendo un comentario</li>	
</ol>

<h1> Listado Cursos </h1>

${cursos}