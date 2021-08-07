package br.ufsc.inf.leobr.cliente.exception;


/**
 * Exceção lançada quando o jogador não estiver conectado ou 
 * participando de uma partida.
 * @author leonardo.brasil
 *
 */
public class NaoJogandoException extends Exception {

	public NaoJogandoException(){
		super("Ainda não está participando de uma partida no servidor");
	}
	
}
