package condominio.modulos.login.view.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import condominio.core.model.entities.Usuario;
import condominio.core.model.util.ModelUtil;
import condominio.modulos.login.model.LoginDto;
import condominio.modulos.login.model.ManagerLogin;
import condominio.modulos.usuario.model.ManagerUsuario;
import condominio.modulos.util.view.controller.JSFUtil;
import condominio.modulos.util.view.controller.Seguridad;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private String correo;
	private String contrasenia;
	private LoginDto login;
	private Usuario ingreUsuario = new Usuario();
	private String verificarContrasenia;
	private boolean activoLogin;
	private boolean isInseguro;
	private String contraseniaActual;
	private String nuevaContrasenia;
	private String confirmarContrasenia;	
	@EJB
	ManagerLogin managerLogin;
	@EJB
	ManagerUsuario managerUsuario;

	public BeanLogin() {

	}
	
@SuppressWarnings("unused")
public  String actionRestablecerContrasenia() {
	try {
		managerLogin.restablecerContraseniaSegura(login, contraseniaActual,Seguridad.encriptar(nuevaContrasenia),Seguridad.encriptar(confirmarContrasenia));
		contraseniaActual="";
		nuevaContrasenia="";
		confirmarContrasenia="";
		isInseguro=false;
		
		JSFUtil.crearMensajeInfo("Contraseña actualizada correctamente");
		return "/tesorero/menu?faces-redirect=true";
	} catch (Exception e) {
JSFUtil.crearMensajeError(e.getMessage());
	}finally {
		JSFUtil.crearMensajeFastFinal();
	}
	return "";
}
	public String actionCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
	}

	public String registrarseUsuario() {

		try {
			boolean verificarC = managerUsuario.verificarContrasenias(ingreUsuario.getContrasenia(),
					verificarContrasenia);
			if (verificarC) {
				ingreUsuario.setContrasenia(Seguridad.encriptar(ingreUsuario.getContrasenia()));
				login = managerUsuario.registrarUsuario(ingreUsuario);
				ingreUsuario = new Usuario();
			}
			JSFUtil.crearMensajeInfo("Bienvenido");
			return "condominio/menu?faces-redirect=true";
		} catch (Exception e) {

			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());

		} finally {
			JSFUtil.crearMensajeFastFinal();
		}
		return "";
	}

	public String actionLogin() {
		try {
			contrasenia = Seguridad.encriptar(contrasenia);
			System.out.println("Contrasenia que ingresa " + contrasenia);
			login = managerLogin.comprobarCredenciales(correo, contrasenia);
			if (login != null) {
				login.setInseguro(managerLogin.verificarTesoreroisNuevo(Seguridad.encriptar(login.getCedula()), contrasenia));
			}
			correo = "";
			contrasenia = "";

			if (!login.isActivo()) {
				JSFUtil.crearMensajeError("Cuenta inactiva contáctese con el administrador");
				return "";
			}
			if (login.getNombreRol().equals("Tesorero")) {
				System.out.println(login.isInseguro());
				if (login.isInseguro()) {
					isInseguro=true;
					return "tesorero/seguridad?faces-redirect=true";
				}

				return "tesorero/menu?faces-redirect=true";
			} else {
				if (login.getNombreRol().equals("Condominio")) {
					/*
					 * if (login.isIsnuevo()==true) { return
					 * "investigador/seguridadInvestigador?faces-redirect=true";
					 * 
					 * }
					 */
					return "condominio/menu?faces-redirect=true";
				} else {
					System.out.println("Rol; " + login.getNombreRol());
					/*
					 * if (login.isIsnuevo()==true) {
					 * System.out.println("Ronuevol; "+login.getRol()); return
					 * "usuario/seguridadUsuario?faces-redirect=true"; }
					 */
					System.out.println("Rno; " + login.getNombreRol());
					return "administrador/menu?faces-redirect=true";
				}
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
	}

	public void actionComprobarSessionLogin() {

		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			String path = ec.getRequestPathInfo();
			if (login == null) {

				if (path.equals("/login.xhtml") || path.equals("/registrarse.xhtml")) {
					return;
				} else
					ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				if (path.equals("/login.xhtml") || path.equals("/registrarse.xhtml")) {
					if (login.getNombreRol().equals("Condominio")) {
						ec.redirect(ec.getRequestContextPath() + "/faces/condominio/menu.xhtml");

					} else {
						ec.redirect(ec.getRequestContextPath() + "/faces/tesorero/menu.xhtml");
					}
				}
				// si hizo login, verificamos que acceda a paginas permitidas:

				// si hizo login, verificamos que acceda a paginas permitidas:
				if (login.getNombreRol().equals("Condominio")) {
					if (!path.contains("/condominio/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/condominio/menu.xhtml");
					else
						return;
				} else {
					if (!path.contains("/tesorero/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/tesorero/menu.xhtml");
					else {
						return;
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError("" + e.getMessage());
		}
		return;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public LoginDto getLogin() {
		return login;
	}

	public void setLogin(LoginDto login) {
		this.login = login;
	}

	public Usuario getIngreUsuario() {
		return ingreUsuario;
	}

	public void setIngreUsuario(Usuario ingreUsuario) {
		this.ingreUsuario = ingreUsuario;
	}

	public String getVerificarContraseniaa() {
		return verificarContrasenia;
	}

	public void setVerificarContrasenia(String verificarContrasenia) {
		this.verificarContrasenia = verificarContrasenia;
	}

	public boolean isActivoLogin() {
		return activoLogin;
	}

	public void setActivoLogin(boolean activoLogin) {
		this.activoLogin = activoLogin;
	}

	public String getVerificarContrasenia() {
		return verificarContrasenia;
	}

	public boolean isInseguro() {
		return isInseguro;
	}

	public void setInseguro(boolean isInseguro) {
		this.isInseguro = isInseguro;
	}

	public String getContraseniaActual() {
		return contraseniaActual;
	}

	public void setContraseniaActual(String contraseniaActual) {
		this.contraseniaActual = contraseniaActual;
	}

	public String getNuevaContrasenia() {
		return nuevaContrasenia;
	}

	public void setNuevaContrasenia(String nuevaContrasenia) {
		this.nuevaContrasenia = nuevaContrasenia;
	}

	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}

	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
	

}
