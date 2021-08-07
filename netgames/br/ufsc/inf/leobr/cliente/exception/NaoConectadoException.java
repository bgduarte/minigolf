package br.ufsc.inf.leobr.cliente.exception;


/**
 * Exceção lançada quando o usuário tenta enviar uma mensagem sem estar
 * conectado ao serivodr.
 * 
 * @author leonardo.brasil
 * 
 */
public class NaoConectadoException extends Exception {

	public NaoConectadoException(){
		super("Não existe uma conexão entre cliente e servidor");
	}
	
}
