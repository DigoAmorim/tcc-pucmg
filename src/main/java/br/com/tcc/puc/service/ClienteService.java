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
import br.com.tcc.puc.model.Cliente;

/**
 * @author Rodrigo
 *
 *         Classe que implementará os serviços para manipulação dos clientes. As
 *         regras de negócio ficarão nessa classe.
 *
 */
public class ClienteService {

	static final String ACESSO_MOCK = "MOCK";
	
	static final String ACESSO_DB = "DB";

	private UtilidadeService utilidadeService;
	
	private CrudDao<Cliente> clienteDao;

	/**
	 * Construtor da Classe
	 * @param tipoAcesso campo que indicará se o método chamará o Mock ou o banco de dados
	 */
	public ClienteService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_MOCK)) {
			clienteDao = new MockClienteDao();
		} else if (tipoAcesso.equals(ACESSO_DB)) { 
			clienteDao = new DBClienteDao();
		}
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
	 * Método que criará o cliente 
	 * @param cliente - Cliente a ser criado na aplicação
	 */
	public void criar(Cliente cliente) {
		// Ajustar a data do dia como data da matrícula do cliente
		cliente.setDtMatricula(new Date(System.currentTimeMillis()));
		instanciarUtilidadeService();
		cliente.setDescTpPlano(utilidadeService.obterDescTpPlano(cliente.getTpPlano()));
		clienteDao.adicionarObjeto(cliente);
	}

	/**
	 * Método que editará o cliente
	 * @param cliente - Cliente a ser modificado na aplicação
	 */
	public void editar(Cliente cliente) {
		instanciarUtilidadeService();
		cliente.setDescTpPlano(utilidadeService.obterDescTpPlano(cliente.getTpPlano()));
		clienteDao.alterarObjeto(cliente);
	}

	/**
	 * Método que obterá o cliente de um mock ou de um banco de dados
	 * @param cliente - Objeto que conterá os campos de filtragem do cliente
	 * @return Retorna o cliente obtido na pesquisa de um Mock ou de um banco de dados
	 */
	public Cliente obter(Cliente cliente) {
		return clienteDao.obterObjeto(cliente);
	}

	/**
	 * Método que obtem todos os clientes de um mock ou de um banco de dados
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
	 * Método que remove um cliente de um mock ou de um banco de dados
	 * @param cliente - Objeto que contém informações do cliente que deverá ser removido
	 */
	public void remover(Cliente cliente) {
		clienteDao.excluirObjeto(cliente);
	}
}
