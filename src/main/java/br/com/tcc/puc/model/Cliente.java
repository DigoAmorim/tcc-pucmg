package br.com.tcc.puc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Essa classe representará o cliente que será armazenado em nossa aplicação. Ele herda os campos de Pessoa.
 * @author Rodrigo
 *  
 */
public class Cliente extends Pessoa implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String endereco = null;
	
	private String estado = null;
	
	private Integer numero = null;
	
	private String cidade = null;
	
	private String tpPlano = null;
	
	private String descTpPlano = null;
	
	private Date dtMatricula = null;
	
	private String sitCliente = null;
	
	private String proxDataVencimento = null;
	
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

	public String getTpPlano() {
		return tpPlano;
	}

	public void setTpPlano(String tpPlano) {
		this.tpPlano = tpPlano;
	}

	public Date getDtMatricula() {
		return dtMatricula;
	}

	public void setDtMatricula(Date dtMatricula) {
		this.dtMatricula = dtMatricula;
	}

	public String getSitCliente() {
		return sitCliente;
	}

	public void setSitCliente(String sitCliente) {
		this.sitCliente = sitCliente;
	}

	public String getProxDataVencimento() {
		return proxDataVencimento;
	}

	public void setProxDataVencimento(String proxDataVencimento) {
		this.proxDataVencimento = proxDataVencimento;
	}

	public String getDescTpPlano() {
		return descTpPlano;
	}

	public void setDescTpPlano(String descTpPlano) {
		this.descTpPlano = descTpPlano;
	}
	
}
