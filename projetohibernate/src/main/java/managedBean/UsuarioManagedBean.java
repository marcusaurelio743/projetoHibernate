package managedBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.faces.context.FacesContext;

import Dao.DaoEmail;
import Dao.DaoUsuario;
import Model.Email;
import Model.Usuario;

@ManagedBean(name = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean {
	
	private Usuario usuario = new Usuario();
	
	private List<Usuario> list = new ArrayList<Usuario>();
	private DaoUsuario<Usuario> daoUsuario = new DaoUsuario();
	private DaoEmail<Email> daoEmail = new DaoEmail<Email>();
	private BarChartModel barChartModel = new BarChartModel();
	private Email email = new Email();
	
	@PostConstruct
	public void init() {
		list = daoUsuario.listar(Usuario.class);
		
		barChartModel = new BarChartModel();
		
		ChartSeries userSalario = new ChartSeries();//grupo de usuarios
		
		for (Usuario usuario : list) {
			
			
				userSalario.set(usuario.getNome(), usuario.getSalario());//vai verificar cada usuario e setar o nome e salario
			
		}
		barChartModel.addSeries(userSalario);//adiciona o grupo no barmodel
		barChartModel.setTitle("Gráfico de Salário");
		
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public Email getEmail() {
		return email;
	}
	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String salvar() {
		 daoUsuario.salvar(usuario); 
		 list.add(usuario);
		 this.novo();
		 init();
		 FacesContext.getCurrentInstance().
		 	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação","Salvo com sucesso!!!"));
		 	
	return "";
	}
	
	public void addEmail() {
		email.setUsuario(usuario);
		email = daoEmail.Meger(email);
		usuario.getEmails().add(email);
		email = new Email();
		
		FacesContext.getCurrentInstance().
	 	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação","E-mail Salvo !!"));
	}
	
	public void removerEmail() throws Exception {
		String codigoemail = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("codigoEmail");
		Email remover = new Email();
		remover.setId(Long.parseLong(codigoemail));
		daoEmail.deletarPorId(remover);
		usuario.getEmails().remove(remover);
		
		FacesContext.getCurrentInstance().
	 	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informação","E-mail Excluido !!"));
	
		
	}
	
	public String novo() {
		usuario = new Usuario();
		
		return "";
	} 
	
	public String pesquisaCep(AjaxBehaviorEvent event) {
		
		try {
			//System.out.println("cep digitado: "+usuario.getCep());
			
			URL url = new URL("https://viacep.com.br/ws/"+usuario.getCep()+"/json/");
			
			URLConnection connection = url.openConnection();
			
			InputStream is = connection.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			
			String cep = " ";
			
			StringBuilder jsoncep = new StringBuilder();
			
			while((cep = br.readLine()) != null) {
				jsoncep.append(cep);
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			 
			Usuario useAux = objectMapper.readValue(jsoncep.toString(),Usuario.class);
			usuario.setCep(useAux.getCep());
			usuario.setBairro(useAux.getBairro());
			usuario.setComplemento(useAux.getComplemento());
			usuario.setLogradouro(useAux.getLogradouro());
			usuario.setLocalidade(useAux.getLocalidade());
			usuario.setUf(useAux.getUf());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
		
	}
	public List<Usuario> getList() {
		
		return list;
	}
	
	public String deletar() {
		
		try {
			daoUsuario.removerUsuario(usuario);
			list.remove(usuario);
			init();
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
