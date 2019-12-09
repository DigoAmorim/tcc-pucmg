package br.com.tcc.puc.model;

/**
 * @author Rodrigo
 * 
 *  Essa classe visa facilitar futuras extensões de novos modelos.
 *  Pela solicitação inicial do TCC, as duas entidades que implementaremos o CRUD possuem os campos descritos abaixo.
 *
 */
public abstract class Pessoa {

	protected String nome = null;
	protected String rg = null;
	protected String cpf = null;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
