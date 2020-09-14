package condominio.modulos.tesorero.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import condominio.core.model.entities.TipoPago;
import condominio.modulos.tesorero.model.ManagerTesorero;
import condominio.modulos.util.view.controller.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BeanTipoPago implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<TipoPago> listaTipoPagos;
	private TipoPago tipoPago = new TipoPago();
	private TipoPago editarTipoPago = new TipoPago();

	@EJB
	ManagerTesorero managerTesorero;

	@PostConstruct
	public void init() {
		try {
			listaTipoPagos = managerTesorero.findAllTipoPagos();
		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerCargarTipoPago(TipoPago g) {
		editarTipoPago = g;
	}

	public void actionListenerIngresarTipoPago() {
		try {
			System.out.print(tipoPago.getNombre());
			managerTesorero.ingresarTipoPago(tipoPago);
			listaTipoPagos = managerTesorero.findAllTipoPagos();
			tipoPago = new TipoPago();
			JSFUtil.crearMensajeInfo("Tipo Pago creado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			listaTipoPagos = managerTesorero.findAllTipoPagos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerEditarTipoPago() {
		try {
			managerTesorero.editarTipoPago(editarTipoPago);
			listaTipoPagos = managerTesorero.findAllTipoPagos();
			JSFUtil.crearMensajeInfo("Tipo pago editado correctamente.!");
		} catch (Exception e) { 
			e.printStackTrace();
			listaTipoPagos = managerTesorero.findAllTipoPagos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarTipoPago(long idTipoPago) {
		try {
			managerTesorero.eliminarTipoPago(idTipoPago);
			listaTipoPagos = managerTesorero.findAllTipoPagos();
			JSFUtil.crearMensajeInfo("Tipo pago eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public List<TipoPago> getListaTipoPagos() {
		return listaTipoPagos;
	}

	public void setListaTipoPagos(List<TipoPago> listaTipoPagos) {
		this.listaTipoPagos = listaTipoPagos;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public TipoPago getEditarTipoPago() {
		return editarTipoPago;
	}

	public void setEditarTipoPago(TipoPago editarTipoPago) {
		this.editarTipoPago = editarTipoPago;
	}

}
