package Dao;

import Model.Usuario;

public class DaoUsuario<E> extends DaoGeneric<Usuario> {
	
	public void removerUsuario(Usuario usuario) throws Exception {
		getEntityManager().getTransaction().begin();
		String sqlDeleteFone = "delete from telefone where usuario_id =  "+usuario.getId();
		
		getEntityManager().createNativeQuery(sqlDeleteFone).executeUpdate();
		
		getEntityManager().getTransaction().commit();
		
		super.deletarPorId(usuario);
		
	}

}
