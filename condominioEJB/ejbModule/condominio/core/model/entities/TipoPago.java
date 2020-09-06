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
	@SequenceGenerator(name="TIPO_PAGO_IDPAGO_GENERATOR", sequenceName="SEQ_TIPO_PAGO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PAGO_IDPAGO_GENERATOR")
	private long idpago;

	private String nombre;

	//bi-directional many-to-one association to PagoCondomino
	@OneToMany(mappedBy="tipoPago")
	private List<PagoCondomino> pagoCondominos;

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

	public List<PagoCondomino> getPagoCondominos() {
		return this.pagoCondominos;
	}

	public void setPagoCondominos(List<PagoCondomino> pagoCondominos) {
		this.pagoCondominos = pagoCondominos;
	}

	public PagoCondomino addPagoCondomino(PagoCondomino pagoCondomino) {
		getPagoCondominos().add(pagoCondomino);
		pagoCondomino.setTipoPago(this);

		return pagoCondomino;
	}

	public PagoCondomino removePagoCondomino(PagoCondomino pagoCondomino) {
		getPagoCondominos().remove(pagoCondomino);
		pagoCondomino.setTipoPago(null);

		return pagoCondomino;
	}

}