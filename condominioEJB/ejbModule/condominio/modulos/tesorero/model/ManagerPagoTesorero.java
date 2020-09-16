package condominio.modulos.tesorero.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.PagoCondomino;

/**
 * Session Bean implementation class ManagerTesorero
 */
@Stateless
@LocalBean
public class ManagerPagoTesorero {

	@PersistenceContext
	private EntityManager em;

	public ManagerPagoTesorero() {
	}

	/**
	 * Lista de PagoCondominos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PagoCondomino> findAllPagoCondominos() {
		Query q = em.createQuery("SELECT r FROM PagoCondomino r order by r.idpagoc asc", PagoCondomino.class);
		List<PagoCondomino> lista = q.getResultList();
		return lista;
	}

	/**
	 * Crear nuevo PagoCondomino
	 * 
	 * @param PagoCondomino
	 * @throws Exception
	 */
	public void ingresarPagoCondomino(PagoCondomino PagoCondomino) throws Exception {
		if (PagoCondomino != null) {
			em.persist(PagoCondomino);
		} else {
			throw new Exception("El objeto pago condominio no ha sido cargado correctamente.!");
		}
	}

	/**
	 * Encontrar PagoCondomino por ID
	 * 
	 * @param idPagoCondomino
	 * @return
	 */
	public PagoCondomino findPagoCondominoById(long idPagoCondomino) {
		PagoCondomino PagoCondomino = em.find(PagoCondomino.class, idPagoCondomino);
		return PagoCondomino;
	}

	/**
	 * Editar PagoCondomino
	 * 
	 * @param g
	 * @throws Exception
	 */
	public void editarPagoCondomino(PagoCondomino g) throws Exception {
		if (g == null) {
			throw new Exception("El objeto pago condomino no ha sido cargado correctamente.!");
		} else {
			em.merge(g);
		}
	}

	/**
	 * Eliminar PagoCondomino
	 * 
	 * @param idPagoCondomino
	 * @throws Exception
	 */
	public void eliminarPagoCondomino(long idPagoCondomino) throws Exception {
		if (idPagoCondomino == 0) {
			throw new Exception("El tipo pago condomino no existe vuelva a intentarlo");
		} else {
			PagoCondomino g = new PagoCondomino();
			g = findPagoCondominoById(idPagoCondomino);
			if (g == null) {
				throw new Exception("No se ha encontrado PagoCondomino");
			} else {
				em.remove(g);
			}
		}
	}
	
}
