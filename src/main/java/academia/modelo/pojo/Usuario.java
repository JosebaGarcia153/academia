package academia.modelo.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Usuario {
	
	public static final int ROL_ALUMNO = 1;
	public static final int ROL_PROFESOR = 2;
	
	private int id;
	
	@NotNull(message = "Nombre no puede estar vacio")
	@Size(min = 3, max = 100, message = "Nombre tiene que tener entre 3 and 100 letras")
	private String nombre;
	
	@NotNull(message = "Apellidos no pueden estar vacio")
	@Size(min = 3, max = 100, message = "Apellidos tienen que tener entre 3 and 100 letras")
	private String apellidos;
	
	private int rol;
	
	@NotNull(message = "Password no puede ser null")
	@Size(min = 3, max = 10, message = "Password tiene que tener entre 6 and 10 letras")
	private String password;
	
	private int numeroAlumnos;
	
	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "";
		this.apellidos = "";
		this.rol = 0;
		this.password = "";
		this.numeroAlumnos = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getNumeroAlumnos() {
		return numeroAlumnos;
	}

	public void setNumeroAlumnos(int numeroAlumnos) {
		this.numeroAlumnos = numeroAlumnos;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", rol=" + rol + ", password="
				+ password + ", numeroAlumnos=" + numeroAlumnos + "]";
	}

	
}
