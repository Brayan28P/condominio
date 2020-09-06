package condominio.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the gasto database table.
 * 
 */
@Entity
@NamedQuery(name="Gasto.findAll", query="SELECT g FROM Gasto g")
public class Gasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GASTO_IDGASTOC_GENERATOR", sequenceName="SEQ_GASTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GASTO_IDGASTOC_GENERATOR")
	private long idgastoc;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechatransaccion;

	private BigDecimal valor;

	//bi-directional many-to-one association to TipoGasto
	@ManyToOne
	@JoinColumn(name="idgasto")
	private TipoGasto tipoGasto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	public Gasto() {
	}

	public long getIdgastoc() {
		return this.idgastoc;
	}

	public void setIdgastoc(long idgastoc) {
		this.idgastoc = idgastoc;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechatransaccion() {
		return this.fechatransaccion;
	}

	public void setFechatransaccion(Date fechatransaccion) {
		this.fechatransaccion = fechatransaccion;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoGasto getTipoGasto() {
		return this.tipoGasto;
	}

	public void setTipoGasto(TipoGasto tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}