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
private Usuario usuario=new Usuario();
private Usuario editarUsuario=new Usuario();
private long idrolfk;
private Rol r=new Rol();
private Rol editarRol=new Rol();
private  List<Rol> listaRoles;
private  List<Usuario> listaUsuarios;

@EJB ManagerUsuario managerUsuario;

@PostConstruct
public void init() {
	try {
	listaRoles=managerUsuario.findAllRoles();
	listaUsuarios=managerUsuario.findAllUsuarios();
	
	} catch (Exception e) {
		e.getStackTrace();
		JSFUtil.crearMensajeError(e.getMessage());
	}

}
public void actionListenerIngresarRol() {
	try {
		managerUsuario.ingresarRol(r);
		listaRoles=managerUsuario.findAllRoles();
		r=new Rol();
		JSFUtil.crearMensajeInfo("Rol creado correctamente");
	} catch (Exception e) {
		e.printStackTrace();
		JSFUtil.crearMensajeError(e.getMessage());
	}
}
public BeanUsuario() {
	 
}
public void actionListenerCargarRol(Rol r) {
	System.out.println("--"+r.getIdrol());
	editarRol=r;
}
public void actionListenerIngresarUsuario() {
	try {
		String contrasenia="123";
		contrasenia=Seguridad.encriptar(contrasenia);
		usuario.setContrasenia(contrasenia);
		managerUsuario.ingresarUsuario(usuario, idrolfk);
		listaUsuarios=managerUsuario.findAllUsuarios();
		usuario=new Usuario();
		JSFUtil.crearMensajeInfo("Usuario creado correctamente");
	} catch (Exception e) {
		e.printStackTrace();
		JSFUtil.crearMensajeError(e.getMessage());
	}
}
public void actionListenerCargarUsuario(Usuario u) {
	editarUsuario=u;
}
public void actionListenerEditarRol() {
	try {
		managerUsuario.editarRol(editarRol);
		listaRoles=managerUsuario.findAllRoles();
		JSFUtil.crearMensajeInfo("Rol editado correctamente.!");
	} catch (Exception e) { 
		e.printStackTrace();
		JSFUtil.crearMensajeError(e.getMessage());
	}
}
public void actionListenerEditarUsuario() {
	
}
public String actionRedireccionarRoles() {
	return"roles.xhtml?faces-redirect=true";
}
public String actionRedireccionarUsuarios() {
	return"usuarios.xhtml?faces-redirect=true";
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
	
 
}
