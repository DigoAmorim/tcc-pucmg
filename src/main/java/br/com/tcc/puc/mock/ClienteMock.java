package br.com.tcc.puc.mock;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pessoa;

/**
 * @author Rodrigo
 *
 *         Classe que simulará o acesso ao BD para testar as funcionalidades
 *         CRUD de Cliente.
 * 
 */
public class ClienteMock {

	private ArrayList<Cliente> clientes = null;

	public ClienteMock() {
		clientes = new ArrayList<Cliente>();
		popularClientes();

	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public Cliente obterCliente(String cpf) {
		Cliente clienteObj = null;
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if(clienteObj.getCpf().equals(cpf)) {
				return clienteObj;
			}
		}
		return null;
	}
	
	public void excluirCliente(Cliente clienteModificado) {
		Cliente clienteObj = null;
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if(clienteObj.getCpf().equals(clienteModificado.getCpf())) {
				iterator.remove();
			}
		}
	}
	
	public boolean alterarCliente(Cliente clienteModificado) {
		Cliente clienteObj;
		for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
			clienteObj = iterator.next();
			if(clienteObj.getCpf().equals(clienteModificado.getCpf())) {
				clienteObj.setCidade(clienteModificado.getCidade());
				clienteObj.setEndereco(clienteModificado.getEndereco());
				clienteObj.setEstado(clienteModificado.getEstado());
				clienteObj.setNome(clienteModificado.getNome());
				clienteObj.setRg(clienteModificado.getRg());
				return true;
			}
		}
		return false;
	}

	public void setClientes(Cliente clientesParam) {
		clientes.add(clientesParam);
	}

	private void popularClientes() {
		Cliente c = new Cliente();
		c.setNome("Rodrigo Amorim");
		c.setCpf("023.905.665-15");
		c.setEndereco("Rua Dr. Carlos Magalhães");
		c.setNumero(new Integer(23));
		c.setRg("054.052.69-00");
		c.setCidade("Salvador");
		clientes.add(c);
		c = new Cliente();
		c.setNome("João da Silva");
		c.setCpf("443.105.365-15");
		c.setEndereco("Av. Paralela");
		c.setNumero(new Integer(345));
		c.setRg("876.522.55-00");
		c.setCidade("Recife");
		clientes.add(c);
		c = new Cliente();
		c.setNome("Maria do Rosário");
		c.setCpf("223.875.332-65");
		c.setEndereco("Av. Atlântica");
		c.setNumero(new Integer(1234));
		c.setRg("332.001.55-70");
		c.setCidade("Porto Alegre");
		clientes.add(c);

	}

}
