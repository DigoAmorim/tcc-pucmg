package br.com.tcc.puc.model;

import java.io.Serializable;

/**
 * Essa classe representará o cliente que será armazenado em nossa aplicação. Ele herda os campos de Pessoa.
 * @author Rodrigo
 *  
 */
public class Cliente extends Pessoa implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String rg = null;
	
	private String endereco = null;
	
	private String estado = null;
	
	private Integer numero = null;
	
	private String cidade = null;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
