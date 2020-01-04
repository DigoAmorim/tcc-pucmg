package br.com.tcc.puc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Essa classe representará o instrutor que será armazenado em nossa aplicação. Ele herda os campos de Pessoa.
 * @author Rodrigo
 *  
 */
@Entity
@Table(name = "instrutor")
public class Instrutor extends Pessoa implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cpf")
	private String cpf = null;

	@Column(name = "nome")
	private String nome = null;
	
	@Column(name = "rg")
	private String rg = null;
	
	@Column(name = "chk_agr")
	private boolean chkAulaGrupo;
	
	@Column(name = "chk_mus")
	private boolean chkAulaMuscu;
	
	@Transient
	private String[] tpAtividade = null;
	
	@Transient
	private String tpAtividadeTxt = null;

	public String[] getTpAtividade() {
		return tpAtividade;
	}

	public void setTpAtividade(String[] tpAtividade) {
		this.tpAtividade = tpAtividade;
	}

	public String getTpAtividadeTxt() {
		return tpAtividadeTxt;
	}

	public void setTpAtividadeTxt(String tpAtividadeTxt) {
		this.tpAtividadeTxt = tpAtividadeTxt;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public boolean isChkAulaGrupo() {
		return chkAulaGrupo;
	}

	public void setChkAulaGrupo(boolean chkAulaGrupo) {
		this.chkAulaGrupo = chkAulaGrupo;
	}

	public boolean isChkAulaMuscu() {
		return chkAulaMuscu;
	}

	public void setChkAulaMuscu(boolean chkAulaMuscu) {
		this.chkAulaMuscu = chkAulaMuscu;
	}
	
}
