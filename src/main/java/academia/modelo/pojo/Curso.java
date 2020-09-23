package academia.modelo.pojo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Curso {
	
	private int id;
	
	@NotNull(message = "Nombre no puede estar vacio")
	private String nombre;
	
	@NotNull(message = "Identificador no puede estar vacio")
	@Size(min = 4, max = 4, message = "El identificador tiene que ser de 4 caracteres")
	private String identificador;
	
	@NotNull(message = "Horas no pueden estar vacio")
	@Min(value = 50, message = "Horas tiene que ser 50 o mas")
	private int horas;
	
	private Usuario profesor;

	
	public Curso() {
		super();
		this.id = 0;
		this.nombre = "";
		this.identificador = "";
		this.horas = 0;
		this.profesor = new Usuario();
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



	public String getIdentificador() {
		return identificador;
	}



	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}



	public int getHoras() {
		return horas;
	}



	public void setHoras(int horas) {
		this.horas = horas;
	}



	public Usuario getProfesor() {
		return profesor;
	}



	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}



	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", identificador=" + identificador + ", horas=" + horas
				+ ", profesor=" + profesor + "]";
	}
	
	
	
	
	
	
	
}
