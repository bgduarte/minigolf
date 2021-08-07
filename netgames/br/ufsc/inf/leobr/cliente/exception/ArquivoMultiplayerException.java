package br.ufsc.inf.leobr.cliente.exception;


public class ArquivoMultiplayerException extends Exception {

	/**
	 * Lançada caso ocorra algum erro com o arquivo, pode ser um erro 
	 * de IOException ou então um erro de ciframento.
	 * A mensagem do erro é exibida para o desevolvedor.
	 * Esse tipo de erro impede que o jogo multiPlayer seja jogado.
	 */
	public ArquivoMultiplayerException(String message) {
		super(message);
	}

}
