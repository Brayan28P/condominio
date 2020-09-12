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
import condominio.modulos.login.model.LoginDto;

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
     Metodo para que se registren los usuarios manualmente
     */
    public LoginDto registrarUsuario(Usuario u) throws Exception {
    	if (ModelUtil.isEmpty(u.getNombres())) {
    		throw new Exception("No ha ingresado los nombres");
		}
    	if (ModelUtil.isEmpty(u.getApellidos())) {
    		throw new Exception("No ha ingresado los apellidos");
		}
    	if (ModelUtil.isEmpty(u.getEmail())) {
    		throw new Exception("No ha ingresado el email");
		}
    	if (ModelUtil.isEmpty(u.getContrasenia())) {
    		throw new Exception("No ha ingresado la contraseña");
		}
    	if (ModelUtil.isEmpty(u.getCedula())) {
    		throw new Exception("No ha ingresado la cédula");
		}
    	
    	Rol r=new Rol();
    	r=findRoByNombre("Tesorero");
				Usuario user=new Usuario();
				user=u;
				u.setRol(r);	
				em.persist(user);
				LoginDto login=new LoginDto();
				login.setNombreRol(r.getNombre());
				login.setIdUsuario(user.getIdusuario());
				login.setNombres(user.getNombres()+" "+user.getApellidos());
				login.setCedula(user.getCedula());
			    login.setActivo(true);
		 return login;
    }
    /*
  Metodo para registrar los roles
    */
    public void ingresarRol(Rol r) throws Exception {
      	if (r!=null) {
      		Rol rol=new Rol();
			rol=r;
			em.persist(rol);
    	
		}else {
			throw new Exception("El objeto rol no ha sido cargado correctamente.!");
		}
    }
    /*
  Metodo para registrar los roles
    */
    public void ingresarUsuario(Usuario u, long id_rol_fk) throws Exception {
      	if (u==null) {

      		throw new Exception("El objeto rol no ha sido cargado correctamente.!");
		}else {
			if (ModelUtil.isEmptyNumber(id_rol_fk)) {
		   		throw new Exception("El objeto rol no ha sido cargado correctamente.!");
			}else {
				Rol r=findRolnById(id_rol_fk);
      		Usuario usuario=new Usuario();
			usuario=u;
			usuario.setRol(r);
 
			em.persist(usuario);
			}
		}
    }
    /*
    Metodo para encontrar un rol por el ID  o PK 
    */
    public Rol findRolnById(long idrol) {
    	Rol rol= em.find(Rol.class, idrol);
		return rol;
	}
    /*
    Metodo para encontrar un rol por el ID  o PK 
    */
    public Usuario findUsuarioById(int idUsuario) {
    	Usuario u= em.find(Usuario.class, idUsuario);
		return u;
	}
    /*
    Metodo para encontrar la lista de roles
    */
	@SuppressWarnings("unchecked")
	public List<Rol> findAllRoles() {
		Query q = em.createQuery("SELECT r FROM Rol r order by r.idrol asc", Rol.class);
		List<Rol> listaRoles = q.getResultList();
		return listaRoles;
	}
    /*
    Metodo para encontrar la lista de roles
    */
	@SuppressWarnings("unchecked")
	public  Rol findRoByNombre(String nombreRol) {
		Query q = em.createQuery("SELECT r FROM Rol r "
				+ " where r.nombre=?1", Rol.class);
		q.setParameter(1, nombreRol);
		Rol r=new Rol();
		List<Rol> listaRoles = q.getResultList();
	if (listaRoles.isEmpty()) {
		return r;
	}else {
		r=listaRoles.get(0);
		return r;
	}
		 
	}
    /*
    Metodo para encontrar la lista de usuarios
    */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios() {
		Query q = em.createQuery("SELECT u FROM Usuario u ", Usuario.class);
		List<Usuario> listaUsuarios= q.getResultList();
		return listaUsuarios;
	}
    /*
    Metodo para editar Roles
    */
	 
	public void  editarRol(Rol r) throws Exception {
if (r==null) {
	throw new Exception("El objeto rol no ha sido cargado correctamente.!");
}else {
	em.merge(r);
}

	}
    
    
}
