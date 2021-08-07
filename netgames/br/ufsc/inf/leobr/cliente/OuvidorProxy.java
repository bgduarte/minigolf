package br.ufsc.inf.leobr.cliente;

import java.io.Serializable;


/**
 * @author Leonardo Brasil
 * 
 */
public interface OuvidorProxy extends Serializable {

	/**
	 * Indica que o jogador requisitou uma nova partida e que ela foi aceita e
	 * portanto ele deve "se preparar" parar iniciar essa partida.
	 */
	public void iniciarNovaPartida(Integer posicao);

	/**
	 * Indica que a partida terminou por algum problema Como por exemplo, se um
	 * dos jogadores for desconectado. E o jogo deve finalizar seu estado no
	 * momento que recebe essa mensagem.
	 */
	public void finalizarPartidaComErro(String message);

	/**
	 * Recebe uma mensagem em forma de String do servidor.
	 * 
	 * @param arg
	 */
	public void receberMensagem(String msg);

	/**
	 * Recebe uma jogada do servidor.
	 * 
	 * @param jogada
	 */
	public void receberJogada(Jogada jogada);

	/**
	 * Caso não consigua se comunicar com o servidor, ex O servidor caiu,
	 * conexão de rede não disponível. Esse método é chamado em todos os
	 * ouvintes.
	 */
	public void tratarConexaoPerdida();
	
	/**
	 * Caso não consigua se comunicar com o servidor, ex O servidor caiu,
	 * conexão de rede não disponível. Esse método é chamado em todos os
	 * ouvintes.
	 */
	public void tratarPartidaNaoIniciada(String message);

	
}
