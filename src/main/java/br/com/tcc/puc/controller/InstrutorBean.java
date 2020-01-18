package br.com.tcc.puc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.tcc.puc.exception.EntidadeDuplicadaException;
import br.com.tcc.puc.model.Instrutor;
import br.com.tcc.puc.service.InstrutorService;
import br.com.tcc.puc.service.UtilidadeService;
import br.com.tcc.puc.util.Utilidade;

@ManagedBean
@SessionScoped
/**
 * Bean respons�vel pelo controle das telas de manipula��o dos instrutores.
 * @author Rodrigo
 *
 */
public class InstrutorBean {

	private Instrutor instrutor = null;
	private String filtroInstrutorCpf = null;
	private ArrayList<Instrutor> instrutoresFiltrados = new ArrayList<Instrutor>();
	private boolean desabiltarCamposAlterarInstrutor = true;

	private InstrutorService instrutorService = null;
	private UtilidadeService utilService = null;

	/**
	 * Construtor do Bean
	 */
	public InstrutorBean() {
		instrutor = new Instrutor();
		setDesabiltarCamposAlterarInstrutor(true);
	}

	/**
	 * M�todo acionado no momento em que o usu�rio grava a cria��o de um instrutor
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String cadastrarInstrutor() {
		instanciarInstrutorService();
		try {
			instrutorService.criar(instrutor);
			Utilidade.retornarMensagem(Utilidade.getMessage("instrutorCadastradoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (EntidadeDuplicadaException e) {
			Utilidade.retornarMensagem(Utilidade.getMessage("instrutorMesmoCPFCadastrado", null), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			System.out.println("Erro precisa ser tratado");
		} finally {
			limparInstrutor();
			setDesabiltarCamposAlterarInstrutor(true);
		}
		return "Instrutor";
	}
	
	/**
	 * M�todo acionado no momento em que o usu�rio altera qualquer informa��o de um instrutor
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String alterarInstrutor() {
		instanciarInstrutorService();
		try {
			instrutorService.editar(instrutor);
			Utilidade.retornarMensagem(Utilidade.getMessage("instrutorAlteradoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Erro precisa ser tratado");
		} finally {
			limparInstrutor();
			setDesabiltarCamposAlterarInstrutor(true);
		}
		return "Instrutor";
	}
	
	/**
	 * M�todo acionado no momento em que o usu�rio remove um instrutor
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String excluirInstrutor() {
		instanciarInstrutorService();
		try {
			instrutorService.remover(instrutor);
			Utilidade.retornarMensagem(Utilidade.getMessage("instrutorExcluidoSucesso", null), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			System.out.println("Deu erro");
		} finally {
			limparInstrutor();
			setDesabiltarCamposAlterarInstrutor(true);
		}
		return "Instrutor";
	}

	/**
	 * Na tela de altera��o, uma das funcionalidade � obter o instrutor pelo identificador, que no caso � o CPF.
	 * @return Retorna a p�gina que dever� manter no view. No exemplo em quest�o, se manter� na mesma p�gina.
	 */
	public String obterInstrutorPorCpf() {
		Instrutor Instparam = new Instrutor();
		Instparam.setCpf(filtroInstrutorCpf);
		instanciarInstrutorService();
		setDesabiltarCamposAlterarInstrutor(true);
		instrutor = instrutorService.obter(Instparam);
		if (Objects.isNull(instrutor)) {
			Utilidade.retornarMensagem(Utilidade.getMessage("instrutorNaoEncontrado", null), FacesMessage.SEVERITY_WARN);
		} else {
			setFiltroInstrutorCpf("");
			setDesabiltarCamposAlterarInstrutor(false);
		}
		return "Instrutor";
	}

	/**
	 * TODO: Quando inserir o BD, modificar a implementa��o desse m�todo.
	 */
	private void limparInstrutor() {
		instrutor = new Instrutor();
		filtroInstrutorCpf = "";
//		instrutor.setCpf("");
//		instrutor.setNome("");
//		instrutor.setRg("");
	}

	/**
	 * M�todo para instanciar o servi�o de instrutores da aplica��o
	 */
	private void instanciarInstrutorService() {
		if (instrutorService == null) {
			instrutorService = new InstrutorService("DB");
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
	public ArrayList<Instrutor> getListaInstrutores() {
		instanciarInstrutorService();
		return instrutorService.obterTodos();
	}

	public List<SelectItem> getListaTpAtividades() {
		instanciarUtilidadeService();
		return utilService.obterTpAtividades();
	}

	public String getFiltroInstrutorCpf() {
		return filtroInstrutorCpf;
	}

	public void setFiltroInstrutorCpf(String filtroInstrutorCpf) {
		this.filtroInstrutorCpf = filtroInstrutorCpf;
	}

	public List<Instrutor> getInstrutoresFiltrados() {
		return instrutoresFiltrados;
	}

	public void setInstrutoresFiltrados(ArrayList<Instrutor> instrutoresFiltrados) {
		this.instrutoresFiltrados = instrutoresFiltrados;
	}

	public InstrutorService getInstrutorService() {
		return instrutorService;
	}

	public void setInstrutorService(InstrutorService instrutorService) {
		this.instrutorService = instrutorService;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public boolean isDesabiltarCamposAlterarInstrutor() {
		return desabiltarCamposAlterarInstrutor;
	}

	public void setDesabiltarCamposAlterarInstrutor(boolean desabiltarCamposAlterarInstrutor) {
		this.desabiltarCamposAlterarInstrutor = desabiltarCamposAlterarInstrutor;
	}
	
	/**
	 * M�todo que limpa os campos da tela.
	 * @return
	 */
	public String getLimparInstrutor() {
		instrutor = new Instrutor();
		return null;
	}
	
	/**
	 * M�todo que limpa os campos da tela.
	 * @return
	 */
	public void setLimparInstrutor() {
	}
}
