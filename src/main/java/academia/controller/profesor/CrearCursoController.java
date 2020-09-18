package academia.controller.profesor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import academia.controller.Alerta;
import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

		
/**
 * Servlet implementation class EntryCreationController
 */
@WebServlet("/crear-curso")
public class CrearCursoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CrearCursoController.class);
	
	private static CursoDAOImpl dao = CursoDAOImpl.getInstance();
	
	//Validators
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			Curso curso = new Curso();
			
			String idParameter = request.getParameter("id");
			
			if (idParameter != null) {
				int id = Integer.parseInt(idParameter);
				curso = dao.buscarPorId(id);
			}
			
			request.setAttribute("curso", curso);
			
		} catch (Exception e) {
			LOG.error(e);

		} finally {		
			response.sendRedirect("privado/profesores/formulario.jsp");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();
		Curso curso = new Curso();
		Alerta alerta = null;
		
		String url = "";
		
		//Define parametros
		int horas = 0;
		
		try {
			//recoge parametros de formulario			
			String paramNombre = request.getParameter("nombre");
			String paramIdentificador = request.getParameter("identificador");
			String paramHoras = request.getParameter("horas");
			
			horas = Integer.parseInt(paramHoras);
			
			//Recuperar usuario de session y setearlo en el curso
			usuario = (Usuario)session.getAttribute("login_usuario");
			
			//Guarda los parametros en la instancia
			curso.setNombre(paramNombre);		
			curso.setIdentificador(paramIdentificador);
			curso.setHoras(horas);
						
			//Recuperar usuario de sesion y setearlo en el curso
			curso.setProfesor(usuario);
			
			//set de violaciones
			Set<ConstraintViolation<Curso>> violations = validator.validate(curso);
			
			if ( violations.isEmpty() ) {
				
				//Crea el curso
				dao.crear(curso);
				
				alerta = new Alerta("success","Curso agregado");
				url = "profesor";
				
			} else {
				
				String errores = "";
				
				//Guarda las violaciones en un string de errores
				for (ConstraintViolation<Curso> violation : violations) {
					errores += "<p><b>" + violation.getPropertyPath() + "</b>: "  + violation.getMessage() + "</p>";
				}

				alerta = new Alerta("danger",errores);
				url = "privado/profesores/formulario.jsp";
			}//if
			
		} catch (SQLException e) {
			
			LOG.error(e);
			alerta = new Alerta("danger", e.getMessage());
			url = "privado/profesores/formulario.jsp";
			
		} catch (Exception e) {
			
			LOG.error(e);
			alerta = new Alerta("danger","Ha habido un error");
			url = "privado/profesores/formulario.jsp";
			
		} finally {
			
			//Recoge los atributos y vuelve a la vista
			request.setAttribute("curso", curso);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(url).forward(request, response);
		}//finally
	}//post
}