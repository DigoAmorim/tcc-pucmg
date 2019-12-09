package br.com.tcc.puc.model;

import java.io.Serializable;

/**
 * Essa classe representará o instrutor que será armazenado em nossa aplicação. Ele herda os campos de Pessoa.
 * @author Rodrigo
 *  
 */
public class Instrutor extends Pessoa implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String[] tpAtividade = null;
	
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
	
	

}
