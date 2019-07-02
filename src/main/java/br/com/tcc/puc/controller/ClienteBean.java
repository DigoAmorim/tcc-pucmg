package br.com.tcc.puc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.tcc.puc.model.Cliente;
import br.com.tcc.puc.model.Pessoa;
import br.com.tcc.puc.service.ClienteService;
import br.com.tcc.puc.service.UtilidadeService;
import br.com.tcc.puc.util.Utilidade;

@ManagedBean
@SessionScoped
public class ClienteBean {
	
	private Cliente cliente = null;
	private ArrayList<Cliente> clientesFiltrados = new ArrayList<Cliente>();
	
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

	private ClienteService clienteService = null;
	
	private UtilidadeService utilService = null;
	
	public ClienteBean() {
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String cadastrarClienter() {
		instanciarClienteService();
		try {
			clienteService.criar(cliente);
			retornarMensagem(Utilidade.getMessage("clienteCadastradoSucesso", null), FacesMessage.SEVERITY_INFO);
			clienteService.obterTodos();
		} catch (Exception e) {
			System.out.println("Deu erro");
		} finally {
			limparCliente();
		}
		return "Cliente";
	}
	
	/**
	 * Quando inserir o BD, modificar a implementação desse método.
	 */
	private void limparCliente() {
		cliente = new Cliente();
//		cliente.setCidade("");
//		cliente.setCpf("");
//		cliente.setEndereco("");
//		cliente.setNome("");
//		cliente.setNumero(null);
//		cliente.setRg("");
//		cliente.setTelefone("");
	}
	
	private void instanciarClienteService() {
		if (clienteService == null) {
			clienteService = new ClienteService();
		}
	}
	
	private void instanciarUtilidadeService() {
		if (utilService == null) {
			utilService = new UtilidadeService();
		}
	}
	
	private void retornarMensagem(String mensagem, Severity severidade) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (severidade.equals(FacesMessage.SEVERITY_INFO)) {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem,  "") );
		} else if (severidade.equals(FacesMessage.SEVERITY_WARN)) {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem,  "") );
		} else {
			contexto.addMessage(null, new FacesMessage(severidade, mensagem,  "") );
		}
	}
	
	public ArrayList<Pessoa> getListaClientes() {
		instanciarClienteService();
		return clienteService.obterTodos();
	}
	
	public List<SelectItem> getListaEstados() {
		instanciarUtilidadeService();
		return utilService.obterEstados();
	}
	

}
