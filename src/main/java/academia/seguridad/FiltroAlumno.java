package academia.seguridad;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import academia.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class FiltroAlumno
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/privado/alumnos/*" })
public class FiltroAlumno implements Filter {
	
	private static final Logger LOG = Logger.getLogger(FiltroAlumno.class);
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("Filtro es destruido");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Cuidado que hay que castear de ServletRequest a HttpServletRequest
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//Recogemos la url de como comienza nuestra App para construir una ruta ABSOLUTA
		String urlIndexApp = req.getContextPath();
		
		LOG.trace("filtrando " + req.getRequestURI());
		
		//Recuperar usuario de session
		Usuario usuarioLogin = (Usuario) req.getSession().getAttribute("login_usuario");
		
		if (usuarioLogin == null) {
			
			LOG.warn("No ha pasado a traves del LOGIN, usuario es NULL, NO TIENE AUTORIZACION");
			//res.sendRedirect("login.jsp"); => Ruta relativa, se mete en un bucle
			res.sendRedirect(urlIndexApp + "login.jsp"); //Ruta absoluta
			
		} else if (usuarioLogin.getRol() != Usuario.ROL_ALUMNO) {
			
			LOG.warn("Usuario sin privilegios de ALUMNO, SIN AUTORIZACION");
			res.sendRedirect(urlIndexApp + "login.jsp");
			
		} else {
			
			//Si el usuario esta logeado 
			//Dejamos pasar y continua la request
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
