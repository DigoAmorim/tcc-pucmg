/**
 * 
 */
package br.com.tcc.puc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import br.com.tcc.puc.dao.CrudDao;
import br.com.tcc.puc.dao.DBClienteDao;
import br.com.tcc.puc.dao.MockClienteDao;
import br.com.tcc.puc.exception.PlanoAtivoException;
import br.com.tcc.puc.exception.PossuiPagamentoException;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;

/**
 * @author Rodrigo
 *
 *         Classe que implementar� os servi�os para manipula��o dos clientes. As
 *         regras de neg�cio ficar�o nessa classe.
 *
 */
public class ClienteService {

	static final String ACESSO_MOCK = "MOCK";

	static final String ACESSO_DB = "DB";

	private UtilidadeService utilidadeService;

	private PagamentoService pagamentoService;

	private CrudDao<Cliente> clienteDao;

	/**
	 * Construtor da Classe
	 * 
	 * @param tipoAcesso campo que indicar� se o m�todo chamar� o Mock ou o banco de
	 *                   dados
	 */
	public ClienteService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_MOCK)) {
			clienteDao = new MockClienteDao();
		} else if (tipoAcesso.equals(ACESSO_DB)) {
			clienteDao = new DBClienteDao();
		}
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
	 * M�otodo para instanciar o servi�o de pagamento da aplica��o.
	 */
	private void instanciarPagamentoService() {
		if (pagamentoService == null) {
			pagamentoService = new PagamentoService(ACESSO_DB);
		}
	}

	/**
	 * M�todo que criar� o cliente
	 * 
	 * @param cliente - Cliente a ser criado na aplica��o
	 */
	public void criar(Cliente cliente) {
		// Ajustar a data do dia como data da matr�cula do cliente
		cliente.setDtMatricula(new Date(System.currentTimeMillis()));
		instanciarUtilidadeService();
		cliente.setDescTpPlano(utilidadeService.obterDescTpPlano(cliente.getTpPlano()));
		clienteDao.adicionarObjeto(cliente);
	}

	/**
	 * M�todo que editar� o cliente
	 * @param cliente - Cliente a ser modificado na aplica��o
	 */
	public void editar(Cliente cliente) throws PlanoAtivoException {
		Cliente clienteOld = new Cliente();

		// Tratamento caso haja mudan�as no tipo de plano
		clienteOld.setCpf(cliente.getCpf());
		clienteOld = obter(clienteOld);
		if(!cliente.getTpPlano().equals(clienteOld.getTpPlano())) {
			instanciarPagamentoService();
			clienteOld = pagamentoService.obterInfoFinanceira(clienteOld);
			if ("Adimplente".equals(clienteOld.getSitCliente())) {
				// Levantar erro impossibilitando a mudan�a, pois o cliente j� possui um plano ativo.
				throw new PlanoAtivoException("clienteComPlanoAtivo");
			}
		}
		instanciarUtilidadeService();
		cliente.setDescTpPlano(utilidadeService.obterDescTpPlano(cliente.getTpPlano()));
		clienteDao.alterarObjeto(cliente);
	}

	/**
	 * M�todo que obter� o cliente de um mock ou de um banco de dados
	 * 
	 * @param cliente - Objeto que conter� os campos de filtragem do cliente
	 * @return Retorna o cliente obtido na pesquisa de um Mock ou de um banco de
	 *         dados
	 */
	public Cliente obter(Cliente cliente) {
		return clienteDao.obterObjeto(cliente);
	}

	/**
	 * M�todo que obtem todos os clientes de um mock ou de um banco de dados
	 * 
	 * @return Retorna a lista de todos os clientes obtidos
	 */
	public ArrayList<Cliente> obterTodos() {

		ArrayList<Cliente> clientes;
		Cliente clienteObj;
		clientes = clienteDao.obterTodos();

		instanciarUtilidadeService();

		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			clienteObj.setDescTpPlano(utilidadeService.obterDescTpPlano(clienteObj.getTpPlano()));
		}

		return clientes;
	}

	/**
	 * M�todo que remove um cliente de um mock ou de um banco de dados
	 * 
	 * @param cliente - Objeto que cont�m informa��es do cliente que dever� ser
	 *                removido
	 */
	public void remover(Cliente cliente) throws PossuiPagamentoException {
		ArrayList<Pagamento> pagamentosDoCliente;
		
		// S� pode excluir cliente, caso ele n�o tenha feito ainda nenhum pagamento � academia
		instanciarPagamentoService();
		pagamentosDoCliente = pagamentoService.obterPagamentos(cliente);
		if (pagamentosDoCliente.size() > 0) {
			throw new PossuiPagamentoException("clientePossuiPagamento");
		}
		clienteDao.excluirObjeto(cliente);
	}
}
