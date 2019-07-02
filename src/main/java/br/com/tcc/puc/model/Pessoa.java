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
	protected String telefone = null;
	protected String cpf = null;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
