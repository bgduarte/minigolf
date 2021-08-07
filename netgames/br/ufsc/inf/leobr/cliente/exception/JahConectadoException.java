package br.ufsc.inf.leobr.cliente.exception;


/**
 * Exceção lançada quando o usuário tenta se conectar ao servidor, porém o mesmo
 * já está conectado.
 * @author leonardo.brasil
 *
 */
public class JahConectadoException extends Exception {
	
	public JahConectadoException(){
		super("Já existe uma conexão ativa entre cliente e servidor");
	}

	
}
