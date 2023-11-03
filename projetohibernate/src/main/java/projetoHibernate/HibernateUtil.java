package projetoHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;
	
	static {
		Init();
	}
	
	public static void Init() {
		try {
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory("projetohibernate");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();//prover a persistencia
	}

}
