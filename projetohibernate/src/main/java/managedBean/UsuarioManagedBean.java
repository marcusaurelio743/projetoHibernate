package managedBean;

import java.util.ArrayList;
import java.util.List;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String salvar() {
		 daoGeneric.salvar(usuario); 
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
		list = daoGeneric.listar(Usuario.class);
		return list;
	}
	
	public String deletar() {
		
		daoGeneric.deletarPorId(usuario);
		this.novo();
		
		return "";
	}
	
	

}
