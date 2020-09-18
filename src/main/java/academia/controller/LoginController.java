package academia.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academia.modelo.dao.impl.UsuarioDAOImpl;
import  academia.modelo.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recoge parametros del formulario
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		//Busca el usuario en la BBDD
		Usuario usuario = dao.buscar(nombre, password);
		
		if (usuario == null) {
			//Si no esta el usuario se queda en la misma pagina
			request.setAttribute("alerta", new Alerta("danger", "Informacion erronea"));
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		} else {

			//Si esta el usuario entonces inicia una session de 50 minutos
			session.setMaxInactiveInterval(60 * 1 * 50);
			session.setAttribute("login_usuario", usuario); // @see ListenerUsuarioLogeados => attributeAdded
			request.setAttribute("alerta", new Alerta("success", nombre + " estas logeado"));	
			
			//Va a una pagina u otra dependiendo del Rol
			if (usuario.getRol() == usuario.ROL_PROFESOR) {
				request.getRequestDispatcher("profesor").forward(request, response);
			} else {
				request.getRequestDispatcher("alumno").forward(request, response);
			}
		}//if
	}
}