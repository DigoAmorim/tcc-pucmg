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

	public InstrutorService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			instrutorDao = new MockInstrutorDao();
		}
	}

	public void criar(Instrutor instrutor) {
		instrutorDao.adicionarObjeto(instrutor);
	}

	public void editar(Instrutor instrutor) {
		instrutorDao.alterarObjeto(instrutor);
	}

	public Instrutor obter(Instrutor instrutor) {
		return instrutorDao.obterObjeto(instrutor);
	}

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

	public void remover(Instrutor instrutor) {
		instrutorDao.excluirObjeto(instrutor);
	}
}
