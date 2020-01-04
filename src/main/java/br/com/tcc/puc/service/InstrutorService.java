/**
 * 
 */
package br.com.tcc.puc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import br.com.tcc.puc.dao.CrudDao;
import br.com.tcc.puc.dao.DBInstrutorDao;
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

	static final String ACESSO_DB = "DB";

	private UtilidadeService utilidadeService;

	private CrudDao<Instrutor> instrutorDao;

	/**
	 * Construtor da Classe
	 * 
	 * @param tipoAcesso campo que indicar� se o m�todo chamar� o Mock ou o banco de
	 *                   dados
	 */
	public InstrutorService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			instrutorDao = new MockInstrutorDao();
		} else if (tipoAcesso.equals(ACESSO_DB)) {
			instrutorDao = new DBInstrutorDao();
		}
	}

	/**
	 * M�tod que criar� o instrutor
	 * 
	 * @param instrutor - Instrutor a ser criado na aplica��o
	 */
	public void criar(Instrutor instrutor) {

		instrutor = marcaCheckPeloArray(instrutor);
		instrutorDao.adicionarObjeto(instrutor);
	}

	/**
	 * M�todo que editar� o instrutor
	 * 
	 * @param instrutor - Instrutor a ser modificado na aplica��o
	 */
	public void editar(Instrutor instrutor) {
		instrutor = marcaCheckPeloArray(instrutor);
		instrutorDao.alterarObjeto(instrutor);
	}

	/**
	 * M�todo que obter� o instrutor de um mock ou de um banco de dados
	 * 
	 * @param instrutor - Objeto que conter� os campos de filtragem do instrutor
	 * @return Retorna o instrutor obtido na pesquisa de um Mock ou de um banco de
	 *         dados
	 */
	public Instrutor obter(Instrutor instrutor) {
		Instrutor inst = instrutorDao.obterObjeto(instrutor);

		if (!Objects.isNull(inst)) {
			inst.setTpAtividade(montaAtividadeComCodigo(inst));
		}
		return inst;
	}

	/**
	 * M�todo que obtem todos os instrutores de um mock ou de um banco de dados
	 * 
	 * @return Retorna a lista de todos os instrutores obtidos
	 */
	public ArrayList<Instrutor> obterTodos() {
		Instrutor inst;
		ArrayList<Instrutor> instrutores = instrutorDao.obterTodos();

		for (Iterator<Instrutor> iterator = instrutores.iterator(); iterator.hasNext();) {
			inst = (Instrutor) iterator.next();
			inst.setTpAtividade(montaAtividadeComCodigo(inst));

			instanciarUtilidadeService();

			boolean primeiroLista = true;
			for (String tipo : inst.getTpAtividade()) {
				if (!tipo.isEmpty()) {
					if (primeiroLista) {
						inst.setTpAtividadeTxt(utilidadeService.obterDescTpAtividade(tipo));
						primeiroLista = false;
					} else {
						inst.setTpAtividadeTxt(
								inst.getTpAtividadeTxt() + ", " + utilidadeService.obterDescTpAtividade(tipo));
					}
				}
			}
		}
		return instrutores;
	}

	/**
	 * M�otodo para instanciar o servi�o de utilidades da aplica��o.
	 */
	private void instanciarUtilidadeService() {
		if (utilidadeService == null) {
			utilidadeService = new UtilidadeService();
		}
	}

	/**
	 * M�todo que monta o array para apresenta��o dos dados na tela.
	 * 
	 * @param inst: Objetos instrutor que precisa ter seu array montado.
	 * @return Array montado para apresenta��o na tela.
	 */
	private String[] montaAtividadeComCodigo(Instrutor inst) {
		String[] tpAtividade = { "", "" };

		// Preparar os campos de checkbox para recupera��o no banco.
		if (inst.isChkAulaGrupo()) {
			tpAtividade[0] = "01";
		}
		if (inst.isChkAulaMuscu()) {
			tpAtividade[1] = "02";
		}
		return tpAtividade;
	}

	/**
	 * Marca os campos booleanos de acordo os valores marcados na tela
	 * 
	 * @param inst: Instrutor com os valores marcados na tela
	 * @return Retorna os mesmos instrutores, s� que com os campos booleanos
	 *         preenchidos.
	 */
	private Instrutor marcaCheckPeloArray(Instrutor inst) {
		// Preparar os campos de checkbox para inser��o no banco.
		inst.setChkAulaGrupo(false);
		inst.setChkAulaMuscu(false);
		
		for (int i = 0; i < inst.getTpAtividade().length; i++) {
			if (inst.getTpAtividade()[i].equals("01")) {
				// Se o 01 tiver marcado, indica que o professor d� aula em grupo
				inst.setChkAulaGrupo(true);
			} else if (inst.getTpAtividade()[i].equals("02")) {
				// Se o 02 tiver marcado, indica que o professor d� aula de muscula��o
				inst.setChkAulaMuscu(true);
			}
		}
		return inst;
	}

	/**
	 * M�todo que remove um cliente de um mock ou de um banco de dados
	 * 
	 * @param cliente - Objeto que cont�m informa��es do cliente que dever� ser
	 *                removido
	 */
	public void remover(Instrutor instrutor) {
		instrutorDao.excluirObjeto(instrutor);
	}
}
