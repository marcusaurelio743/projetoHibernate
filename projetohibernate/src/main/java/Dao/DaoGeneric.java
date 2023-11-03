package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import projetoHibernate.HibernateUtil;

public class DaoGeneric<E> {
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}
	
}
