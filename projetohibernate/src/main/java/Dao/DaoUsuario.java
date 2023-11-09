package Dao;

import Model.Usuario;

public class DaoUsuario<E> extends DaoGeneric<Usuario> {
	
	public void removerUsuario(Usuario usuario) throws Exception {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(usuario);
		
		getEntityManager().getTransaction().commit();
		
		super.deletarPorId(usuario);
		
	}

}
