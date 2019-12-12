package br.com.tcc.puc.controller;

import java.util.ArrayList;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.tcc.puc.exception.PagAnteriorVencException;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pagamento;
import br.com.tcc.puc.service.ClienteService;
import br.com.tcc.puc.service.PagamentoService;
import br.com.tcc.puc.util.Utilidade;

@ManagedBean
@SessionScoped
/**
 * Bean respons�vel pelo controle das telas de manipula��o dos pagamentos dos
 * clientes.
 * 
 * @author Rodrigo
 *
 */
public class PagamentoBean {

	private Pagamento pagamento = null;
	private String filtroClienteCpf = null;
	private boolean desabiltarCamposRegistrarPagamento = true;
	private ArrayList<Pagamento> pagamentosFiltrados = new ArrayList<Pagamento>();

	private ClienteService clienteService = null;
	private PagamentoService pagamentoService = null;

	public PagamentoBean() {
		pagamento = new Pagamento();
	}

	/**
	 * Na tela de altera��o, uma das funcionalidade � obter o cliente pelo
	 * identificador, que no caso � o CPF.
	 * 
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se
	 *         manter� na mesma p�gina.
	 */
	public String obterClientePorCpf() {
		Cliente Cliparam = new Cliente();
		Cliparam.setCpf(filtroClienteCpf);
		instanciarClienteService();
		pagamento.setCliente(clienteService.obter(Cliparam));
		instanciarPagamentoService();
		pagamento.setCliente(pagamentoService.obterInfoFinanceira(pagamento.getCliente()));
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
	 * M�todo acionado no momento em que o usu�rio registra um pagamento
	 * 
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se
	 *         manter� na mesma p�gina.
	 */
	public String registrarPagamento() {
		instanciarPagamentoService();
		try {
			pagamentoService.criar(pagamento);
			Utilidade.retornarMensagem(Utilidade.getMessage("pagamentoRegistradoSucesso", null),
					FacesMessage.SEVERITY_INFO);
		} catch (PagAnteriorVencException e) {
			Utilidade.retornarMensagem(Utilidade.getMessage(e.getMessage(), null), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			System.out.println("Erro precisa ser tratado");
		} finally {
			limparPagamento();
			setDesabiltarCamposRegistrarPagamento(true);
		}
		return "Pagamento";
	}

	/**
	 * TODO: Quando inserir o BD, modificar a implementa��o desse m�todo.
	 */
	private void limparPagamento() {
		pagamento = new Pagamento();
		filtroClienteCpf = "";
	}

	/**
	 * M�todo para instanciar o servi�o de clientes da aplica��o
	 */
	private void instanciarClienteService() {
		if (clienteService == null) {
			clienteService = new ClienteService("MOCK");
		}
	}

	/**
	 * M�todo para instanciar o servi�o de pagamentos da aplica��o
	 */
	private void instanciarPagamentoService() {
		if (pagamentoService == null) {
			pagamentoService = new PagamentoService("MOCK");
		}
	}

	/**
	 * 
	 * M�todos Getters and Setters
	 * 
	 */
	public ArrayList<Pagamento> getListaPagamentos() {
		instanciarPagamentoService();
		return pagamentoService.obterTodos();
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

	public ArrayList<Pagamento> getPagamentosFiltrados() {
		return pagamentosFiltrados;
	}

	public void setPagamentosFiltrados(ArrayList<Pagamento> pagamentosFiltrados) {
		this.pagamentosFiltrados = pagamentosFiltrados;
	}

	public boolean isDesabiltarCamposRegistrarPagamento() {
		return desabiltarCamposRegistrarPagamento;
	}

	public void setDesabiltarCamposRegistrarPagamento(boolean desabiltarCamposRegistrarPagamento) {
		this.desabiltarCamposRegistrarPagamento = desabiltarCamposRegistrarPagamento;
	}

}
