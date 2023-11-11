package datatablelazer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import Dao.DaoUsuario;
import Model.Usuario;

public class LazyDataTableModelUsuario<T> extends LazyDataModel<Usuario> {
	private DaoUsuario<Usuario> dao = new DaoUsuario<Usuario>();
	
	public List<Usuario> list = new ArrayList<Usuario>();
	private String sql = "from Usuario";
	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		list = (List<Usuario>) dao.getEntityManager().createQuery(getSql())
				.setFirstResult(first)
				.setMaxResults(pageSize).getResultList();
		sql = "from Usuario";
		
		setPageSize(pageSize);
		
		Integer qtdResgistro = Integer.parseInt(dao.getEntityManager()
				.createQuery("select count(1) "+getSql()).getSingleResult().toString()); 
		
		setRowCount(qtdResgistro);
		return list;
		
	}
	public List<Usuario> getList() {
		return list;
	}
	
	public void pesquisa(String CampoPesquisa) {
		sql+=" where nome like '%"+CampoPesquisa+"%' ";
	}
	
	public String getSql() {
		return sql;
	}

}
