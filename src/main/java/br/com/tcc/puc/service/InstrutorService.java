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
 *         Classe que implementará os serviços para manipulação dos instrutores.
 *         As regras de negócio ficarão nessa classe.
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
	 * @param tipoAcesso campo que indicará se o método chamará o Mock ou o banco de
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
	 * Métod que criará o instrutor
	 * 
	 * @param instrutor - Instrutor a ser criado na aplicação
	 */
	public void criar(Instrutor instrutor) {

		instrutor = marcaCheckPeloArray(instrutor);
		instrutorDao.adicionarObjeto(instrutor);
	}

	/**
	 * Método que editará o instrutor
	 * 
	 * @param instrutor - Instrutor a ser modificado na aplicação
	 */
	public void editar(Instrutor instrutor) {
		instrutor = marcaCheckPeloArray(instrutor);
		instrutorDao.alterarObjeto(instrutor);
	}

	/**
	 * Método que obterá o instrutor de um mock ou de um banco de dados
	 * 
	 * @param instrutor - Objeto que conterá os campos de filtragem do instrutor
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
	 * Método que obtem todos os instrutores de um mock ou de um banco de dados
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
	 * Méotodo para instanciar o serviço de utilidades da aplicação.
	 */
	private void instanciarUtilidadeService() {
		if (utilidadeService == null) {
			utilidadeService = new UtilidadeService();
		}
	}

	/**
	 * Método que monta o array para apresentação dos dados na tela.
	 * 
	 * @param inst: Objetos instrutor que precisa ter seu array montado.
	 * @return Array montado para apresentação na tela.
	 */
	private String[] montaAtividadeComCodigo(Instrutor inst) {
		String[] tpAtividade = { "", "" };

		// Preparar os campos de checkbox para recuperação no banco.
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
	 * @return Retorna os mesmos instrutores, só que com os campos booleanos
	 *         preenchidos.
	 */
	private Instrutor marcaCheckPeloArray(Instrutor inst) {
		// Preparar os campos de checkbox para inserção no banco.
		inst.setChkAulaGrupo(false);
		inst.setChkAulaMuscu(false);
		
		for (int i = 0; i < inst.getTpAtividade().length; i++) {
			if (inst.getTpAtividade()[i].equals("01")) {
				// Se o 01 tiver marcado, indica que o professor dá aula em grupo
				inst.setChkAulaGrupo(true);
			} else if (inst.getTpAtividade()[i].equals("02")) {
				// Se o 02 tiver marcado, indica que o professor dá aula de musculação
				inst.setChkAulaMuscu(true);
			}
		}
		return inst;
	}

	/**
	 * Método que remove um cliente de um mock ou de um banco de dados
	 * 
	 * @param cliente - Objeto que contém informações do cliente que deverá ser
	 *                removido
	 */
	public void remover(Instrutor instrutor) {
		instrutorDao.excluirObjeto(instrutor);
	}
}
