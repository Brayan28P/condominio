package condominio.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_pago database table.
 * 
 */
@Entity
@Table(name="tipo_pago")
@NamedQuery(name="TipoPago.findAll", query="SELECT t FROM TipoPago t")
public class TipoPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_PAGO_IDPAGO_GENERATOR", sequenceName="SEQ_TIPO_PAGO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PAGO_IDPAGO_GENERATOR")
	private long idpago;

	private String nombre;

	//bi-directional many-to-one association to PagoCondominio
	@OneToMany(mappedBy="tipoPago")
	private List<PagoCondominio> pagoCondominios;

	public TipoPago() {
	}

	public long getIdpago() {
		return this.idpago;
	}

	public void setIdpago(long idpago) {
		this.idpago = idpago;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PagoCondominio> getPagoCondominos() {
		return this.pagoCondominios;
	}

	public void setPagoCondominos(List<PagoCondominio> pagoCondominios) {
		this.pagoCondominios = pagoCondominios;
	}

	public PagoCondominio addPagoCondomino(PagoCondominio pagoCondominio) {
		getPagoCondominos().add(pagoCondominio);
		pagoCondominio.setTipoPago(this);

		return pagoCondominio;
	}

	public PagoCondominio removePagoCondomino(PagoCondominio pagoCondominio) {
		getPagoCondominos().remove(pagoCondominio);
		pagoCondominio.setTipoPago(null);

		return pagoCondominio;
	}

}