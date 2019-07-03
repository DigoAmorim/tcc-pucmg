package br.com.tcc.puc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.tabview.TabView;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pessoa;
import br.com.tcc.puc.service.ClienteService;
import br.com.tcc.puc.service.UtilidadeService;
import br.com.tcc.puc.util.Utilidade;

@ManagedBean
@SessionScoped
public class ClienteBean {

	private Cliente cliente = null;

	private String filtroClienteCpf = null;
	
	private boolean desabiltarCamposAlterarCliente = true;

	private ArrayList<Cliente> clientesFiltrados = new ArrayList<Cliente>();

	private ClienteService clienteService = null;

	private UtilidadeService utilService = null;

	public ClienteBean() {
		cliente = new Cliente();
		setDesabiltarCamposAlterarCliente(true);
	}

	public String cadastrarCliente() {
		instanciarClienteService();
		try {
			clienteService.criar(cliente);
			retornarMensagem(Utilidade.getMessage("clienteCadastradoSucesso", null), FacesMessage.SEVERITY_INFO);
			clienteService.obterTodos();
		} catch (Exception e) {
			System.out.println("Deu erro");
		} finally {
			limparCliente();
			setDesabiltarCamposAlterarCliente(true);
		}
		return "Cliente";
	}
	
	public String alterarCliente() {
		instanciarClienteService();
		try {
			clienteService.editar(cliente);
			retornarMensagem(Utilidade.getMessage("clienteAlteradoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Deu erro");
		} finally {
			limparCliente();
			setDesabiltarCamposAlterarCliente(true);
		}
		return "Cliente";
	}
	
	public String excluirCliente() {
		instanciarClienteService();
		try {
			clienteService.remover(cliente);
			retornarMensagem(Utilidade.getMessage("clienteExcluidoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Deu erro");
		} finally {
			limparCliente();
			setDesabiltarCamposAlterarCliente(true);
		}
		return "Cliente";
	}

	public String obterClientePorCpf() {
		setDesabiltarCamposAlterarCliente(true);
		instanciarClienteService();
		cliente = clienteService.obter(filtroClienteCpf);
		/**
		 * TODO: Verificar depois uma maneira para melhor testar a nulidade.
		 */
		if (cliente == null) {
			retornarMensagem(Utilidade.getMessage("clienteNaoEncontrado", null), FacesMessage.SEVERITY_WARN);
		} else {
			setFiltroClienteCpf("");
			setDesabiltarCamposAlterarCliente(false);
		}
		return "Cliente";
	}

	/**
	 * TODO: Quando inserir o BD, modificar a implementação desse método.
	 */
	private void limparCliente() {
		cliente = new Cliente();
		filtroClienteCpf = "";
//		cliente.setCidade("");
//		cliente.setCpf("");
//		cliente.setEndereco("");
//		cliente.setNome("");
//		cliente.setNumero(null);
//		cliente.setRg("");
//		cliente.setTelefone("");
	}

	/**
	 * Método para instanciar o serviço de clientes da aplicação
	 */
	private void instanciarClienteService() {
		if (clienteService == null) {
			clienteService = new ClienteService();
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
	 * Méotodo que adiciona mensagens de retorno ao view.
	 * 
	 * @param mensagem:   Texto da mensagem
	 * @param severidade: Tipo da severidade
	 */
	private void retornarMensagem(String mensagem, Severity severidade) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (severidade.equals(FacesMessage.SEVERITY_INFO)) {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem, ""));
		} else if (severidade.equals(FacesMessage.SEVERITY_WARN)) {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem, ""));
		} else {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem, ""));
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
