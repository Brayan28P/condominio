package condominio.modulos.usuario.model;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Gasto;
import condominio.core.model.entities.PagoCondominio;
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
    public boolean verificarContrasenias(String contrasenia1,String contrasenia2) throws Exception {
    	if (ModelUtil.isEmpty(contrasenia1)) {
    		throw new Exception("No ha ingresado la contraseña 1");
		}
    	if (ModelUtil.isEmpty(contrasenia2)) {
    		throw new Exception("No ha ingresado la contraseña 2");
		}
    	if (!contrasenia1.equals(contrasenia2)) {
    		throw new Exception("La contraseñas no coinciden");
		}else return true;
    	
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
    	if (!ModelUtil.validarCedula(u.getCedula())) {
    		throw new Exception("La cédula es incorrecta ingrese una válida porfavor");
		}
    	
    	Rol r=new Rol();
    	r=findRoByNombre("Condominio");
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
      	Rol nrol=findRoByNombre(r.getNombre());
      	if (nrol!=null) {
      		System.out.println(".. "+nrol.getIdrol());
      		throw new Exception("El rol "+r.getNombre()+" ya existe.");
		}else {
      		Rol rol=new Rol();
			rol=r;
			em.persist(rol);
		}
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
    public Usuario findUsuarioById(long idUsuario) {
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
		return null;
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
	Rol rol= new Rol();
	rol=findRoByNombre(r.getNombre());
	if (rol==null) {
		em.merge(r);
	}else {
		if (r.getIdrol()!=rol.getIdrol()) {
			throw new Exception("Ya existe un nombre con ese rol.!");
		}
	}
  
}

	}
    /*
    Metodo para editar Usuarios
    */
	 
	public void  editarUsuarios(Usuario r , long idRolFk) throws Exception {
if (r==null) {
	throw new Exception("El objeto usuario no ha sido cargado correctamente.!");
}else {
	Usuario user= new Usuario();
	user=findUsuarioById(r.getIdusuario());
	if (user==null) {
		em.merge(r);
	}else {
		if (user.getIdusuario()!=user.getIdusuario()) {
			throw new Exception("Ya existe un nombre con ese rol.!");
		}
	}
  
}

	}
	
	public void EliminarRol(long idRol) throws Exception {
		if (idRol==0) {
			throw new Exception("El rol no existe vuelva a intentarlo");
		}else {
			Rol r=new Rol();
			r=findRolnById(idRol);
			 boolean existeRolenUser=existeRolenUsuario(idRol);
			 if (existeRolenUser) {
				 throw new Exception("El rol no puede ser eliminado se encuentra utilizado en la entidad usuario");	
			}else
				em.remove(r);
		}
	}
	public void EliminarUsuario(long idUsuario) throws Exception {
		if (idUsuario==0) {
			throw new Exception("El usuario no existe vuelva a intentarlo");
		}else {
			Usuario user=new Usuario();
			user=findUsuarioById(idUsuario);
			 boolean existeUserenPago=existeUsuarioenPagoCondominio(idUsuario);
			 if (existeUserenPago) {
				 throw new Exception("El usuario no puede ser eliminado se encuentra utilizado en la entidad Pago Condominio");	
			}else {
				boolean existeUsuarioenGato=existeUsuarioenGasto(idUsuario);
				if (existeUsuarioenGato) {
					 throw new Exception("El usuario no puede ser eliminado se encuentra utilizado en la entidad Gasto");	
						}else
				em.remove(user);
			}
				
		}
	}
	
	 @SuppressWarnings("unchecked")
		public boolean existeUsuarioenGasto(long idUsuario) {
	      	Query q = em.createQuery("SELECT g FROM Gasto g "
	    			+ "where g.usuario.idusuario=?1", Gasto.class);
	      	q.setParameter(1, idUsuario);
	      	List<Gasto>listaGastos=new ArrayList<Gasto>();
			listaGastos= q.getResultList(); 
			if (listaGastos.isEmpty()) {
				return false;
			}else
			return true;
	    }
	 @SuppressWarnings("unchecked")
		public boolean existeUsuarioenPagoCondominio(long idUsuario) {
	      	Query q = em.createQuery("SELECT p FROM PagoCondominio p "
	    			+ "where p.usuario.idusuario=?1", PagoCondominio.class);
	      	q.setParameter(1, idUsuario);
	      	List<PagoCondominio>listaPagos=new ArrayList<PagoCondominio>();
			listaPagos= q.getResultList(); 
			if (listaPagos.isEmpty()) {
				return false;
			}else
			return true;
	    }
    @SuppressWarnings("unchecked")
	public boolean existeRolenUsuario(long idRol) {
      	Query q = em.createQuery("SELECT u FROM Usuario u "
    			+ "where u.rol.idrol=?1", Usuario.class);
      	q.setParameter(1, idRol);
      	List<Usuario>listaUsuarios=new ArrayList<Usuario>();
		listaUsuarios= q.getResultList(); 
		if (listaUsuarios.isEmpty()) {
			return false;
		}else
		return true;
    }
    
}
