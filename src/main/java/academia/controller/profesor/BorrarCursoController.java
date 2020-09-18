package academia.controller.profesor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import academia.controller.Alerta;
import academia.modelo.dao.impl.CursoDAOImpl;
import academia.modelo.pojo.Curso;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/borrar-curso")
public class BorrarCursoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(BorrarCursoController.class);
	
	private static final CursoDAOImpl dao = CursoDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idParameter = request.getParameter("id");
		int id = Integer.parseInt(idParameter);
		
		String mensaje = "";
		boolean fallo = true;
		
		try {
			Curso curso = dao.borrar(id);
			mensaje = curso.getNombre() + " borrado";
			fallo = false;
			
		} catch (Exception e) {
			
			mensaje = "Error: " + e.getMessage();
			LOG.error(e);
			fallo = true;
			
		} finally {
			
			if (fallo == false) {
				request.setAttribute("alerta", new Alerta("success", mensaje));				
			} else {
				request.setAttribute("alerta", new Alerta("danger", mensaje));
			}
			
			request.getRequestDispatcher("profesor").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}
