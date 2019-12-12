package br.com.tcc.puc.exception;

public class PagAnteriorVencException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PagAnteriorVencException(String mensagem) {
		super(mensagem);
	}

}
