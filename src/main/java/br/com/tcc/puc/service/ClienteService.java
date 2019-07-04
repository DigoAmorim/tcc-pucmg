/**
 * 
 */
package br.com.tcc.puc.service;

import java.util.ArrayList;

import br.com.tcc.puc.dao.Dao;
import br.com.tcc.puc.dao.MockClienteDao;
import br.com.tcc.puc.model.Cliente;

/**
 * @author Rodrigo
 *
 *         Classe que implementar� os servi�os para manipula��o dos clientes.
 *         As regras de neg�cio ficar�o nessa classe.
 *
 */
public class ClienteService {

	static final String ACESSO_DADOS = "MOCK";

	private Dao<Cliente> clienteDao;
	
	public ClienteService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			clienteDao = new MockClienteDao();
		}
	}

	public void criar(Cliente cliente) {
		clienteDao.adicionarObjeto(cliente);
	}

	public void editar(Cliente cliente) {
		clienteDao.alterarObjeto(cliente);
	}

	public Cliente obter(Cliente cliente) {
		return clienteDao.obterObjeto(cliente);
	}

	public ArrayList<Cliente> obterTodos() {
		return clienteDao.obterTodos();
	}

	public void remover(Cliente cliente) {
		clienteDao.excluirObjeto(cliente);
	}
}
