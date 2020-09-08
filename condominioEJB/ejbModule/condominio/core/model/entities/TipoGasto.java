package condominio.core.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_gasto database table.
 * 
 */
@Entity
@Table(name="tipo_gasto")
@NamedQuery(name="TipoGasto.findAll", query="SELECT t FROM TipoGasto t")
public class TipoGasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPO_GASTO_IDGASTO_GENERATOR", sequenceName="SEQ_TIPO_GASTO",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_GASTO_IDGASTO_GENERATOR")
	private long idgasto;

	private String nombre;

	//bi-directional many-to-one association to Gasto
	@OneToMany(mappedBy="tipoGasto")
	private List<Gasto> gastos;

	public TipoGasto() {
	}

	public long getIdgasto() {
		return this.idgasto;
	}

	public void setIdgasto(long idgasto) {
		this.idgasto = idgasto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Gasto> getGastos() {
		return this.gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	public Gasto addGasto(Gasto gasto) {
		getGastos().add(gasto);
		gasto.setTipoGasto(this);

		return gasto;
	}

	public Gasto removeGasto(Gasto gasto) {
		getGastos().remove(gasto);
		gasto.setTipoGasto(null);

		return gasto;
	}

}