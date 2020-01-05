package br.com.tcc.puc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.temporal.ChronoUnit;

/**
 * Essa classe representará o pagamento que será armazenado em nossa aplicação.
 * @author Rodrigo
 *  
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable, Comparable<Pagamento> {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
	private int pagamentoId;
	
    @ManyToOne
    @JoinColumn(name="cpf_cliente", nullable=false)
	private Cliente cliente = null;
	
	@Column(name = "dt_pagamento")
	@Temporal(TemporalType.DATE)
	private Date dtPagamento = null;
	
	@Column(name = "vlr_pagamento")
	private Double valorPagamento = null;
	
	/**
	 * Método que ajudará a efetuar a comparação entre os dias de pagamento, para ordená-lo em ordem crescente.
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

	public int getPagamentoId() {
		return pagamentoId;
	}

	public void setPagamentoId(int pagamentoId) {
		this.pagamentoId = pagamentoId;
	}
	
}
