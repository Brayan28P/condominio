package condominio.modulos.login.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {
	@PersistenceContext
	private EntityManager em;
    public ManagerLogin() {
        // TODO Auto-generated constructor stub
    }

    
    public LoginDto comprobarCredenciales(String correo,String contrasenia) throws Exception {
    	LoginDto login=new LoginDto();
    	List<Usuario>usuarios=existeUsuario( correo, contrasenia);
    Usuario usuario;
    	if (usuarios.isEmpty()) {
    		throw new Exception("Credenciales incorrectas intente otra vez");
    	}else {
    		usuario=usuarios.get(0);
    	login.setIdUsuario(usuario.getIdusuario());
    	login.setNombres(usuario.getApellidos()+" "+usuario.getNombres());
    	login.setNombreRol(usuario.getRol().getNombre());
    	login.setActivo(true);
    	}
    	return login;
    	
    }
	@SuppressWarnings("unchecked")
	public List<Usuario> existeUsuario(String correo,String contrasenia){
		String JPQL = "SELECT u FROM Usuario u WHERE u.email=?1 and u.contrasenia=?2";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, correo);
		query.setParameter(2, contrasenia);
		List<Usuario> lista;
		lista = query.getResultList();
		return lista;	
	}
}
