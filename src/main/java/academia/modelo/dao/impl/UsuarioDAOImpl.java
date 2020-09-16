package academia.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import academia.modelo.ConnectionManager;
import academia.modelo.dao.UsuarioDAO;
import academia.modelo.pojo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static final Logger LOG = Logger.getLogger(UsuarioDAOImpl.class);
	private static UsuarioDAOImpl instance = null;
	
	private UsuarioDAOImpl() {
		super();	
	}
	
	public static synchronized UsuarioDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new UsuarioDAOImpl();
		}	
		return instance;
	}

	private final String SQL_EXISTE = "SELECT id, nombre, password, rol FROM usuarios WHERE nombre = ? AND password = MD5(?) LIMIT 500; ";
	

	@Override
	public Usuario buscar (String nombre, String password) {
		
		Usuario usuario = null;
		
		try(	
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_EXISTE);	
			) {
			
			pst.setString(1, nombre);
			pst.setString(2, password);
			
			LOG.debug(pst);
			try (ResultSet rs = pst.executeQuery()) {
				
				if (rs.next()) {
					
					usuario = mapper(rs);
				}
			} //2nd try
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return usuario;
	}
	
	
	private Usuario mapper( ResultSet rs ) throws SQLException {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(rs.getInt("id"));
		usuario.setNombre(rs.getString("nombre"));
		usuario.setPassword(rs.getString("password"));
		usuario.setRol(rs.getInt("rol"));
		
		return usuario;	
	}
}