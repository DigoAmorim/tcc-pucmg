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
 * Bean responsável pelo controle das telas de manipulação dos instrutores.
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
	 * Método acionado no momento em que o usuário grava a criação de um instrutor
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Método acionado no momento em que o usuário altera qualquer informação de um instrutor
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Método acionado no momento em que o usuário remove um instrutor
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * Na tela de alteração, uma das funcionalidade é obter o instrutor pelo identificador, que no caso é o CPF.
	 * @return Retorna a página que deverá manter no view. No exemplo em questão, se manterá na mesma página.
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
	 * TODO: Quando inserir o BD, modificar a implementação desse método.
	 */
	private void limparInstrutor() {
		instrutor = new Instrutor();
		filtroInstrutorCpf = "";
//		instrutor.setCpf("");
//		instrutor.setNome("");
//		instrutor.setRg("");
	}

	/**
	 * Método para instanciar o serviço de instrutores da aplicação
	 */
	private void instanciarInstrutorService() {
		if (instrutorService == null) {
			instrutorService = new InstrutorService("DB");
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
	 * Método que limpa os campos da tela.
	 * @return
	 */
	public String getLimparInstrutor() {
		instrutor = new Instrutor();
		return null;
	}
	
	/**
	 * Método que limpa os campos da tela.
	 * @return
	 */
	public void setLimparInstrutor() {
	}
}
