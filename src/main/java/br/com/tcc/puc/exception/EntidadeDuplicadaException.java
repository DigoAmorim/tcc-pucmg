package br.com.tcc.puc.exception;

public class EntidadeDuplicadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor da classe
	 */
	public EntidadeDuplicadaException(String mensagem) {
		super(mensagem);
	}

}