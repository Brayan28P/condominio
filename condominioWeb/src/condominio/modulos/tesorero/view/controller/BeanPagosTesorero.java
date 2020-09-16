package condominio.modulos.tesorero.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import condominio.core.model.entities.PagoCondomino;
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
	private List<PagoCondomino> listaPagoCondominos;
	private PagoCondomino pagoCondomino = new PagoCondomino();
	private PagoCondomino editarPagoCondominio = new PagoCondomino();
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

	public void actionListenerCargarPagoCondomino(PagoCondomino g) {
		editarPagoCondominio = g;
		idtPagofkE=g.getTipoPago().getIdpago();
	}

	public void actionListenerIngresarPagoCondomino() {
		try {
			TipoPago tipoPago = managerTesorero.findTipoPagoById(idtPagofk);
			System.out.print(tipoPago.getNombre());
			Date fechatransaccion = new Date();
			Usuario usuario = managerUsuario.findUsuarioById(idCondominiofk);
			pagoCondomino.setTipoPago(tipoPago);
			pagoCondomino.setFechatransaccion(fechatransaccion);
			pagoCondomino.setUsuario(usuario);
			pagoCondomino.setValido(true);
			managerPagoTesorero.ingresarPagoCondomino(pagoCondomino);
			listaPagoCondominos = managerPagoTesorero.findAllPagoCondominos();
			pagoCondomino = new PagoCondomino();
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
			JSFUtil.crearMensajeInfo("PagoCondomino eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public List<PagoCondomino> getListaPagoCondominos() {
		return listaPagoCondominos;
	}

	public void setListaPagoCondominos(List<PagoCondomino> listaPagoCondominos) {
		this.listaPagoCondominos = listaPagoCondominos;
	}

	public PagoCondomino getPagoCondomino() {
		return pagoCondomino;
	}

	public void setPagoCondomino(PagoCondomino pagoCondomino) {
		this.pagoCondomino = pagoCondomino;
	}

	public PagoCondomino geteditarPagoCondominio() {
		return editarPagoCondominio;
	}

	public void seteditarPagoCondominio(PagoCondomino editarPagoCondominio) {
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
