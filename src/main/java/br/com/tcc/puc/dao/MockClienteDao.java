package br.com.tcc.puc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import br.com.tcc.puc.model.Cliente;

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidades
 *         CRUD de Cliente.
 * 
 */
public class MockClienteDao implements CrudDao<Cliente> {

	private ArrayList<Cliente> clientes = null;

	/**
	 * Construtor do DAO
	 */
	public MockClienteDao() {
		clientes = new ArrayList<Cliente>();
		popularClientes();

	}

	/**
	 * Método que retorna a retorna todos os clientes da lista.
	 */
	public ArrayList<Cliente> obterTodos() {
		return clientes;
	}

	/**
	 * Método que recupera da lista um cliente passado como parâmetro de busca.
	 */
	public Cliente obterObjeto(Cliente cli) {
		Cliente clienteObj = null;
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if(clienteObj.getCpf().equals(cli.getCpf())) {
				return clienteObj;
			}
		}
		return null;
	}
	
	/**
	 * Método que exclui da lista um cliente passado como parâmetro.
	 */
	public void excluirObjeto(Cliente clienteModificado) {
		Cliente clienteObj = null;
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if(clienteObj.getCpf().equals(clienteModificado.getCpf())) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Método que altera um cliente passado como parâmetro.  
	 */
	public void alterarObjeto(Cliente clienteModificado) {
		Cliente clienteObj;
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if(clienteObj.getCpf().equals(clienteModificado.getCpf())) {
				clienteObj.setCidade(clienteModificado.getCidade());
				clienteObj.setEndereco(clienteModificado.getEndereco());
				clienteObj.setEstado(clienteModificado.getEstado());
				clienteObj.setNome(clienteModificado.getNome());
				clienteObj.setRg(clienteModificado.getRg());
			}
		}
	}

	/**
	 * Método que adicionar um cliente passado como parâmetro a uma lista de clientes.
	 */
	public void adicionarObjeto(Cliente clientesParam) {
		clientes.add(clientesParam);
	}

	/**
	 * Método que popula a lista com clientes. 
	 */
	private void popularClientes() {
		Cliente c = new Cliente();
		c.setNome("Rodrigo Amorim");
		c.setCpf("023.905.665-15");
		c.setEndereco("Rua Dr. Carlos Magalhães");
		c.setNumero(new Integer(23));
		c.setRg("054.052.69-00");
		c.setCidade("Salvador");
		c.setTpPlano("30");
		c.setDescTpPlano("Pagamento Mensal");
		c.setDtMatricula(new Date(System.currentTimeMillis()));
		clientes.add(c);
		c = new Cliente();
		c.setNome("João da Silva");
		c.setCpf("443.105.365-15");
		c.setEndereco("Av. Paralela");
		c.setNumero(new Integer(345));
		c.setRg("876.522.55-00");
		c.setCidade("Recife");
		c.setTpPlano("30");
		c.setDescTpPlano("Pagamento Mensal");
		c.setDtMatricula(new Date(System.currentTimeMillis()));
		clientes.add(c);
		c = new Cliente();
		c.setNome("Maria do Rosário");
		c.setCpf("223.875.332-65");
		c.setEndereco("Av. Atlântica");
		c.setNumero(new Integer(1234));
		c.setRg("332.001.55-70");
		c.setCidade("Porto Alegre");
		c.setTpPlano("365");
		c.setDescTpPlano("Pagamento Anual");
		c.setDtMatricula(new Date(System.currentTimeMillis()));
		clientes.add(c);
	}
}
