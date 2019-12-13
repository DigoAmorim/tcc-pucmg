package br.com.tcc.puc.exception;

public class PagAnteriorVencException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor da classe
	 */
	public PagAnteriorVencException(String mensagem) {
		super(mensagem);
	}

}
