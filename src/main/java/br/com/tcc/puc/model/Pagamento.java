package br.com.tcc.puc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Essa classe representará o pagamento que será armazenado em nossa aplicação.
 * @author Rodrigo
 *  
 */
public class Pagamento implements Serializable  {

	private static final long serialVersionUID = 1L;

	private Cliente cliente = null;
	
	private Date dtPagamento = null;
	
	private Float valorPagamento = null;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public Float getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(Float valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	
	
	
}
