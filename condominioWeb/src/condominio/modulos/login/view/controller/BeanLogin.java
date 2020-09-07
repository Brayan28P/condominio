package condominio.modulos.login.view.controller;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	public BeanLogin() {
		
	}
	public String  actionCerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();		
		return "/login?faces-redirect=true";
	}
	
}
