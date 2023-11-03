package projetoHibernate;

import java.util.Scanner;

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
	@Test
	public void testeInserir() {
		Scanner sc = new Scanner(System.in);
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario p = new Usuario();
		System.out.println("insere o nome do usuario");
		p.setNome(sc.nextLine());
		System.out.println("insere o sobrenome");
		p.setSobrenome(sc.nextLine());
		System.out.println("insere o   Email");
		p.setEmail(sc.nextLine());
		System.out.println("insere idade");
		p.setIdade(sc.nextInt());
		sc.nextLine();
		
		System.out.println("insere o Login");
		p.setLogin(sc.nextLine());
		System.out.println("insere senha");
		p.setSenha(sc.nextLine());
		
		daoGeneric.salvar(p);
		
		sc.close();
		
		
		
	}
	
	@Test
	public void testeBuscar() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		Usuario pessoa = new Usuario();
		pessoa.setId(3L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
		
	}
	
	@Test
	public void testeBuscar2() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisar(2L,Usuario.class);
		
		System.out.println(pessoa);
		
		
	}

}
