package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Dao.DaoUsuario;
import Model.Usuario;

@ManagedBean(name = "telefoneManagedBean")
@ViewScoped
public class TelefoneManagedBean {
	
	private Usuario user = new Usuario();
	private DaoUsuario<Usuario> daouser = new DaoUsuario<Usuario>();
	
	@PostConstruct
	public void init() {
		String coduser = FacesContext.
					getCurrentInstance().
					getExternalContext().
					getRequestParameterMap().get("codigoUser");
		user = daouser.pesquisar(Long.valueOf(coduser), Usuario.class);
		
		
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Usuario getUser() {
		return user;
	}

}
