/**
 * 
 */
package br.com.tcc.puc.service;

import java.util.ArrayList;
import java.util.Date;

import br.com.tcc.puc.dao.CrudDao;
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

	static final String ACESSO_DADOS = "MOCK";

	private UtilidadeService utilidadeService;
	private CrudDao<Cliente> clienteDao;

	public ClienteService(String tipoAcesso) {
		if (tipoAcesso.equals(ACESSO_DADOS)) {
			clienteDao = new MockClienteDao();
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

	public void criar(Cliente cliente) {
		// Ajustar a data do dia como data da matrícula do cliente
		cliente.setDtMatricula(new Date(System.currentTimeMillis()));
		instanciarUtilidadeService();
		cliente.setDescTpPlano(utilidadeService.obterDescTpPlano(cliente.getTpPlano()));
		clienteDao.adicionarObjeto(cliente);
	}

	public void editar(Cliente cliente) {
		instanciarUtilidadeService();
		cliente.setDescTpPlano(utilidadeService.obterDescTpPlano(cliente.getTpPlano()));
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
