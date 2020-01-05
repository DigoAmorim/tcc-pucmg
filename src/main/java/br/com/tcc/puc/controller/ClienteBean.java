package br.com.tcc.puc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.tcc.puc.exception.EntidadeDuplicadaException;
import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.service.ClienteService;
import br.com.tcc.puc.service.UtilidadeService;
import br.com.tcc.puc.util.Utilidade;

@ManagedBean
@SessionScoped
/**
 * Bean respons�vel pelo controle das telas de manipula��o dos clientes.
 * @author Rodrigo
 *
 */
public class ClienteBean {

	private Cliente cliente = null;
	private String filtroClienteCpf = null;
	private ArrayList<Cliente> clientesFiltrados = new ArrayList<Cliente>();
	private boolean desabiltarCamposAlterarCliente = true;

	private ClienteService clienteService = null;
	private UtilidadeService utilService = null;

	/**
	 * Construtor do bean
	 */
	public ClienteBean() {
		cliente = new Cliente();
		setDesabiltarCamposAlterarCliente(true);
	}

	/**
	 * M�todo acionado no momento em que o usu�rio grava a cria��o de um cliente
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String cadastrarCliente() {
		instanciarClienteService();
		try {
			clienteService.criar(cliente);
			Utilidade.retornarMensagem(Utilidade.getMessage("clienteCadastradoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (EntidadeDuplicadaException e) {
			Utilidade.retornarMensagem(Utilidade.getMessage("clienteMesmoCPFCadastrado", null), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			System.out.println("Erro precisa ser tratado");
		} finally {
			limparCliente();
			setDesabiltarCamposAlterarCliente(true);
		}
		return "Cliente";
	}
	
	/**
	 * M�todo acionado no momento em que o usu�rio altera qualquer informa��o de um cliente
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String alterarCliente() {
		instanciarClienteService();
		try {
			clienteService.editar(cliente);
			Utilidade.retornarMensagem(Utilidade.getMessage("clienteAlteradoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Erro precisa ser tratado");
		} finally {
			limparCliente();
			setDesabiltarCamposAlterarCliente(true);
		}
		return "Cliente";
	}
	
	/**
	 * M�todo acionado no momento em que o usu�rio remove um cliente
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String excluirCliente() {
		instanciarClienteService();
		try {
			clienteService.remover(cliente);
			Utilidade.retornarMensagem(Utilidade.getMessage("clienteExcluidoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Deu erro");
		} finally {
			limparCliente();
			setDesabiltarCamposAlterarCliente(true);
		}
		return "Cliente";
	}

	/**
	 * Na tela de altera��o, uma das funcionalidade � obter o cliente pelo identificador, que no caso � o CPF.
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String obterClientePorCpf() {
		Cliente Cliparam = new Cliente();
		Cliparam.setCpf(filtroClienteCpf);
		instanciarClienteService();
		setDesabiltarCamposAlterarCliente(true);
		cliente = clienteService.obter(Cliparam);
		if (Objects.isNull(cliente)) {
			Utilidade.retornarMensagem(Utilidade.getMessage("clienteNaoEncontrado", null), FacesMessage.SEVERITY_WARN);
		} else {
			setFiltroClienteCpf("");
			setDesabiltarCamposAlterarCliente(false);
		}
		return "Cliente";
	}

	/**
	 * M�todo que limpa campos na tela
	 */
	private void limparCliente() {
		cliente = new Cliente();
		filtroClienteCpf = "";
	}

	/**
	 * M�todo para instanciar o servi�o de clientes da aplica��o
	 */
	private void instanciarClienteService() {
		if (clienteService == null) {
			clienteService = new ClienteService("DB");
		}
	}

	/**
	 * M�otodo para instanciar o servi�o de utilidades da aplica��o.
	 */
	private void instanciarUtilidadeService() {
		if (utilService == null) {
			utilService = new UtilidadeService();
		}
	}

	/**
	 * 
	 * M�todos Getters and Setters
	 * 
	 */
	public ArrayList<Cliente> getListaClientes() {
		instanciarClienteService();
		return clienteService.obterTodos();
	}

	public List<SelectItem> getListaEstados() {
		instanciarUtilidadeService();
		return utilService.obterEstados();
	}
	
	public List<SelectItem> getListaTpPlanos() {
		instanciarUtilidadeService();
		return utilService.obterTpPlanos();
	}

	public String getFiltroClienteCpf() {
		return filtroClienteCpf;
	}

	public void setFiltroClienteCpf(String filtroClienteCpf) {
		this.filtroClienteCpf = filtroClienteCpf;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(ArrayList<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isDesabiltarCamposAlterarCliente() {
		return desabiltarCamposAlterarCliente;
	}

	public void setDesabiltarCamposAlterarCliente(boolean desabiltarCamposAlterarCliente) {
		this.desabiltarCamposAlterarCliente = desabiltarCamposAlterarCliente;
	}
}
