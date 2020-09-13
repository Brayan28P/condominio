package condominio.modulos.tesorero.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.Gasto;

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
		Query q = em.createQuery("SELECT r FROM Gasto r order by r.idgasto asc", Gasto.class);
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
}
