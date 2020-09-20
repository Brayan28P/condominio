package condominio.modulos.condominio.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import condominio.core.model.entities.PagoCondominio;
import condominio.core.model.entities.Usuario;

/**
 * Session Bean implementation class ManagerCondominio
 */
@Stateless
@LocalBean
public class ManagerCondominio {

	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerCondominio() {
        // TODO Auto-generated constructor stub
    }
    /**
	 * Lista de condominios
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<Usuario> findAllCondominos() {
		Query q = em.createQuery("SELECT r FROM Usuario r where r.rol.nombre='Condominio' order by r.nombres asc", Usuario.class);
		List<Usuario> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Lista de PagoCondominos
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PagoCondominio> findAllPagoCondominos(long idUsuario) {
		Query q = em.createQuery("SELECT r FROM PagoCondominio r where r.usuario.idusuario=?1  order by r.valido asc", PagoCondominio.class);
		q.setParameter(1, idUsuario);
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
