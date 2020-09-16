package condominio.modulos.condominio.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import condominio.core.model.entities.Gasto;
import condominio.core.model.entities.Usuario;
import condominio.modulos.condominio.model.ManagerCondominio;
import condominio.modulos.tesorero.model.ManagerTesorero;
import condominio.modulos.util.view.controller.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BeanCondominio implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Usuario> listaCondominio;
	@EJB
	ManagerCondominio managerCondominio;
	@PostConstruct
	public void init() {
		try {
			listaCondominio = managerCondominio.findAllCondominos();
		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}
	public BeanCondominio() {
		// TODO Auto-generated constructor stub
	}
	public List<Usuario> getListaCondominio() {
		return listaCondominio;
	}
	public void setListaCondominio(List<Usuario> listaCondominio) {
		this.listaCondominio = listaCondominio;
	}

	
}
