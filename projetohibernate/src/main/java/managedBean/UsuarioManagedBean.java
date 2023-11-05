package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Dao.DaoGeneric;
import Model.Usuario;

@ManagedBean(name = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean {
	
	private Usuario usuario = new Usuario();
	
	private DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
	private List<Usuario> list = new ArrayList<Usuario>();
	
	@PostConstruct
	public void init() {
		list = daoGeneric.listar(Usuario.class);
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String salvar() {
		 daoGeneric.salvar(usuario); 
		 list.add(usuario);
		 this.novo();
		 FacesContext.getCurrentInstance().
		 	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação","Salvo com sucesso!!!"));
		 	
	return "";
	}
	
	public String novo() {
		usuario = new Usuario();
		
		return "";
	}
	public List<Usuario> getList() {
		
		return list;
	}
	
	public String deletar() {
		
		try {
			daoGeneric.deletarPorId(usuario);
			list.remove(usuario);
		} catch (Exception e) {
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().
				addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Informação","Existem Telefones para o usuario!!!"));
			 	
				
			}
			e.printStackTrace();
		}
		this.novo();
		
		 FacesContext.getCurrentInstance().
		 	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação","Deletado com Sucesso!!!"));
		 	
		
		return "";
	}
	
	
	
	

}
