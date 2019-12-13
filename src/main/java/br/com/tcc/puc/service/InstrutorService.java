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
 *         Classe que implementará os serviços para manipulação dos instrutores.
 *         As regras de negócio ficarão nessa classe.
 *
 */
public class InstrutorService {

	static final String ACESSO_DADOS = "MOCK";

	private CrudDao<Instrutor> instrutorDao;
	
	/**
	 * Construtor da Classe
	 * @param tipoAcesso campo que indicará se o método chamará o Mock ou o banco de dados
	 */
	public InstrutorService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			instrutorDao = new MockInstrutorDao();
		}
	}

	/**
	 * Métod que criará o instrutor 
	 * @param instrutor - Instrutor a ser criado na aplicação
	 */
	public void criar(Instrutor instrutor) {
		instrutorDao.adicionarObjeto(instrutor);
	}

	/**
	 * Método que editará o instrutor
	 * @param instrutor - Instrutor a ser modificado na aplicação
	 */
	public void editar(Instrutor instrutor) {
		instrutorDao.alterarObjeto(instrutor);
	}

	/**
	 * Método que obterá o instrutor de um mock ou de um banco de dados
	 * @param instrutor - Objeto que conterá os campos de filtragem do instrutor
	 * @return Retorna o instrutor obtido na pesquisa de um Mock ou de um banco de dados
	 */
	public Instrutor obter(Instrutor instrutor) {
		return instrutorDao.obterObjeto(instrutor);
	}

	/**
	 * Método que obtem todos os instrutores de um mock ou de um banco de dados
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
	 * Método que remove um cliente de um mock ou de um banco de dados
	 * @param cliente - Objeto que contém informações do cliente que deverá ser removido
	 */
	public void remover(Instrutor instrutor) {
		instrutorDao.excluirObjeto(instrutor);
	}
}
