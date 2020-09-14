package condominio.modulos.tesorero.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Gasto;
import condominio.core.model.entities.Rol;
import condominio.core.model.entities.TipoGasto;
import condominio.core.model.entities.TipoPago;

/**
 * Session Bean implementation class ManagerTesorero
 */
@Stateless
@LocalBean
public class ManagerTesorero {

	@PersistenceContext
	private EntityManager em;

	public ManagerTesorero() {
	}

	/**
	 * Lista de gastos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gasto> findAllGastos() {
		Query q = em.createQuery("SELECT r FROM Gasto r order by r.idgastoc asc", Gasto.class);
		List<Gasto> lista = q.getResultList();
		return lista;
	}

	/**
	 * Crear nuevo gasto
	 * 
	 * @param gasto
	 * @throws Exception
	 */
	public void ingresarGasto(Gasto gasto) throws Exception {
		if (gasto != null) {
			em.persist(gasto);
		} else {
			throw new Exception("El objeto gasto no ha sido cargado correctamente.!");
		}
	}

	/**
	 * Encontrar Gasto por ID
	 * 
	 * @param idGasto
	 * @return
	 */
	public Gasto findGastoById(long idGasto) {
		Gasto gasto = em.find(Gasto.class, idGasto);
		return gasto;
	}

	/**
	 * Editar Gasto
	 * 
	 * @param g
	 * @throws Exception
	 */
	public void editarGasto(Gasto g) throws Exception {
		if (g == null) {
			throw new Exception("El objeto gasto no ha sido cargado correctamente.!");
		} else {
			em.merge(g);
		}
	}

	/**
	 * Eliminar Gasto
	 * 
	 * @param idGasto
	 * @throws Exception
	 */
	public void eliminarGasto(long idGasto) throws Exception {
		if (idGasto == 0) {
			throw new Exception("El tipo gasto no existe vuelva a intentarlo");
		} else {
			Gasto g = new Gasto();
			g = findGastoById(idGasto);
			if (g == null) {
				throw new Exception("No se ha encontrado gasto");
			} else {
				em.remove(g);
			}
		}
	}

	/**
	 * Lista de tipo de gastos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TipoGasto> findAllTipoGastos() {
		Query q = em.createQuery("SELECT r FROM TipoGasto r order by r.idgasto asc", TipoGasto.class);
		List<TipoGasto> lista = q.getResultList();
		return lista;
	}

	/**
	 * Crear nuevo tipo gasto
	 * 
	 * @param gasto
	 * @throws Exception
	 */
	public void ingresarTipoGasto(TipoGasto gasto) throws Exception {
		if (gasto != null) {
			{
				TipoGasto g = new TipoGasto();
				g = findTipoGastoByNombre(gasto.getNombre());
				if (g == null) {
					em.persist(gasto);
				} else {
					throw new Exception("Ya existe un tipo gasto con ese nombre!");
				}
			}
		} else {
			throw new Exception("El objeto tipo gasto no ha sido cargado correctamente.!");
		}
	}

	/**
	 * Encontrar Tipo Gasto por ID
	 * 
	 * @param idGasto
	 * @return
	 */
	public TipoGasto findTipoGastoById(long idGasto) {
		TipoGasto gasto = em.find(TipoGasto.class, idGasto);
		return gasto;
	}

	/**
	 * Encontrar Tipo Gasto por Nombre
	 * 
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TipoGasto findTipoGastoByNombre(String nombre) {
		Query q = em.createQuery("SELECT r FROM TipoGasto r " + " where r.nombre=?1", TipoGasto.class);
		q.setParameter(1, nombre);
		TipoGasto r = new TipoGasto();
		List<TipoGasto> lista = q.getResultList();
		if (lista.isEmpty()) {
			return null;
		} else {
			r = lista.get(0);
			return r;
		}

	}

	/**
	 * Editar Tipo Gasto
	 * 
	 * @param g
	 * @throws Exception
	 */
	public void editarTipoGasto(TipoGasto g) throws Exception {
		if (g == null) {
			throw new Exception("El objeto tipo gasto no ha sido cargado correctamente.!");
		} else {
			TipoGasto gasto = new TipoGasto();
			gasto = findTipoGastoByNombre(g.getNombre());
			if (gasto == null) {
				em.merge(g);
			} else if (g.getIdgasto() != gasto.getIdgasto()) {
				throw new Exception("Ya existe un tipo gasto con ese nombre!");
			}
		}
	}

	/**
	 * Eliminar Tipo Gasto
	 * 
	 * @param idGasto
	 * @throws Exception
	 */
	public void eliminarTipoGasto(long idGasto) throws Exception {
		if (idGasto == 0) {
			throw new Exception("El tipo gasto no existe vuelva a intentarlo");
		} else {
			TipoGasto g = new TipoGasto();
			g = findTipoGastoById(idGasto);
			if (g == null) {
				throw new Exception("No se ha encontrado tipo gasto");
			} else {
				em.remove(g);
			}
		}
	}
	
	/**
	 * Lista de tipo de pago
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TipoPago> findAllTipoPagos() {
		Query q = em.createQuery("SELECT r FROM TipoPago r order by r.idPago asc", TipoPago.class);
		List<TipoPago> lista = q.getResultList();
		return lista;
	}

	/**
	 * Crear nuevo tipo Pago
	 * 
	 * @param Pago
	 * @throws Exception
	 */
	public void ingresarTipoPago(TipoPago pago) throws Exception {
		if (pago != null) {
			{
				TipoPago g = new TipoPago();
				g = findTipoPagoByNombre(pago.getNombre());
				if (g == null) {
					em.persist(pago);
				} else {
					throw new Exception("Ya existe un tipo pago con ese nombre!");
				}
			}
		} else {
			throw new Exception("El objeto tipo pago no ha sido cargado correctamente.!");
		}
	}

	/**
	 * Encontrar Tipo Pago por ID
	 * 
	 * @param idPago
	 * @return
	 */
	public TipoPago findTipoPagoById(long idPago) {
		TipoPago Pago = em.find(TipoPago.class, idPago);
		return Pago;
	}

	/**
	 * Encontrar Tipo Pago por Nombre
	 * 
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TipoPago findTipoPagoByNombre(String nombre) {
		Query q = em.createQuery("SELECT r FROM TipoPago r " + " where r.nombre=?1", TipoPago.class);
		q.setParameter(1, nombre);
		TipoPago r = new TipoPago();
		List<TipoPago> lista = q.getResultList();
		if (lista.isEmpty()) {
			return null;
		} else {
			r = lista.get(0);
			return r;
		}

	}

	/**
	 * Editar Tipo Pago
	 * 
	 * @param g
	 * @throws Exception
	 */
	public void editarTipoPago(TipoPago g) throws Exception {
		if (g == null) {
			throw new Exception("El objeto tipo pago no ha sido cargado correctamente.!");
		} else {
			TipoPago Pago = new TipoPago();
			Pago = findTipoPagoByNombre(g.getNombre());
			if (Pago == null) {
				em.merge(g);
			} else if (g.getIdpago() != Pago.getIdpago()) {
				throw new Exception("Ya existe un tipo pago con ese nombre!");
			}
		}
	}

	/**
	 * Eliminar Tipo Pago
	 * 
	 * @param idPago
	 * @throws Exception
	 */
	public void eliminarTipoPago(long idPago) throws Exception {
		if (idPago == 0) {
			throw new Exception("El tipo pago no existe vuelva a intentarlo");
		} else {
			TipoPago g = new TipoPago();
			g = findTipoPagoById(idPago);
			if (g == null) {
				throw new Exception("No se ha encontrado tipo pago");
			} else {
				em.remove(g);
			}
		}
	}
}
