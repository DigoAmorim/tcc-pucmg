package br.com.tcc.puc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;

import br.com.tcc.puc.dao.UtilMock;

/**
 * @author Rodrigo
 *
 *         Classe que implementará os serviços para manipulação dos campos úteis
 *         (gerais) do sistema.
 *
 */
public class UtilidadeService {

	static final String ACESSO_DADOS = "MOCK";

	private UtilMock utilMock = new UtilMock();

	public List<SelectItem> obterEstados() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaEstados();
		}
		return null;
	}

	public List<SelectItem> obterTpAtividades() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaTpAtividades();
		}
		return null;
	}

	public ArrayList<SelectItem> obterTpPlanos() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaTpPlanos();
		}
		return null;
	}

	public String obterDescTpPlano(String tpPlano) {
		if (ACESSO_DADOS.equals("MOCK")) {
			ArrayList<SelectItem> listaTpPlano;
			SelectItem valorSelectItem;

			listaTpPlano = obterTpPlanos();
			for (Iterator<SelectItem> iterator = listaTpPlano.iterator(); iterator.hasNext();) {
				valorSelectItem = iterator.next();
				if (tpPlano.equals(valorSelectItem.getValue().toString())) {
					return valorSelectItem.getLabel();
				}
			}
		}
		return null;
	}

}
