package condominio.modulos.tesorero.view.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import condominio.core.model.entities.Gasto;
import condominio.core.model.entities.PagoCondominio;
import condominio.core.model.entities.TipoGasto;
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
public class BeanTesorero implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Gasto> listaGastos;
	private Gasto gasto = new Gasto();
	private Gasto editarGasto = new Gasto();
	private long idtgastofk;
	private long idtgastofkE;
	private PieChartModel pieModel;
	private double ingresos=0;
	private double gastos=0;
	private double total=0;
	@EJB
	ManagerTesorero managerTesorero;
	@EJB
	ManagerPagoTesorero managerPagos;
	@EJB
	ManagerUsuario managerUsuario;
	@Inject
	private BeanLogin beanLogin;

	@PostConstruct
	public void init() {
		try {
			listaGastos = managerTesorero.findAllGastos();
		} catch (Exception e) {
			e.getStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		createPieModel();

	}

	public void actionListenerCargarGasto(Gasto g) {
		editarGasto = g;
		idtgastofkE=g.getTipoGasto().getIdgasto();
	}

	public void actionListenerIngresarGasto() {
		try {
			TipoGasto tipoGasto = managerTesorero.findTipoGastoById(idtgastofk);
			Date fechatransaccion = new Date();
			Usuario usuario = managerUsuario.findUsuarioById(beanLogin.getLogin().getIdUsuario());
			gasto.setTipoGasto(tipoGasto);
			gasto.setFechatransaccion(fechatransaccion);
			gasto.setUsuario(usuario);
			managerTesorero.ingresarGasto(gasto);
			listaGastos = managerTesorero.findAllGastos();
			gasto = new Gasto();
			JSFUtil.crearMensajeInfo("Gasto creado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			listaGastos = managerTesorero.findAllGastos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEditarGasto() {
		try {
			TipoGasto tipoGasto = managerTesorero.findTipoGastoById(idtgastofkE);
			Date fechatransaccion = new Date();
			Usuario usuario = managerUsuario.findUsuarioById(beanLogin.getLogin().getIdUsuario());
			editarGasto.setTipoGasto(tipoGasto);
			editarGasto.setFechatransaccion(fechatransaccion);
			editarGasto.setUsuario(usuario);
			managerTesorero.editarGasto(editarGasto);
			listaGastos = managerTesorero.findAllGastos();
			JSFUtil.crearMensajeInfo("Gasto editado correctamente.!");
		} catch (Exception e) {
			e.printStackTrace();
			listaGastos = managerTesorero.findAllGastos();
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}

	public void actionListenerEliminarGasto(long idGasto) {
		try {
			managerTesorero.eliminarGasto(idGasto);
			listaGastos = managerTesorero.findAllGastos();
			JSFUtil.crearMensajeInfo("Gasto eliminado correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	private void createPieModel() {
        pieModel= new PieChartModel();
        List<PagoCondominio> listaPagos=managerPagos.findAllPagoCondominos();
        for(int i=0; i<listaPagos.size(); i++) {
        	if(listaPagos.get(i).getValido()) {
        	ingresos+=listaPagos.get(i).getValor().doubleValue();
        	}
        }
        for(int i=0; i<listaGastos.size(); i++) {
        	gastos+=listaGastos.get(i).getValor().doubleValue();
        }
        
        total=ingresos-gastos;
        pieModel.set("Ingresos", ingresos);
        pieModel.set("Gastos", gastos);
        pieModel.setShowDataLabels(true);
        pieModel.setSeriesColors("75DBFF ,FF757C, 0A0000");
        pieModel.setNegativeSeriesColors("000000");
        pieModel.setTitle("Saldo");
        pieModel.setLegendPosition("w");
        pieModel.setShadow(false);
        pieModel.setSliceMargin(5);
    }

	public List<Gasto> getListaGastos() {
		return listaGastos;
	}

	public void setListaGastos(List<Gasto> listaGastos) {
		this.listaGastos = listaGastos;
	}

	public Gasto getGasto() {
		return gasto;
	}

	public void setGasto(Gasto gasto) {
		this.gasto = gasto;
	}

	public Gasto getEditarGasto() {
		return editarGasto;
	}

	public void setEditarGasto(Gasto editarGasto) {
		this.editarGasto = editarGasto;
	}

	public long getIdtgastofk() {
		return idtgastofk;
	}

	public void setIdtgastofk(long idtgastofk) {
		this.idtgastofk = idtgastofk;
	}

	public long getIdtgastofkE() {
		return idtgastofkE;
	}

	public void setIdtgastofkE(long idtgastofkE) {
		this.idtgastofkE = idtgastofkE;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public double getIngresos() {
		return ingresos;
	}

	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public double getGastos() {
		return gastos;
	}

	public void setGastos(double gastos) {
		this.gastos = gastos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
}
