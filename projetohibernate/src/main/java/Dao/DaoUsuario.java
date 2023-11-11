package Dao;

import java.util.List;

import javax.persistence.Query;

import Model.Usuario;

public class DaoUsuario<E> extends DaoGeneric<Usuario> {
	
	public void removerUsuario(Usuario usuario) throws Exception {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(usuario);
		
		getEntityManager().getTransaction().commit();
		
		super.deletarPorId(usuario);
		
	}

	public List<Usuario> pesquisar(String campoPesquisa) {
		Query query = super.getEntityManager().createQuery("from Usuario where nome like '%"+campoPesquisa+"%'");
		return query.getResultList();
	}

}
