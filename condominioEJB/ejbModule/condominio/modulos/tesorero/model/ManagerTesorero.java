package condominio.modulos.tesorero.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Gasto;
import condominio.core.model.entities.TipoGasto;

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
			throw new Exception("El objeto Gasto no ha sido cargado correctamente.!");
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
			throw new Exception("El objeto Gasto no ha sido cargado correctamente.!");
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
			throw new Exception("El Gasto no existe vuelva a intentarlo");
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
	public List<Gasto> findAllTipoGastos() {
		Query q = em.createQuery("SELECT r FROM tipo_gasto r order by r.idgasto asc", Gasto.class);
		List<Gasto> lista = q.getResultList();
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
			TipoGasto g = new TipoGasto();
			g = findTipoGastoById(gasto.getIdgasto());
			if (g == null) {
				em.persist(gasto);
			} else if (g.getIdgasto() != gasto.getIdgasto()) {
				throw new Exception("Ya existe un tipo gasto con ese nombre!");
			}
			em.persist(gasto);
		} else {
			throw new Exception("El objeto Tipo Gasto no ha sido cargado correctamente.!");
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
	 * Editar Tipo Gasto
	 * 
	 * @param g
	 * @throws Exception
	 */
	public void editarTipoGasto(TipoGasto g) throws Exception {
		if (g == null) {
			throw new Exception("El objeto Tipo Gasto no ha sido cargado correctamente.!");
		} else {
			TipoGasto gasto = new TipoGasto();
			gasto = findTipoGastoById(g.getIdgasto());
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
			throw new Exception("El Tipo Gasto no existe vuelva a intentarlo");
		} else {
			TipoGasto g = new TipoGasto();
			g = findTipoGastoById(idGasto);
			if (g == null) {
				throw new Exception("No se ha encontrado gasto");
			} else {
				em.remove(g);
			}
		}
	}
}
