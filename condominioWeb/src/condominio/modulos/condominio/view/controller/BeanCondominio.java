package condominio.modulos.condominio.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import condominio.core.model.entities.Gasto;
import condominio.core.model.entities.PagoCondominio;
import condominio.core.model.entities.TipoPago;
import condominio.core.model.entities.Usuario;
import condominio.modulos.condominio.model.ManagerCondominio;
import condominio.modulos.login.view.controller.BeanLogin;
import condominio.modulos.tesorero.model.ManagerTesorero;
import condominio.modulos.usuario.model.ManagerUsuario;
import condominio.modulos.util.view.controller.JSFUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class BeanCondominio implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Usuario> listaCondominio;
	private List<PagoCondominio> listaPagoCondominos;
	private PagoCondominio pagoCondominio = new PagoCondominio();
	private PagoCondominio editarPagoCondominio = new PagoCondominio();
	private long idtPagofk;
	private long idtPagofkE;
	@EJB
	ManagerCondominio managerCondominio;
	@EJB
	ManagerTesorero managerTesorero;
	@EJB
	ManagerUsuario managerUsuario;
	@Inject
	private BeanLogin beanLogin;

	@PostConstruct
	public void init() {
		try {
			listaCondominio = managerCondominio.findAllCondominos();
			listaPagoCondominos = managerCondominio.findAllPagoCondominos(beanLogin.getLogin().getIdUsuario());
		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public BeanCondominio() {
		// TODO Auto-generated constructor stub
	}
	
	public void actionListenerCargarPagoCondomino(PagoCondominio g) {
		editarPagoCondominio = g;
		idtPagofkE=g.getTipoPago().getIdpago();
	}

	public void actionListenerIngresarPagoCondomino() {
		try {
			TipoPago tipoPago = managerTesorero.findTipoPagoById(idtPagofk);
			System.out.print(tipoPago.getNombre());
			Date fechatransaccion = new Date();
			Usuario usuario = managerUsuario.findUsuarioById(beanLogin.getLogin().getIdUsuario());
			pagoCondominio.setTipoPago(tipoPago);
			pagoCondominio.setFechatransaccion(fechatransaccion);
			pagoCondominio.setUsuario(usuario);
			pagoCondominio.setValido(false);
			managerCondominio.ingresarPagoCondomino(pagoCondominio);
			listaPagoCondominos = managerCondominio.findAllPagoCondominos(beanLogin.getLogin().getIdUsuario());
			pagoCondominio = new PagoCondominio();
			JSFUtil.crearMensajeInfo("Pago condomino creado correctamente, el tesorero debe validar.");
		} catch (Exception e) {
			e.printStackTrace();
			listaPagoCondominos = managerCondominio.findAllPagoCondominos(beanLogin.getLogin().getIdUsuario());
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarPagoCondominio() {
		try {
			TipoPago tipoPagoCondomino = managerTesorero.findTipoPagoById(idtPagofkE);
			Date fechatransaccion = new Date();
			Usuario usuario = managerUsuario.findUsuarioById(beanLogin.getLogin().getIdUsuario());
			editarPagoCondominio.setTipoPago(tipoPagoCondomino);
			editarPagoCondominio.setFechatransaccion(fechatransaccion);
			editarPagoCondominio.setUsuario(usuario);
			editarPagoCondominio.setValido(false);
			managerCondominio.editarPagoCondomino(editarPagoCondominio);
			listaPagoCondominos = managerCondominio.findAllPagoCondominos(beanLogin.getLogin().getIdUsuario());
			JSFUtil.crearMensajeInfo("Pago condomino editado correctamente, el tesorero debe validar.");
		} catch (Exception e) {
			e.printStackTrace();
			listaPagoCondominos = managerCondominio.findAllPagoCondominos(beanLogin.getLogin().getIdUsuario());
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarPagoCondomino(long idPagoCondomino) {
		try {
			managerCondominio.eliminarPagoCondomino(idPagoCondomino);
			listaPagoCondominos = managerCondominio.findAllPagoCondominos(beanLogin.getLogin().getIdUsuario());
			JSFUtil.crearMensajeInfo("PagoCondominio eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public List<Usuario> getListaCondominio() {
		return listaCondominio;
	}

	public void setListaCondominio(List<Usuario> listaCondominio) {
		this.listaCondominio = listaCondominio;
	}

	public List<PagoCondominio> getListaPagoCondominos() {
		return listaPagoCondominos;
	}

	public void setListaPagoCondominos(List<PagoCondominio> listaPagoCondominos) {
		this.listaPagoCondominos = listaPagoCondominos;
	}

	public PagoCondominio getEditarPagoCondominio() {
		return editarPagoCondominio;
	}

	public void setEditarPagoCondominio(PagoCondominio editarPagoCondominio) {
		this.editarPagoCondominio = editarPagoCondominio;
	}

	public long getIdtPagofk() {
		return idtPagofk;
	}

	public void setIdtPagofk(long idtPagofk) {
		this.idtPagofk = idtPagofk;
	}

	public long getIdtPagofkE() {
		return idtPagofkE;
	}

	public void setIdtPagofkE(long idtPagofkE) {
		this.idtPagofkE = idtPagofkE;
	}

	public PagoCondominio getPagoCondominio() {
		return pagoCondominio;
	}

	public void setPagoCondominio(PagoCondominio pagoCondominio) {
		this.pagoCondominio = pagoCondominio;
	}

}
