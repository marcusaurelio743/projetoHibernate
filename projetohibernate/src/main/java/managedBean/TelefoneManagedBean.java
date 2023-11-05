package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Dao.DaoTelefone;
import Dao.DaoUsuario;
import Model.Telefone;
import Model.Usuario;

@ManagedBean(name = "telefoneManagedBean")
@ViewScoped
public class TelefoneManagedBean {
	
	private Usuario user = new Usuario();
	private DaoUsuario<Usuario> daouser = new DaoUsuario<Usuario>();
	private DaoTelefone<Telefone> daoTelefone = new DaoTelefone<Telefone>();
	private Telefone telefone = new Telefone();
	
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

	public void setDaoTelefone(DaoTelefone<Telefone> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public DaoTelefone<Telefone> getDaoTelefone() {
		return daoTelefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Telefone getTelefone() {
		return telefone;
	}
	
	public String salvar() {
		telefone.setUsuario(user);
		daoTelefone.salvar(telefone);
		
		telefone = new Telefone();
		
		 FacesContext.getCurrentInstance().
		 	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação","Salvo com sucesso!!!"));
	return "";	 
	}

}
