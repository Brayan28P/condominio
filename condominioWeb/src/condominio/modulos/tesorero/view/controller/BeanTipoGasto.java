package condominio.modulos.tesorero.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import condominio.core.model.entities.TipoGasto;
import condominio.modulos.tesorero.model.ManagerTesorero;
import condominio.modulos.util.view.controller.JSFUtil;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BeanTipoGasto implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<TipoGasto> listaTipoGastos;
	private TipoGasto tipoGasto = new TipoGasto();
	private TipoGasto editarTipoGasto = new TipoGasto();

	@EJB
	ManagerTesorero managerTesorero;

	@PostConstruct
	public void init() {
		try {
			listaTipoGastos = managerTesorero.findAllTipoGastos();
		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerCargarTipoGasto(TipoGasto g) {
		editarTipoGasto = g;
	}

	public void actionListenerIngresarTipoGasto() {
		try {
			System.out.print(tipoGasto.getNombre());
			managerTesorero.ingresarTipoGasto(tipoGasto);
			listaTipoGastos = managerTesorero.findAllTipoGastos();
			tipoGasto = new TipoGasto();
			JSFUtil.crearMensajeInfo("Tipo gasto creado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			listaTipoGastos = managerTesorero.findAllTipoGastos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void actionListenerEditarTipoGasto() {
		try {
			managerTesorero.editarTipoGasto(editarTipoGasto);
			listaTipoGastos = managerTesorero.findAllTipoGastos();
			JSFUtil.crearMensajeInfo("Tipo gasto editado correctamente.!");
		} catch (Exception e) { 
			e.printStackTrace();
			listaTipoGastos = managerTesorero.findAllTipoGastos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarTipoGasto(long idTipoGasto) {
		try {
			managerTesorero.eliminarTipoGasto(idTipoGasto);
			listaTipoGastos = managerTesorero.findAllTipoGastos();
			JSFUtil.crearMensajeInfo("Tipo gasto eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public List<TipoGasto> getListaTipoGastos() {
		return listaTipoGastos;
	}

	public void setListaTipoGastos(List<TipoGasto> listaTipoGastos) {
		this.listaTipoGastos = listaTipoGastos;
	}

	public TipoGasto getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public TipoGasto getEditarTipoGasto() {
		return editarTipoGasto;
	}

	public void setEditarTipoGasto(TipoGasto editarTipoGasto) {
		this.editarTipoGasto = editarTipoGasto;
	}

}
