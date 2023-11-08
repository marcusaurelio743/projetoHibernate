package projetoHibernate;

import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import Dao.DaoGeneric;
import Model.Telefone;
import Model.Usuario;

public class testeHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = new Usuario();
		//pessoa.setEmail("ddd@email.com");
		pessoa.setNome("fransisco");
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
		//p.setEmail(sc.nextLine());
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
	
	@Test
	public void testeAtualizar() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisar(1L,Usuario.class);
		pessoa.setNome("Nome Atualizado");
		//pessoa.setEmail("Email.com.Mudou");
		pessoa.setIdade(99);
		
		pessoa = daoGeneric.Meger(pessoa);
		
		System.out.println(pessoa);
		
		
	}
	
	@Test
	public void testeDelete() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		Usuario pessoa = daoGeneric.pesquisar(1L,Usuario.class);
		
		//daoGeneric.deletarPorId(pessoa);
		
		
	}
	
	@Test
	public void testeConsultar() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		List<Usuario> list = daoGeneric.listar(Usuario.class);
		
		for (Usuario usuario : list) {
			System.out.println(usuario);
			System.out.println("-------------------------------------");
		}
		
		
	}
	
	@Test
	public void testequery() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		List<Usuario> list = daoGeneric.getEntityManager().createQuery("from Usuario where nome = 'paulo'").getResultList();
		
		for (Usuario usuario : list) {
			System.out.println("----------------------------------------");
			System.out.println(usuario);
		}
		
		
	}
	
	@Test
	public void testequeryMaxResult() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		
		List<Usuario> list = daoGeneric.getEntityManager().
				createQuery("from Usuario order by id ").setMaxResults(2).getResultList();
		
		for (Usuario usuario : list) {
			System.out.println("----------------------------------------");
			System.out.println(usuario);
		}
		
		
	}
	
	@Test
	public void testeGravaFone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		Usuario pessoa = (Usuario) daoGeneric.pesquisar(2L, Usuario.class);
		
		Telefone fone = new Telefone();
		fone.setTipo("servico");
		fone.setNumero("(55) 7676767676767");
		fone.setUsuario(pessoa);
		daoGeneric.Meger(fone);
		
		
		
	}
	
	@Test
	public void testeConsultaFone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		Usuario pessoa = (Usuario) daoGeneric.pesquisar(2L, Usuario.class);
		System.out.println();
		for (Telefone telefone : pessoa.getTelefones()) {
			System.out.println(telefone.getId());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getUsuario().getNome());
			System.out.println("===============================================");
		}
		
		
		
	}

}
