package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Dao.DaoGeneric;
import Model.Usuario;

@ManagedBean(name = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean {
	
	private Usuario usuario = new Usuario();
	
	private DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String salvar() {
		 daoGeneric.salvar(usuario);
		 	
	return "";
	}
	
	public String novo() {
		usuario = new Usuario();
		
		return "";
	}
	
	

}
