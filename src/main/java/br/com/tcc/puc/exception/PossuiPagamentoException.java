package br.com.tcc.puc.exception;

public class PossuiPagamentoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe
	 */
	public PossuiPagamentoException(String mensagem) {
		super(mensagem);
	}
}