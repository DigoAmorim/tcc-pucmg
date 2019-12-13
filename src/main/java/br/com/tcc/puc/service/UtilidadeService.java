package br.com.tcc.puc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;

import br.com.tcc.puc.dao.UtilMock;

/**
 * @author Rodrigo
 *
 *         Classe que implementar� os servi�os para manipula��o dos campos �teis
 *         (gerais) do sistema.
 *
 */
public class UtilidadeService {

	static final String ACESSO_DADOS = "MOCK";

	private UtilMock utilMock = new UtilMock();

	/**
	 * M�todo que retornar� os estados para serem preenchidos nos select items da tela
	 * @return Cole��o contendo select items de estado a serem apresentado na tela
	 */
	public List<SelectItem> obterEstados() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaEstados();
		}
		return null;
	}

	/**
	 * M�todo que retornar� os tipos de atividades para serem preenchidos nos select items da tela
	 * @return Cole��o contendo select items tipos de atividades a serem apresentado na tela
	 */
	public List<SelectItem> obterTpAtividades() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaTpAtividades();
		}
		return null;
	}

	/**
	 * M�todo que retornar� os tipos de planos para serem preenchidos nos select items da tela
	 * @return Cole��o contendo select items tipos de planos a serem apresentado na tela
	 */
	public ArrayList<SelectItem> obterTpPlanos() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaTpPlanos();
		}
		return null;
	}

	/**
	 * M�todo que retorna a descri��o de um plano com base no c�digo escolhido pelo usu�rio na tela
	 * @param tpPlano - C�digo do plano escolhido pelo usu�rio na tela.
	 * @return Retorna uma string contendo a descri��o do plano escolhido
	 */
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
