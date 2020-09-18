package academia.modelo.dao;

import java.util.ArrayList;

import academia.modelo.pojo.Curso;

public interface CursoDAO {
	
	ArrayList<Curso> listar();
	Curso buscarPorId(int id) throws Exception;
	Curso buscarPorId(int id, int idUsuario) throws Exception, SecurityException;
	ArrayList<Curso> listarPorProfesor(int id);
	ArrayList<Curso> listarCursosInscrito(int idAlumno);
	ArrayList<Curso> listarCursosNoInscrito(int idAlumno);
	
	Curso crear(Curso curso) throws Exception;
	Curso borrar(int id) throws Exception;
	void inscribir(int idAlumno, int idCurso) throws Exception;	
}
