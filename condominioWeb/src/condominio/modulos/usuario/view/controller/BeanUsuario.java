package condominio.modulos.usuario.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import condominio.core.model.entities.Rol;
import condominio.core.model.entities.Usuario;
import condominio.modulos.usuario.model.ManagerUsuario;
import condominio.modulos.util.view.controller.JSFUtil;
import condominio.modulos.util.view.controller.Seguridad;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BeanUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private Usuario editarUsuario = new Usuario();
	private long idrolfk;
	private long idrolfkE;
	private Rol r = new Rol();
	private Rol editarRol = new Rol();
	private List<Rol> listaRoles;
	private List<Usuario> listaUsuarios;

	@EJB
	ManagerUsuario managerUsuario;

	@PostConstruct
	public void init() {
		try {
			listaRoles = managerUsuario.findAllRoles();
			listaUsuarios = managerUsuario.findAllUsuarios();

		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerIngresarRol() {
		try {
			managerUsuario.ingresarRol(r);
			listaRoles = managerUsuario.findAllRoles();
			r = new Rol();
			JSFUtil.crearMensajeInfo("Rol creado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			listaRoles = managerUsuario.findAllRoles();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public BeanUsuario() {

	}

	public void actionListenerCargarRol(Rol r) {
		editarRol = r;

	}

	public void actionListenerIngresarUsuario() {
		try {
			usuario.setContrasenia(Seguridad.encriptar(usuario.getCedula()));
			managerUsuario.ingresarUsuario(usuario, idrolfk);
			listaUsuarios = managerUsuario.findAllUsuarios();
			usuario = new Usuario();
			JSFUtil.crearMensajeInfo("Usuario creado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			listaUsuarios = managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerCargarUsuario(Usuario u) {
		editarUsuario = u;
		idrolfkE = u.getRol().getIdrol();
	}

	public void actionListenerEditarRol() {
		try {
			managerUsuario.editarRol(editarRol);
			listaRoles = managerUsuario.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol editado correctamente.!");
		} catch (Exception e) {
			e.printStackTrace();
			listaRoles = managerUsuario.findAllRoles();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarUsuario() {
		try {
			managerUsuario.editarUsuarios(editarUsuario, idrolfkE);
listaUsuarios=managerUsuario.findAllUsuarios();
JSFUtil.crearMensajeInfo("Usuario editado correctamente");
		} catch (Exception e) {
			listaUsuarios=managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarUsuario(long idUsuario) {
		try {
			managerUsuario.EliminarUsuario(idUsuario);
			listaUsuarios = managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeInfo("Usuario eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarRol(long idRol) {
		try {
			managerUsuario.EliminarRol(idRol);
			listaRoles = managerUsuario.findAllRoles();
			JSFUtil.crearMensajeInfo("Rol eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public String actionRedireccionarRoles() {
		return "roles.xhtml?faces-redirect=true";
	}

	public String actionRedireccionarUsuarios() {
		return "usuarios.xhtml?faces-redirect=true";
	}

	public String actionRedireccionarGastos() {
		return "gastos.xhtml?faces-redirect=true";
	}
	
	public String actionRedireccionarTipoGastos() {
		return "tipogastos.xhtml?faces-redirect=true";
	}
	
	public String actionRedireccionarTipoPagos() {
		return "tipopagos.xhtml?faces-redirect=true";
	}
	
	public String actionRedireccionarPagosTesorero() {
		return "pagostesorero.xhtml?faces-redirect=true";
	}

	public void actionRegistrarUsurio() {

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getIdrolfk() {
		return idrolfk;
	}

	public void setIdrolfk(long idrolfk) {
		this.idrolfk = idrolfk;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getEditarUsuario() {
		return editarUsuario;
	}

	public void setEditarUsuario(Usuario editarUsuario) {
		this.editarUsuario = editarUsuario;
	}

	public Rol getEditarRol() {
		return editarRol;
	}

	public void setEditarRol(Rol editarRol) {
		this.editarRol = editarRol;
	}

	public Rol getR() {
		return r;
	}

	public void setR(Rol r) {
		this.r = r;
	}

	public long getIdrolfkE() {
		return idrolfkE;
	}

	public void setIdrolfkE(long idrolfkE) {
		this.idrolfkE = idrolfkE;
	}

}
