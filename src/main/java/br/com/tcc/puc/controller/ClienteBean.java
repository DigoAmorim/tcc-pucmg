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
 * Bean responsável pelo controle das telas de manipulação dos clientes.
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
	 * Método acionado no momento em que o usuário grava a criação de um cliente
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Método acionado no momento em que o usuário altera qualquer informação de um cliente
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Método acionado no momento em que o usuário remove um cliente
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Na tela de alteração, uma das funcionalidade é obter o cliente pelo identificador, que no caso é o CPF.
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Método que limpa campos na tela
	 */
	private void limparCliente() {
		cliente = new Cliente();
		filtroClienteCpf = "";
	}

	/**
	 * Método para instanciar o serviço de clientes da aplicação
	 */
	private void instanciarClienteService() {
		if (clienteService == null) {
			clienteService = new ClienteService("DB");
		}
	}

	/**
	 * Méotodo para instanciar o serviço de utilidades da aplicação.
	 */
	private void instanciarUtilidadeService() {
		if (utilService == null) {
			utilService = new UtilidadeService();
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
