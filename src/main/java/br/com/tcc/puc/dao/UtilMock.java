package br.com.tcc.puc.dao;

import java.util.ArrayList;
import javax.faces.model.SelectItem;

/**
 * TODO: Modificar essa classe incluindo uma entidade chamada Estado, contendo dois par�metros e modificar essa implementa��o
 * para que ela implemente Dao<Estado>.
 */

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidade que s�o �teis na
 *         aplica��o.
 * 
 */
public class UtilMock {

	private ArrayList<SelectItem> listaEstados = null;

	public UtilMock() {
		listaEstados = new ArrayList<SelectItem>();
		popularEstados();

	}

	private void popularEstados() {
		listaEstados.add(new SelectItem("AC", "Acre"));
		listaEstados.add(new SelectItem("AL", "Alagoas"));
		listaEstados.add(new SelectItem("AP", "Amap�"));
		listaEstados.add(new SelectItem("AM", "Amazonas"));
		listaEstados.add(new SelectItem("BA", "Bahia"));
		listaEstados.add(new SelectItem("CE", "Cear�"));
		listaEstados.add(new SelectItem("DF", "Distrito Federal"));
		listaEstados.add(new SelectItem("ES", "Esp�rito Santo"));
		listaEstados.add(new SelectItem("GO", "Goi�s"));
		listaEstados.add(new SelectItem("MA", "Maranh�o"));
		listaEstados.add(new SelectItem("MT", "Mato Grosso"));
		listaEstados.add(new SelectItem("MS", "Mato Grosso do Sul"));
		listaEstados.add(new SelectItem("MG", "Minas Gerais"));
		listaEstados.add(new SelectItem("PA", "Par�"));
		listaEstados.add(new SelectItem("PB", "Para�ba"));
		listaEstados.add(new SelectItem("PR", "Paran�"));
		listaEstados.add(new SelectItem("PE", "Pernambuco"));
		listaEstados.add(new SelectItem("PI", "Piau�"));
		listaEstados.add(new SelectItem("RJ", "Rio de Janeiro"));
		listaEstados.add(new SelectItem("RN", "Rio Grande do Norte"));
		listaEstados.add(new SelectItem("RS", "Rio Grande do Sul"));
		listaEstados.add(new SelectItem("RO", "Rond�nia"));
		listaEstados.add(new SelectItem("RR", "Roraima"));
		listaEstados.add(new SelectItem("SC", "Santa Catarina"));
		listaEstados.add(new SelectItem("SP", "S�o Paulo"));
		listaEstados.add(new SelectItem("SE", "Sergipe"));
		listaEstados.add(new SelectItem("TO", "Tocantins"));
	}

	public ArrayList<SelectItem> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<SelectItem> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
	

}
