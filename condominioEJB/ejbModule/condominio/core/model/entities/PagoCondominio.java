package condominio.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the pago_condominio database table.
 * 
 */
@Entity
@Table(name="pago_condominio")
@NamedQuery(name="PagoCondominio.findAll", query="SELECT p FROM PagoCondominio p")
public class PagoCondominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAGO_CONDOMINIO_IDPAGOC_GENERATOR", sequenceName="SEQ_PAGO_CONDOMINIO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAGO_CONDOMINIO_IDPAGOC_GENERATOR")
	private long idpagoc;

	@Temporal(TemporalType.DATE)
	private Date fechatransaccion;

	private Boolean valido;

	private BigDecimal valor;

	//bi-directional many-to-one association to TipoPago
	@ManyToOne
	@JoinColumn(name="idpago")
	private TipoPago tipoPago;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public PagoCondominio() {
	}

	public long getIdpagoc() {
		return this.idpagoc;
	}

	public void setIdpagoc(long idpagoc) {
		this.idpagoc = idpagoc;
	}

	public Date getFechatransaccion() {
		return this.fechatransaccion;
	}

	public void setFechatransaccion(Date fechatransaccion) {
		this.fechatransaccion = fechatransaccion;
	}

	public Boolean getValido() {
		return this.valido;
	}

	public void setValido(Boolean valido) {
		this.valido = valido;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPago getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}