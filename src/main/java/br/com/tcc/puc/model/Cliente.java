package br.com.tcc.puc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Essa classe representará o cliente que será armazenado em nossa aplicação. Ele herda os campos de Pessoa.
 * @author Rodrigo
 *  
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cpf")
	private String cpf = null;
	
	@Column(name = "nome")
	private String nome = null;
	
	@Column(name = "rg")
	private String rg = null;
	
	@Column(name = "endereco")
	private String endereco = null;
	
	@Column(name = "uf")
	private String estado = null;
	
	@Column(name = "num_endereco")
	private Integer numero = null;
	
	@Column(name = "cidade")
	private String cidade = null;
	
	@Column(name = "tp_Plano")
	private String tpPlano = null;
	
	@Transient
	private String descTpPlano = null;
	
	@Transient
	private Date dtMatricula = null;
	
	@Transient
	private String sitCliente = null;
	
	@Transient
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
