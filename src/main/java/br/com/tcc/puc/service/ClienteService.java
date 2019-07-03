/**
 * 
 */
package br.com.tcc.puc.service;

import java.util.ArrayList;

import br.com.tcc.puc.mock.ClienteMock;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pessoa;

/**
 * @author Rodrigo
 *
 *         Classe que implementará os serviços para manipulação dos clientes.
 *
 */
public class ClienteService implements IPessoaService {

	static final String ACESSO_DADOS = "MOCK";

	private ClienteMock clienteMock = new ClienteMock();

	public void criar(Cliente cliente) {
		if (ACESSO_DADOS.equals("MOCK")) {
			clienteMock.setClientes(cliente);
		}
	}

	public void editar(Cliente cliente) {
		if (ACESSO_DADOS.equals("MOCK")) {
			clienteMock.alterarCliente(cliente);
		}
	}

	public Cliente obter(Object cpf) {
		if (ACESSO_DADOS.equals("MOCK")) {
			return clienteMock.obterCliente(cpf.toString());
		}
		return null;
	}

	public ArrayList<Cliente> obterTodos() {
		if (ACESSO_DADOS.equals("MOCK")) {
			return clienteMock.getClientes();
		}
		return null;
	}

	public void criar(Pessoa pessoa) {
		// TODO Auto-generated method stub

	}

	public void editar(Pessoa pessoa) {
		// TODO Auto-generated method stub

	}

	public void remover(Pessoa pessoa) {
		// TODO Auto-generated method stub
	}

	public void remover(Cliente cliente) {
		if (ACESSO_DADOS.equals("MOCK")) {
			clienteMock.excluirCliente(cliente);
		}
	}

	public ArrayList obterPessoas(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

}
