package condominio.modulos.condominio.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query q = em.createQuery("SELECT r FROM Usuario r order by r.nombres", Usuario.class);
		List<Usuario> lista = q.getResultList();
		return lista;
	}

}
