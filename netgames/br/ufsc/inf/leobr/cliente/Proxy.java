package br.ufsc.inf.leobr.cliente;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

import com.retrogui.dualrpc.common.CallException;

/**
 * Classe de comunicação com o servidor. Implementa o design pattern Singleton.
 * 
 * @author Leonardo de Souza Brasil
 * 
 */
public final class Proxy implements ClienteReceptor {

	private static Proxy instance;

	private String nomeJogador;

	private static Long idJogo = 1l;

	private List<OuvidorProxy> ouvintes = new ArrayList<OuvidorProxy>();

	private EstadoProxy estadoProxy, estadoConectado, estadoDesconectado,
			estadoJogando;

	/**
	 * Implementa o padrão Singleton
	 * 
	 * @return uma instancia de Proxy
	 */
	public static Proxy getInstance() {
		if (instance == null) {
			instance = new Proxy();
		}
		return instance;
	}

	/**
	 * Construtor padrão que inicia o proxy com o estado desconectado
	 */
	private Proxy() {
		super();

		estadoConectado = new ProxyConectadoEstado(this);
		estadoDesconectado = new ProxyDesconectadoEstado(this);
		estadoJogando = new ProxyJogandoEstado(this);

		estadoProxy = estadoDesconectado;

	}

	// ----------------------- Get e Set
	public static Long getIdJogo() {
		return idJogo;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	protected List<OuvidorProxy> getOuvintes() {
		return ouvintes;
	}

	protected EstadoProxy getEstadoProxy() {
		return estadoProxy;
	}

	protected void setEstadoProxy(EstadoProxy estadoProxy) {
		this.estadoProxy = estadoProxy;
	}

	protected EstadoProxy getEstadoConectado() {
		return estadoConectado;
	}

	protected EstadoProxy getEstadoDesconectado() {
		return estadoDesconectado;
	}

	protected EstadoProxy getEstadoJogando() {
		return estadoJogando;
	}

	// ----------------------- Ciclo de Vida
	/**
	 * Adiciona um objeto para receber notivicações
	 * 
	 * @param ouvinte
	 *            Qualquer classe que implemente OuvidorProxy
	 */
	public void addOuvinte(OuvidorProxy ouvinte) {
		ouvintes.add(ouvinte);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufsc.inf.leobr.common.cliente.JogadorIf#conectar(java.lang.String,
	 *      java.lang.String)
	 */
	public synchronized void conectar(String ipServidor, String nome)
			throws JahConectadoException, NaoPossivelConectarException,
			ArquivoMultiplayerException {
		this.setNomeJogador(nome);

		estadoProxy.conectar(ipServidor, nome);

		notifyAll();

	}

	public void desconectar() throws NaoConectadoException {
		estadoProxy.desconectar();
	}

	public synchronized void receberMensagem(String arg) {
		for (OuvidorProxy ouvinte : this.ouvintes) {
			ouvinte.receberMensagem(arg);
		}
		notifyAll();
	}

	/**
	 * Requisita o inicio de uma partida ao servidor
	 * 
	 * @throws NaoConectadoException
	 */
	public synchronized void iniciarPartida(Integer qtdeJogadoresNaPartida)
			throws NaoConectadoException {

		estadoProxy.iniciarPartida(qtdeJogadoresNaPartida);

		notifyAll();
	}
	
	public synchronized void finalizarPartida() throws NaoConectadoException, NaoJogandoException {
		
		estadoProxy.finalizarPartida();
	
		notifyAll();
	}

	/**
	 * Com a partida já iniciada envia uma jogada.
	 * 
	 * @param jogada
	 * @throws NaoJogandoException
	 */
	public synchronized void enviaJogada(Jogada jogada)
			throws NaoJogandoException {

		estadoProxy.enviaJogada(jogada);

		notifyAll();
	}

	/**
	 * Recebe a jogada do servidor.
	 * 
	 * @param jogadaMarshalled
	 */

	public synchronized void recebaJogada(MarshalledObject jogadaMarshalled) {

		System.out.println("Mensagem recebida no proxy, enviado aos ouvintes:");
		System.out.println();
		Jogada jogada;

		try {
			jogada = (Jogada) jogadaMarshalled.get();
			for (OuvidorProxy ouvinte : ouvintes) {
				ouvinte.receberJogada(jogada);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		notifyAll();

	}

	public synchronized void iniciarNovaPartida(Integer posicao) {

		this.setEstadoProxy(this.getEstadoJogando());

		for (OuvidorProxy ouvinte : ouvintes) {
			ouvinte.iniciarNovaPartida(posicao);
		}

		notifyAll();

	}

	/**
	 * Método chamado pelo servidor caso ocorra algum erro e a partida tenha que
	 * ser finalizada. Como por exemplo se um dos jogadores de uma partida se
	 * desconecta.
	 * 
	 * @param message
	 */

	public synchronized void finalizarPartidaComErro(String message) {

		this.setEstadoProxy(new ProxyConectadoEstado(this));

		for (OuvidorProxy ouvinte : ouvintes) {
			ouvinte.finalizarPartidaComErro(message);
		}

		notifyAll();
	}

	public synchronized void tratarPerdaConexao() {

		this.setEstadoProxy(new ProxyDesconectadoEstado(this));

		for (OuvidorProxy ouvinte : ouvintes) {
			ouvinte.tratarConexaoPerdida();
		}

		notifyAll();

	}

	public synchronized void reiniciarPartida() throws NaoConectadoException,
			NaoJogandoException {

		estadoProxy.reiniciarPartida();

		notifyAll();
	}

	public synchronized void tratarPartidaNaoInciada(String message) {
		for (OuvidorProxy ouvinte : ouvintes) {
			ouvinte.tratarPartidaNaoIniciada(message);
		}
		notifyAll();
	}

	/**
	 * Obtêm uma lista com os identificadores dos outros jogadores de uma mesma
	 * partida
	 * 
	 * @return
	 */
	public List<String> obterNomeAdversarios() {
		List<String> nomeJogadores = estadoProxy.obterNomeAdversarios();
		nomeJogadores.remove(this.getNomeJogador());
		return nomeJogadores;
	}

	/**
	 * Obtêm o nome de um adversário dada uma posíção
	 * @param posicao
	 * @return
	 */
	public String obterNomeAdversario(Integer posicao) {
		return estadoProxy.obterNomeAdversario(posicao);
	}

}
