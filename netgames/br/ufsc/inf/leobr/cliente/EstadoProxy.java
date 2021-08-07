package br.ufsc.inf.leobr.cliente;

import java.util.List;

import com.retrogui.dualrpc.client.NotConnectedException;
import com.retrogui.dualrpc.common.CallException;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public abstract class EstadoProxy {
	
	protected static Cliente cliente;
	
	/**
	 * Conecta o cliente ao servidor.
	 * @param ipServidor
	 * @param nome
	 * @throws JahConectadoException Caso o usuário já esteja conectado.
	 * @throws ArquivoMultiplayerException 
	 */
	 
	public abstract void conectar(String ipServidor, String nome) throws NaoPossivelConectarException, JahConectadoException, ArquivoMultiplayerException;
	
	/**
	 * Desconecta-se do servidor.
	 * @throws NaoConectadoException
	 */
	 
	public void desconectar() throws NaoConectadoException{
		try {
			cliente.desconectar();
		} catch (CallException e) {
			e.printStackTrace();
		} catch (NotConnectedException e) {
			e.printStackTrace();
			throw new NaoConectadoException();
		}
	}
	
	/**
	 * Envia a requisição para o início de uma nova partida.
	 * @throws NaoConectadoException Caso o usuário ainda não esteja conectado.
	 */
	 
	public abstract void iniciarPartida(Integer qtdeJogadoresNaPartida) throws NaoConectadoException;
	
	/**
	 * Envia uma jogada para o servidor
	 * @param jogada
	 * @throws NaoJogandoException
	 */
	 
	public abstract void enviaJogada(Jogada jogada) throws NaoJogandoException;

	/**
	 * Finaliza uma partida mas não se desconecta do servidor.
	 * @throws NaoConectadoException
	 * @throws NaoJogandoException
	 */
	 
	public abstract void finalizarPartida()  throws NaoConectadoException, NaoJogandoException;

	/**
	 * Envia uma requisição para que a partida atual seja reiniciada.
	 * Os jogadores adversários são preservados.
	 * @throws NaoConectadoException
	 * @throws NaoJogandoException
	 */
	 
	public abstract void reiniciarPartida() throws NaoConectadoException, NaoJogandoException;
	
	/**
	 * Obtêm uma lista com os identificadores dos jogadores de uma mesma
	 * partida
	 * 
	 * @return
	 */
	public List<String> obterNomeAdversarios() {
		try {
			List<String> nomeJogadores = cliente.obterNomeJogadores();
			return nomeJogadores;
		} catch (CallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtêm o nome de um adversario em uma determinada posição.
	 * @param posicao
	 * @return
	 */
	public String obterNomeAdversario(Integer posicao) {
		List<String> nomeJogadores;
		try {
			nomeJogadores = cliente.obterNomeJogadores();
			return nomeJogadores.get((posicao - 1));
		} catch (CallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
