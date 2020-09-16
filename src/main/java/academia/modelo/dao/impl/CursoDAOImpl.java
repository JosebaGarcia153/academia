package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.CursoDAO;
import academia.modelo.pojo.Curso;
import academia.modelo.pojo.Usuario;

public class CursoDAOImpl implements CursoDAO {
	
	private static final Logger LOG = Logger.getLogger(CursoDAOImpl.class);
	private static CursoDAOImpl instance = null;
	
	private CursoDAOImpl() {
		super();	
	}
	
	public static synchronized CursoDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new CursoDAOImpl();
		}	
		return instance;
	}
	
	private final static String SQL_LISTAR = "SELECT \n" + 
												"	c.id as 'curso_id', " + 
												"	c.identificador, " + 
												"	c.nombre as 'curso_nombre', " + 
												"	c.horas, " + 
												"	f.id as 'profesor_id', " + 
												"	f.nombre as 'profesor_nombre', " + 
												"	f.apellidos as 'profesor_apellidos', " + 
												"	rol " +												
												" FROM cursos c, usuarios f " + 
												" WHERE " + 
												"	c.id_profesor = f.id;";
	

	@Override
	public ArrayList<Curso> listar() {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			 ResultSet rs = pst.executeQuery()	
			){
			
			LOG.debug(pst);
			while ( rs.next() ) {				
				
				Curso c = new Curso();
				c.setId( rs.getInt("curso_id"));
				c.setNombre( rs.getString("curso_nombre"));
				c.setIdentificador(rs.getString("identificador"));
				c.setHoras(rs.getInt("horas"));
				
				Usuario p = new Usuario();
				p.setId(rs.getInt("profesor_id"));
				p.setNombre(rs.getString("profesor_nombre"));
				p.setApellidos( rs.getString("profesor_apellidos"));
				p.setRol( rs.getInt("rol"));
								
				c.setProfesor(p);
				
				cursos.add(c);
				
			}	
		}catch (Exception e) {
			LOG.error(e);
		}
		
		return cursos;
	}
}
