package br.ufsc.inf.leobr.cliente.exception;


public class ChamadaPerdidaException extends Exception {

	/**
	 * Lançada quando por algum motivo não é possível chamar uma função no
	 * servidor. Nesse caso a conexão de rede ainda existe, pois se não
	 * existisse seria tratada pelo ConexaoPerdidaTratador, porém
	 * não é possível chamar a função no servidor.
	 * Possível erro: a função não existe no servidor.
	 */
	public ChamadaPerdidaException() {
		super(
				"Por algum motivo não foi possível enviar essa mensagem ao servidor");
	}

}
