package condominio.modulos.login.view.controller;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import condominio.core.model.entities.Usuario;
import condominio.modulos.login.model.LoginDto;
import condominio.modulos.login.model.ManagerLogin;
import condominio.modulos.usuario.model.ManagerUsuario;
import condominio.modulos.util.view.controller.JSFUtil;
import condominio.modulos.util.view.controller.Seguridad;

import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;
private String correo;
private String contrasenia;
private LoginDto login;
private Usuario ingreUsuario=new Usuario();

@EJB ManagerLogin managerLogin;
@EJB ManagerUsuario managerUsuario;
	public BeanLogin() {
		
	}
	public String  actionCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		
		return "/login?faces-redirect=true";
	}

	public String registrarseUsuario() {
		
		try {
			ingreUsuario.setContrasenia(Seguridad.encriptar(ingreUsuario.getContrasenia()));
			login=managerUsuario.registrarUsuario(ingreUsuario);
			ingreUsuario=new Usuario();
			JSFUtil.crearMensajeInfo("Bienvenido");
			return "condominio/menu?faces-redirect=true";
		} catch (Exception e) {
			
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());

		}finally {
			JSFUtil.crearMensajeFastFinal();
		}
return"";
	}
	public String actionLogin() {
		try {
			contrasenia=Seguridad.encriptar(contrasenia);
			System.out.println("Contrasenia que ingresa "+contrasenia);
			login = managerLogin.comprobarCredenciales(correo, contrasenia);
			correo="";
			contrasenia="";
			if (!login.isActivo()) {
				JSFUtil.crearMensajeError("Cuenta inactiva contáctese con el administrador");
				return"";
			}
			System.out.println("Rol; "+login.getNombreRol());
			if (login.getNombreRol().equals("Tesorero")) {
			/*
				if (login.isIsnuevo()) {
					return "administrador/seguridadAdministrador?faces-redirect=true";
				}
				*/
				 
				return "tesorero/menu?faces-redirect=true";
			} else {
				if (login.getNombreRol().equals("Condominio")) {
					/*
					if (login.isIsnuevo()==true) {
						return "investigador/seguridadInvestigador?faces-redirect=true";
					
					}*/ 
					return "condominio/menu?faces-redirect=true";
				} else {
					System.out.println("Rol; "+login.getNombreRol());
					/*
					if (login.isIsnuevo()==true) {
						System.out.println("Ronuevol; "+login.getRol());
						return "usuario/seguridadUsuario?faces-redirect=true";
					}
					*/ 
					System.out.println("Rno; "+login.getNombreRol());
					return "administrador/menu?faces-redirect=true";
				}
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			return "";
		}
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
	
}
