/**
 * 
 */
package br.com.tcc.puc.service;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.tcc.puc.dao.CrudDao;
import br.com.tcc.puc.dao.MockInstrutorDao;
import br.com.tcc.puc.model.Instrutor;

/**
 * @author Rodrigo
 *
 *         Classe que implementar� os servi�os para manipula��o dos instrutores.
 *         As regras de neg�cio ficar�o nessa classe.
 *
 */
public class InstrutorService {

	static final String ACESSO_DADOS = "MOCK";

	private CrudDao<Instrutor> instrutorDao;
	
	/**
	 * Construtor da Classe
	 * @param tipoAcesso campo que indicar� se o m�todo chamar� o Mock ou o banco de dados
	 */
	public InstrutorService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			instrutorDao = new MockInstrutorDao();
		}
	}

	/**
	 * M�tod que criar� o instrutor 
	 * @param instrutor - Instrutor a ser criado na aplica��o
	 */
	public void criar(Instrutor instrutor) {
		instrutorDao.adicionarObjeto(instrutor);
	}

	/**
	 * M�todo que editar� o instrutor
	 * @param instrutor - Instrutor a ser modificado na aplica��o
	 */
	public void editar(Instrutor instrutor) {
		instrutorDao.alterarObjeto(instrutor);
	}

	/**
	 * M�todo que obter� o instrutor de um mock ou de um banco de dados
	 * @param instrutor - Objeto que conter� os campos de filtragem do instrutor
	 * @return Retorna o instrutor obtido na pesquisa de um Mock ou de um banco de dados
	 */
	public Instrutor obter(Instrutor instrutor) {
		return instrutorDao.obterObjeto(instrutor);
	}

	/**
	 * M�todo que obtem todos os instrutores de um mock ou de um banco de dados
	 * @return Retorna a lista de todos os instrutores obtidos
	 */
	public ArrayList<Instrutor> obterTodos() {
		ArrayList<Instrutor> instrutores = instrutorDao.obterTodos();
		Instrutor inst;
		for (Iterator<Instrutor> iterator = instrutores.iterator(); iterator.hasNext();) {
			inst = (Instrutor) iterator.next();
			boolean primeiroLista = true;
			for (String tipo : inst.getTpAtividade()) {
				if (primeiroLista) {
					inst.setTpAtividadeTxt(tipo);
					primeiroLista = false;
				} else {
					inst.setTpAtividadeTxt(inst.getTpAtividadeTxt() + ", " + tipo);
				}
			}

		}
		return instrutores;
	}

	/**
	 * M�todo que remove um cliente de um mock ou de um banco de dados
	 * @param cliente - Objeto que cont�m informa��es do cliente que dever� ser removido
	 */
	public void remover(Instrutor instrutor) {
		instrutorDao.excluirObjeto(instrutor);
	}
}
