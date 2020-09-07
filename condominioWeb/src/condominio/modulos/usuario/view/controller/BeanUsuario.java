package condominio.modulos.usuario.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import condominio.core.model.entities.Rol;
import condominio.core.model.entities.Usuario;
import condominio.modulos.usuario.model.ManagerUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BeanUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
private Usuario usuario=new Usuario();
private long idrolfk;
private Rol rol=new Rol();
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
	}

}
public BeanUsuario() {
	 
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


public Rol getRol() {
	return rol;
}


public void setRol(Rol rol) {
	this.rol = rol;
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
	
 
}
