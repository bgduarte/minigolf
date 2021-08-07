package br.ufsc.inf.leobr.cliente;

import java.rmi.MarshalledObject;

/**
 * Interface que define os métodos que devem ser 
 * tratados pelo cliente que recebe as mensagens do servidor.
 * 
 * @author Leonardo de Souza Brasil
 *
 */
public interface ClienteReceptor {

	/**
	 * Método que indica que uma nova partida deve ser iniciada.
	 * @param posicao
	 */
	public void iniciarNovaPartida(Integer posicao);
	
	public void recebaJogada(MarshalledObject jogadaMarshalled);
	
	public void finalizarPartidaComErro(String message);
	
	public void tratarPerdaConexao();
	
	public void tratarPartidaNaoInciada(String message);
	
}
