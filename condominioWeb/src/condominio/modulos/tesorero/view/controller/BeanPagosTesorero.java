package condominio.modulos.tesorero.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import condominio.core.model.entities.PagoCondominio;
import condominio.core.model.entities.TipoPago;
import condominio.core.model.entities.Usuario;
import condominio.modulos.login.view.controller.BeanLogin;
import condominio.modulos.tesorero.model.ManagerPagoTesorero;
import condominio.modulos.tesorero.model.ManagerTesorero;
import condominio.modulos.usuario.model.ManagerUsuario;
import condominio.modulos.util.view.controller.JSFUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class BeanPagosTesorero implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<PagoCondominio> listaPagoCondominos;
	private PagoCondominio pagoCondominio = new PagoCondominio();
	private PagoCondominio editarPagoCondominio = new PagoCondominio();
	private long idtPagofk;
	private long idtPagofkE;
	private long idCondominiofk;
	@EJB
	ManagerPagoTesorero managerPagoTesorero;
	@EJB
	ManagerTesorero managerTesorero;
	@EJB
	ManagerUsuario managerUsuario;
	@Inject
	private BeanLogin beanLogin;

	@PostConstruct
	public void init() {
		try {
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}

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
			Usuario usuario = managerUsuario.findUsuarioById(idCondominiofk);
			pagoCondominio.setTipoPago(tipoPago);
			pagoCondominio.setFechatransaccion(fechatransaccion);
			pagoCondominio.setUsuario(usuario);
			pagoCondominio.setValido(true);
			managerPagoTesorero.ingresarPagoCondomino(pagoCondominio);
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
			pagoCondominio = new PagoCondominio();
			JSFUtil.crearMensajeInfo("Pago condomino creado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
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
			managerPagoTesorero.editarPagoCondomino(editarPagoCondominio);
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
			JSFUtil.crearMensajeInfo("Pago condomino editado correctamente.!");
		} catch (Exception e) {
			e.printStackTrace();
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarPagoCondomino(long idPagoCondomino) {
		try {
			managerPagoTesorero.eliminarPagoCondomino(idPagoCondomino);
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
			JSFUtil.crearMensajeInfo("PagoCondominio eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public List<PagoCondominio> getListaPagoCondominos() {
		return listaPagoCondominos;
	}

	public void setListaPagoCondominos(List<PagoCondominio> listaPagoCondominos) {
		this.listaPagoCondominos = listaPagoCondominos;
	}

	public PagoCondominio getPagoCondomino() {
		return pagoCondominio;
	}

	public void setPagoCondomino(PagoCondominio pagoCondominio) {
		this.pagoCondominio = pagoCondominio;
	}

	public PagoCondominio geteditarPagoCondominio() {
		return editarPagoCondominio;
	}

	public void seteditarPagoCondominio(PagoCondominio editarPagoCondominio) {
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

	public long getIdCondominiofk() {
		return idCondominiofk;
	}

	public void setIdCondominiofk(long idCondominiofk) {
		this.idCondominiofk = idCondominiofk;
	}

	
}
