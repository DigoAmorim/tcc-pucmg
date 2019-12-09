package br.com.tcc.puc.dao;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;

/**
 * @author Rodrigo
 *
 *         Classe que simula o acesso ao BD para testar as funcionalidades de
 *         gerenciamento do pagamento dos clientes.
 * 
 */
public class MockPagamentoDao {

	private ArrayList<Pagamento> pagamentos = null;

	public MockPagamentoDao() {
		pagamentos = new ArrayList<Pagamento>();

	}

	/**
	 * Método que retorna a retorna todos os clientes da lista.
	 */
	public ArrayList<Pagamento> obterTodos() {
		return pagamentos;
	}

	/**
	 * Método que recupera da lista de pagamentos de um cliente passado como
	 * parâmetro de busca.
	 */
	public ArrayList<Pagamento> obterObjeto(Cliente cli) {
		Pagamento pagamentoObj = null;
		ArrayList<Pagamento> pagamentosDoCliente = new ArrayList<Pagamento>();
		for (Iterator<Pagamento> iterator = pagamentos.iterator(); iterator.hasNext();) {
			pagamentoObj = iterator.next();
			if (pagamentoObj.getCliente().getCpf().equals(cli.getCpf())) {
				pagamentosDoCliente.add(pagamentoObj);
			}
		}
		return pagamentosDoCliente;
	}

	/**
	 * Método que adicionar um cliente passado como parâmetro a uma lista de
	 * clientes.
	 */
	public void adicionarObjeto(Pagamento pagamentoParam) {
		pagamentos.add(pagamentoParam);
	}

}
