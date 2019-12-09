package br.com.tcc.puc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;
import br.com.tcc.puc.service.ClienteService;
import br.com.tcc.puc.service.PagamentoService;
import br.com.tcc.puc.service.UtilidadeService;
import br.com.tcc.puc.util.Utilidade;

@ManagedBean
@SessionScoped
/**
 * Bean responsável pelo controle das telas de manipulação dos pagamentos dos clientes.
 * @author Rodrigo
 *
 */
public class PagamentoBean {

	private Pagamento pagamento = null;
	private String filtroClienteCpf = null;
	private boolean desabiltarCamposRegistrarPagamento = true;

	private ClienteService clienteService = null;
	private PagamentoService pagamentoService = null;

	public PagamentoBean() {
		pagamento = new Pagamento();
	}

	/**
	 * Na tela de alteração, uma das funcionalidade é obter o cliente pelo identificador, que no caso é o CPF.
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
	 */
	public String obterClientePorCpf() {
		Cliente Cliparam = new Cliente();
		Cliparam.setCpf(filtroClienteCpf);
		instanciarClienteService();
		pagamento.setCliente(clienteService.obter(Cliparam));
		instanciarPagamentoService();
		pagamento.setCliente(pagamentoService.obterInfoPagamento(pagamento.getCliente()));
		setDesabiltarCamposRegistrarPagamento(true);
		if (Objects.isNull(pagamento.getCliente())) {
			Utilidade.retornarMensagem(Utilidade.getMessage("clienteNaoEncontrado", null), FacesMessage.SEVERITY_WARN);
		} else {
			setFiltroClienteCpf("");
			setDesabiltarCamposRegistrarPagamento(false);
		}
		return "Pagamento";
	}
	
	/**
	 * Método acionado no momento em que o usuário registra um pagamento
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
	 */
	public String registrarPagamento() {
		instanciarPagamentoService();
		try {
			pagamentoService.criar(pagamento);
			Utilidade.retornarMensagem(Utilidade.getMessage("pagamentoRegistradoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Erro precisa ser tratado");
		} finally {
			limparPagamento();
			setDesabiltarCamposRegistrarPagamento(true);
		}
		return "Pagamento";
	}

	/**
	 * TODO: Quando inserir o BD, modificar a implementação desse método.
	 */
	private void limparPagamento() {
		pagamento = new Pagamento();
		filtroClienteCpf = "";
	}

	/**
	 * Método para instanciar o serviço de clientes da aplicação
	 */
	private void instanciarClienteService() {
		if (clienteService == null) {
			clienteService = new ClienteService("MOCK");
		}
	}
	
	/**
	 * Método para instanciar o serviço de pagamentos da aplicação
	 */
	private void instanciarPagamentoService() {
		if (pagamentoService == null) {
			pagamentoService = new PagamentoService("MOCK");
		}
	}

	/**
	 * 
	 * Métodos Getters and Setters
	 * 
	 */
	public ArrayList<Cliente> getListaClientes() {
		instanciarClienteService();
		return clienteService.obterTodos();
	}

	public String getFiltroClienteCpf() {
		return filtroClienteCpf;
	}

	public void setFiltroClienteCpf(String filtroClienteCpf) {
		this.filtroClienteCpf = filtroClienteCpf;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public boolean isDesabiltarCamposRegistrarPagamento() {
		return desabiltarCamposRegistrarPagamento;
	}

	public void setDesabiltarCamposRegistrarPagamento(boolean desabiltarCamposRegistrarPagamento) {
		this.desabiltarCamposRegistrarPagamento = desabiltarCamposRegistrarPagamento;
	}
	
}
