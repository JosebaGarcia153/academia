package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private final static String SQL_LISTAR = "SELECT c.id 'curso_id', c.identificador, c.nombre 'curso_nombre', c.horas, u.id 'profesor_id', u.nombre 'profesor_nombre', u.apellidos as 'profesor_apellidos', rol "
												+ " FROM cursos c, usuarios u "
												+ " WHERE c.id_profesor = u.id "
												+ " ORDER BY c.id ASC LIMIT 500; ";
	
	
	private final static String SQL_BUSCAR_POR_ID = "SELECT c.id 'curso_id', c.identificador, c.nombre 'curso_nombre', c.horas,"
												+ " u.id 'profesor_id', u.nombre 'profesor_nombre', u.apellidos as 'profesor_apellidos', rol "
												+ " FROM cursos c, usuarios u "
												+ " WHERE c.id_profesor = u.id AND c.id = ?"
												+ " ORDER BY c.id ASC LIMIT 500; ";
	
	private final static String SQL_BUSCAR_POR_ID_CON_USUARIO = "SELECT c.id 'curso_id', c.identificador, c.nombre 'curso_nombre', c.horas,"
												+ "u.id 'profesor_id', u.nombre 'profesor_nombre', u.apellidos as 'profesor_apellidos', rol "
												+ " FROM cursos c, usuarios u "
												+ " WHERE c.id_profesor = u.id AND c.id = ? AND u.id = ?"
												+ " ORDER BY c.id ASC LIMIT 500; ";
	
	private final static String SQL_CURSOS_POR_PROFESOR = "SELECT c.id, c.nombre, c.identificador, c.horas, CONCAT(u.nombre, ' ', u.apellidos) AS 'profesor'"
												+ " FROM cursos c, usuarios u"
												+ " WHERE c.id_profesor = u.id AND id_profesor = ?"
												+ " ORDER BY c.id ASC LIMIT 500; ";
	
	private final static String SQL_CURSOS_INSCRITO = "SELECT  c.id, c.nombre, c.identificador, c.horas, CONCAT(u.nombre, ' ', u.apellidos) AS 'profesor'"
												+ " FROM alumnos_curso ac, cursos c, usuarios u" 
												+ " WHERE ac.id_alumno = ? AND ac.id_curso = c.id AND c.id_profesor = u.id"
												+ " ORDER BY c.id ASC LIMIT 500; ";
	
	private final static String SQL_CURSOS_NO_INSCRITO = "SELECT c.id, c.nombre, c.identificador, c.horas, CONCAT(u.nombre, ' ', u.apellidos) AS 'profesor'"
												+ " FROM cursos c, usuarios u"
												+ " WHERE c.id_profesor = u.id AND c.id NOT IN (SELECT id_curso FROM alumnos_curso ac WHERE id_alumno = ?)"
												+ " ORDER BY c.id ASC LIMIT 500; ";
	
	private final static String SQL_CREAR = "INSERT INTO cursos (nombre, identificador, horas, id_profesor) VALUES (?, ?, ?, ?); ";
	private final static String SQL_BORRAR = "DELETE FROM cursos WHERE id = ?;";
	
	private final static String SQL_INSCRIBIRSE  = "INSERT INTO alumnos_curso (id_alumno, id_curso) VALUES (?, ?); ";

	
	
	@Override
	public ArrayList<Curso> listar() {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try( Connection con = ConnectionManager.getConnection() ;
			 PreparedStatement pst = con.prepareStatement(SQL_LISTAR);
			 ResultSet rs = pst.executeQuery()	
			){
			
			LOG.debug(pst);
			while ( rs.next() ) {			
				cursos.add(mapper(rs));			
			}
			
		}catch (Exception e) {
			LOG.error(e);
		}
		
		return cursos;
	}
	
	
	@Override
	public Curso buscarPorId(int id) throws Exception {
		
		Curso curso = new Curso();

		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_BUSCAR_POR_ID);
			){

			pst.setInt(1, id);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				if (rs.next()) {
					curso = mapper(rs);

				} else {
					throw new Exception ("No se pudo encontrar la ID: " + id);
				}
			}	
		}
		return curso;
	}
	
	
	@Override
	public Curso buscarPorId(int id, int idUsuario) throws Exception, SecurityException {
		
		Curso curso = new Curso();

		try (
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_BUSCAR_POR_ID_CON_USUARIO);
			){

			pst.setInt(1, id);
			pst.setInt(2, idUsuario);
			
			try ( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				if (rs.next()) {

					curso = mapper(rs);

				} else {

					throw new SecurityException();
				}
			} 	
		} 
		return curso;
	}
	
	
	@Override
	public ArrayList<Curso> listarPorProfesor(int id) {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try (
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_CURSOS_POR_PROFESOR);
			){
			
			pst.setInt(1, id);
			
			try( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while (rs.next()) {
					Curso c = new Curso();
					Usuario u = new Usuario();
					
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					c.setHoras(rs.getInt("horas"));
					c.setIdentificador(rs.getString("identificador"));
					
					u.setNombre(rs.getString("profesor"));
					c.setProfesor(u);
					
					cursos.add(c);
				}
			}	

		} catch (Exception e) {
			LOG.error(e);
		}

		return cursos;
	}
	
	
	@Override
	public ArrayList<Curso> listarCursosInscrito(int idAlumno) {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try (
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_CURSOS_INSCRITO);
			){
			
			pst.setInt(1, idAlumno);
			
			try( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while (rs.next()) {
					Curso c = new Curso();
					Usuario u = new Usuario();
					
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					c.setHoras(rs.getInt("horas"));
					c.setIdentificador(rs.getString("identificador"));
					
					u.setNombre(rs.getString("profesor"));
					c.setProfesor(u);
					
					cursos.add(c);
				}
			}	

		} catch (Exception e) {
			LOG.error(e);
		}

		return cursos;
	}
	
	
	@Override
	public ArrayList<Curso> listarCursosNoInscrito(int idAlumno) {
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try (
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_CURSOS_NO_INSCRITO);
			){
			
			pst.setInt(1, idAlumno);
			
			try( ResultSet rs = pst.executeQuery() ){
				
				LOG.debug(pst);
				while (rs.next()) {
					Curso c = new Curso();
					Usuario u = new Usuario();
					
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					c.setHoras(rs.getInt("horas"));
					c.setIdentificador(rs.getString("identificador"));
					
					u.setNombre(rs.getString("profesor"));
					c.setProfesor(u);
					
					cursos.add(c);
				}
			}	

		} catch (Exception e) {
			LOG.error(e);
		}

		return cursos;
	}
	
	
	@Override
	public Curso crear(Curso curso) throws Exception {
		
		if (curso.getNombre() == null) {

			throw new Exception("You must insert a name");
		}
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_CREAR, PreparedStatement.RETURN_GENERATED_KEYS);
			){
			
			pst.setString(1, curso.getNombre());
			pst.setString(2, curso.getIdentificador());
			pst.setInt(3, curso.getHoras());
			pst.setInt(4, curso.getProfesor().getId());	
		
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows == 1) {
				
				//conseguir el ID
				try ( ResultSet rsKeys = pst.getGeneratedKeys(); ) {
					
					if (rsKeys.next()) {

						curso.setId(rsKeys.getInt(1));
					}
				}
			} else {

				throw new Exception ("No se pudo crear una entrada para " + curso.getNombre());
			}
		} catch (SQLException e) {
			
			throw new SQLException("El nombre ya existe");
		}
		
		return curso;
	}
	
	
	@Override
	public Curso borrar(int id) throws Exception {
		
		Curso curso = new Curso();
		try (	
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement pst = connection.prepareStatement(SQL_BORRAR);
				){
				
				pst.setInt(1, id);
				
				curso = buscarPorId(id);

				int affectedRows = pst.executeUpdate();
				
				LOG.debug(pst);
				if (affectedRows != 1) {
					
					throw new Exception("No se pudo borrar el curso con ID: " + id);
				}
			}
			return curso;
	}
	
	
	@Override
	public void inscribir(int idAlumno, int idCurso) throws Exception {
		
		try ( 
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement pst = connection.prepareStatement(SQL_INSCRIBIRSE, PreparedStatement.RETURN_GENERATED_KEYS);
			){

			pst.setInt(1, idAlumno);
			pst.setInt(2, idCurso);	
			
			int affectedRows = pst.executeUpdate();
			
			LOG.debug(pst);
			if (affectedRows != 1) {
				
				throw new Exception("Ha habido un error");
			}
		
		} catch (Exception e) {
			
			throw new SQLException("Ha habido un error");
		}
	}
	
	
	private Curso mapper( ResultSet rs ) throws SQLException {
		
		Curso c = new Curso();
		Usuario u = new Usuario();
		
		c.setId(rs.getInt("curso_id"));
		c.setNombre(rs.getString("curso_nombre"));
		c.setHoras(rs.getInt("horas"));
		c.setIdentificador(rs.getString("identificador"));

		u.setId(rs.getInt("profesor_id"));
		u.setNombre(rs.getString("profesor_nombre"));
		u.setApellidos(rs.getString("profesor_apellidos"));
		u.setRol(rs.getInt("rol"));
		
		c.setProfesor(u);
	
		return c;
	}
}
