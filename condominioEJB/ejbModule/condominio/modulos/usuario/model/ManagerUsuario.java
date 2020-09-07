package condominio.modulos.usuario.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Rol;
import condominio.core.model.entities.Usuario;
import condominio.core.model.util.ModelUtil;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {
	@PersistenceContext
	private EntityManager em;

    public ManagerUsuario() {


    }
    
    /*
     M�todo para que se registren los usuarios manualmente
     */
    public void registrarUsuario(Usuario u, long idRolfk) throws Exception {
    	if (u==null) {
    		throw new Exception("El objeto usuario no ha sido cargado correctamente.!");
		}else {
			if (ModelUtil.isEmptyNumber(idRolfk)) {
				throw new Exception("La clave for�nea de roles no ha sido correctamente cargada.!");	
			}else {
				Rol r=new Rol();
				Usuario user=new Usuario();
				user=u;
				u.setRol(r);	
				em.persist(user);
			}
			
		}
    }
    /*
  M�todo para registrar los roles
    */
    public void ingresarRol(Rol r) throws Exception {
      	if (r==null) {
    		throw new Exception("El objeto rol no ha sido cargado correctamente.!");
		}else {
			em.persist(r);
		}
    }
    /*
    M�todo para encontrar un rol por el ID  o PK 
    */
    public Rol findRolnById(int idRol) {
    	Rol rol= em.find(Rol.class, idRol);
		return rol;
	}
    /*
    M�todo para encontrar un rol por el ID  o PK 
    */
    public Usuario findUsuarioById(int idUsuario) {
    	Usuario u= em.find(Usuario.class, idUsuario);
		return u;
	}
    /*
    M�todo para encontrar la lista de roles
    */
	@SuppressWarnings("unchecked")
	public List<Rol> findAllRoles() {
		Query q = em.createQuery("SELECT r FROM Rol r order by r.idrol asc", Rol.class);
		List<Rol> listaRoles = q.getResultList();
		return listaRoles;
	}
    /*
    M�todo para encontrar la lista de usuarios
    */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios() {
		Query q = em.createQuery("SELECT u FROM Usuario u order by u.idUsuario asc", Usuario.class);
		List<Usuario> listaUsuarios= q.getResultList();
		return listaUsuarios;
	}
    
    
}
