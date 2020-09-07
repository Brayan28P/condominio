package condominio.modulos.login.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {
	@PersistenceContext
	private EntityManager em;
    public ManagerLogin() {
        // TODO Auto-generated constructor stub
    }

}
