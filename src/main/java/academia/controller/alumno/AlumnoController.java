package academia.controller.alumno;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import academia.modelo.pojo.Usuario;

import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;

/**
 * Servlet implementation class ProfesorController
 */
@WebServlet("/alumno")
public class AlumnoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(AlumnoController.class);
	private static final CursoDAOImpl dao = CursoDAOImpl.getInstance();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Curso> cursosInscrito = new ArrayList<Curso>();
		ArrayList<Curso> cursosNoInscrito = new ArrayList<Curso>();
		
		try {
			
			Usuario userSession = (Usuario) request.getSession().getAttribute("login_usuario");
			int idUsuario = userSession.getId();
			
			cursosInscrito = dao.listarCursosInscrito(idUsuario);
			cursosNoInscrito = dao.listarCursosNoInscrito(idUsuario);
			
		} catch (Exception e) {
			
			LOG.error(e);
			
		} finally {
			
			request.setAttribute("cursosInscrito", cursosInscrito);
			request.setAttribute("cursosNoInscrito", cursosNoInscrito);
			request.getRequestDispatcher("privado/alumnos/alumno.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
