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

	/**
	 * Método que retornará os estados para serem preenchidos nos select items da tela
	 * @return Coleção contendo select items de estado a serem apresentado na tela
	 */
	public List<SelectItem> obterEstados() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaEstados();
		}
		return null;
	}

	/**
	 * Método que retornará os tipos de atividades para serem preenchidos nos select items da tela
	 * @return Coleção contendo select items tipos de atividades a serem apresentado na tela
	 */
	public List<SelectItem> obterTpAtividades() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaTpAtividades();
		}
		return null;
	}

	/**
	 * Método que retornará os tipos de planos para serem preenchidos nos select items da tela
	 * @return Coleção contendo select items tipos de planos a serem apresentado na tela
	 */
	public ArrayList<SelectItem> obterTpPlanos() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return utilMock.getListaTpPlanos();
		}
		return null;
	}

	/**
	 * Método que retorna a descrição de um plano com base no código escolhido pelo usuário na tela
	 * @param tpPlano - Código do plano escolhido pelo usuário na tela.
	 * @return Retorna uma string contendo a descrição do plano escolhido
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
