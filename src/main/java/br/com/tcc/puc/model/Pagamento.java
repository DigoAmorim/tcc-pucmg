package br.com.tcc.puc.model;

import java.io.Serializable;
import java.util.Date;
import java.time.temporal.ChronoUnit;

/**
 * Essa classe representar� o pagamento que ser� armazenado em nossa aplica��o.
 * @author Rodrigo
 *  
 */
public class Pagamento implements Serializable, Comparable<Pagamento> {

	private static final long serialVersionUID = 1L;

	private Cliente cliente = null;
	
	private Date dtPagamento = null;
	
	private Double valorPagamento = null;
	
	/**
	 * M�todo que ajudar� a efetuar a compara��o entre os dias de pagamento, para orden�-lo em ordem crescente.
	 */
	public int compareTo(Pagamento o) {
		long difDias;
		difDias = ChronoUnit.DAYS.between(this.dtPagamento.toInstant(), o.dtPagamento.toInstant());  
		return (int)difDias;
	}

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

	public Double getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(Double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

}
