package academia.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import academia.controller.Alerta;


	
/**
 * Application Lifecycle Listener implementation class IndexListener
 *
 */
@WebListener
public class IndexListener implements ServletContextListener {
	
	private static final Logger LOG = Logger.getLogger(IndexListener.class);
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// cuando paramos la App
    	LOG.info("Shutting down the server");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	//Cuando ejecutamos la App en el Servidor, al arrancar la 1º vez
    	
    	//Evento de log
    	LOG.info("Executing the APP");
    	
    	// Este contexto es para toda la Aplicacion y es accesible desde cualquier JSP o Servlet    	
    	ServletContext contextoAplicacion = sce.getServletContext();
    	
    	try {
    		contextoAplicacion.setAttribute("logged_users", 0);

    	} catch (Exception e) {
    		
    		LOG.fatal(e);
    		contextoAplicacion.setAttribute("alert", new Alerta("danger", "Tenemos un problema desconocido"));
		}	
    }
}
