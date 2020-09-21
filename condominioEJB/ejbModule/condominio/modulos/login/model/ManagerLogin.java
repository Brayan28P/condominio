package condominio.modulos.login.model;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Usuario;
import condominio.core.model.util.ModelUtil;
import condominio.modulos.usuario.model.ManagerUsuario;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {
	@PersistenceContext
	private EntityManager em;
@EJB
private ManagerUsuario managerUsuario;
	public ManagerLogin() {
		 
	}

	public LoginDto comprobarCredenciales(String correo, String contrasenia) throws Exception {
		LoginDto login = new LoginDto();
		List<Usuario> usuarios = existeUsuario(correo, contrasenia);
		Usuario usuario;
		if (usuarios.isEmpty()) {
			throw new Exception("Credenciales incorrectas intente otra vez");
		} else {
			usuario = usuarios.get(0);
			login.setIdUsuario(usuario.getIdusuario());
			login.setNombres(usuario.getApellidos() + " " + usuario.getNombres());
			login.setNombreRol(usuario.getRol().getNombre());
			login.setActivo(true);
			login.setCedula(usuario.getCedula());
		}
		return login;

	}

	public boolean verificarTesoreroisNuevo(String contrasenia, String cedula) throws Exception {
		if (ModelUtil.isEmpty(contrasenia) || ModelUtil.isEmpty(cedula)) {
			return false;
		} else {
			if (contrasenia.equals(cedula)) {
				return true;
			} else {
				return false;
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> existeUsuario(String correo, String contrasenia) {
		String JPQL = "SELECT u FROM Usuario u WHERE u.email=?1 and u.contrasenia=?2";
		Query query = em.createQuery(JPQL, Usuario.class);
		query.setParameter(1, correo);
		query.setParameter(2, contrasenia);
		List<Usuario> lista;
		lista = query.getResultList();
		return lista;
	}

	public void restablecerContraseniaSegura(LoginDto login, String contraseniaActual, String contraseniaNueva,
			String confirmarContrasenia) throws Exception {
		if (ModelUtil.isEmptyNumber(login.getIdUsuario())) {
			throw new Exception("Error al cargar el id del usuario en la sesión");
		}
		if (ModelUtil.isEmpty(contraseniaActual)) {
			throw new Exception("Ingrese la contraseña actual");
		}
		if (ModelUtil.isEmpty(contraseniaNueva)) {
			throw new Exception("Ingrese la contraseña nueva");
		}
		if (ModelUtil.isEmpty(confirmarContrasenia)) {
			throw new Exception("Ingrese la confirmación de la contraseña");
		}
		if (ModelUtil.isEmpty(login.getCedula())) {
			throw new Exception("La cédula del login no ha sido cargada correctamente");
		}
		if (!contraseniaActual.equals(login.getCedula())) {
			throw new Exception("La contraseña Actual no es válida");
		}
		if (!contraseniaNueva.equals(confirmarContrasenia)) {
			throw new Exception("Las nuevas contraseñas no coinciden");
		}
		Usuario user=managerUsuario.findUsuarioById(login.getIdUsuario());
		user.setContrasenia(contraseniaNueva);
		em.merge(user);
		
	}
}
