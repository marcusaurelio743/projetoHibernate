package projetoHibernate;

import org.junit.Test;

import Dao.DaoGeneric;
import Model.Usuario;

public class testeHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = new Usuario();
		pessoa.setEmail("ddd@email.com");
		pessoa.setNome("UsuarioTeste 2");
		pessoa.setIdade(20);
		pessoa.setSobrenome("Usuario");
		pessoa.setLogin("admin");
		pessoa.setSenha("admin");
		
		daoGeneric.salvar(pessoa);
		
	}

}
