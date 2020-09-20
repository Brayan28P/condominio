package condominio.modulos.tesorero.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.PagoCondominio;

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
	public List<PagoCondominio> findAllPagoCondominos() {
		Query q = em.createQuery("SELECT r FROM PagoCondominio r order by r.fechatransaccion desc", PagoCondominio.class);
		List<PagoCondominio> lista = q.getResultList();
		return lista;
	}

	/**
	 * Crear nuevo PagoCondominio
	 * 
	 * @param PagoCondominio
	 * @throws Exception
	 */
	public void ingresarPagoCondomino(PagoCondominio PagoCondominio) throws Exception {
		if (PagoCondominio != null) {
			em.persist(PagoCondominio);
		} else {
			throw new Exception("El objeto pago condominio no ha sido cargado correctamente.!");
		}
	}

	/**
	 * Encontrar PagoCondominio por ID
	 * 
	 * @param idPagoCondomino
	 * @return
	 */
	public PagoCondominio findPagoCondominoById(long idPagoCondomino) {
		PagoCondominio PagoCondominio = em.find(PagoCondominio.class, idPagoCondomino);
		return PagoCondominio;
	}

	/**
	 * Editar PagoCondominio
	 * 
	 * @param g
	 * @throws Exception
	 */
	public void editarPagoCondomino(PagoCondominio g) throws Exception {
		if (g == null) {
			throw new Exception("El objeto pago condomino no ha sido cargado correctamente.!");
		} else {
			em.merge(g);
		}
	}

	/**
	 * Eliminar PagoCondominio
	 * 
	 * @param idPagoCondomino
	 * @throws Exception
	 */
	public void eliminarPagoCondomino(long idPagoCondomino) throws Exception {
		if (idPagoCondomino == 0) {
			throw new Exception("El tipo pago condomino no existe vuelva a intentarlo");
		} else {
			PagoCondominio g = new PagoCondominio();
			g = findPagoCondominoById(idPagoCondomino);
			if (g == null) {
				throw new Exception("No se ha encontrado pago condominio");
			} else {
				em.remove(g);
			}
		}
	}
	
}
