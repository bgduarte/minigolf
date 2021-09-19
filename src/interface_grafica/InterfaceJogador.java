package interface_grafica;

import dominio_do_problema.*;
import rede.*;

public class InterfaceJogador {
	
	protected InterfaceNetgamesServer ngames;
	protected InterfaceMiniGolf interfaceMiniGolf;
	protected Partida partida;
	protected boolean aguardandoMouse;
	protected float tempoDoMouse;
	protected boolean antigoEstadoTurno;
	protected boolean novoEstadoTurno;
	protected float inicioUltimaIteracao;
	protected float fimUltimaIteracao;
	protected Vetor2 velocidade;
	protected float tempoDaForcaMaxima = 2000f;
	protected float forcaDaTacada = 10f;
	protected float fracao;

	public InterfaceJogador() {
		ngames = new InterfaceNetgamesServer();
		partida = new Partida();
	}

	public String conectar(String string, String string2) {
		String mensagem = "ja conectado";
		boolean permitido = partida.permitidoConectar();
		if (permitido) {
			mensagem = ngames.conectar(string, string2);
			if (mensagem.equals("Sucesso: conectado a Netgames Server")) {
				partida.definirConectado(true);
			}
		}
		return mensagem;
	}
	
	public String desconectar() {
		String mensagem = "Nao conectado para poder desconectar";
		boolean permitido = partida.permitidoDesconectar();
		if (permitido) {
			mensagem = ngames.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				partida.definirConectado(false);
			}
		}
		return mensagem;
	}
	
	public String iniciarPartida() {
		String mensagem = "Faltam jogadores suficientes conectados";
		boolean permitido = partida.permitidoIniciarPartida();
		if (permitido) {
			mensagem = ngames.iniciarPartida();
		}
		return mensagem;
	}
	
	public void receberTacada(Tacada tacada) {
		antigoEstadoTurno = partida.receberTacada(tacada);
		movimentaBolinha();
		avaliarEncerramentoDePartida();
	}
	
	
	public void processarForca() {
		comecarAContar();
		while(aguardandoMouse) {
			interfaceMiniGolf.desenharForca(fracao);
			contarTempo();
		}
		
	}
	
	public void comecarAContar() {
		aguardandoMouse = true;
		tempoDoMouse = 0f;
		inicioUltimaIteracao = System.currentTimeMillis();
	}
	
	public void contarTempo() {
		fimUltimaIteracao = System.currentTimeMillis();
		tempoDoMouse = fimUltimaIteracao - inicioUltimaIteracao;
		fracao = tempoDoMouse/tempoDaForcaMaxima;
		if(fracao > 1f)
			fracao = 1f;
	}
	
	public void selecionarForca() {
		if(aguardandoMouse) {
			terminarDeContar();
			velocidade = velocidade.multiplicarPorEscalar(fracao);
		}
	}
	
	public void terminarDeContar() {
		aguardandoMouse = false;
	}
	
	public void selecionarDirecao(Vetor2 direcao) {
		boolean turno = partida.obterTurnoJogadorLocal();
		if(turno) {
			velocidade = direcao.obterVetorNormalizado();
			velocidade = velocidade.multiplicarPorEscalar(forcaDaTacada);
			processarForca();
			Tacada tacada = new Tacada(velocidade);
			ngames.enviarTacada(tacada);
			partida.registrarTacada(direcao);
			movimentaBolinha();
			avaliarEncerramentoDePartida();
		}
	}
	
	public void movimentaBolinha() {
		while(antigoEstadoTurno == novoEstadoTurno) {
			float tempoIteracao = fimUltimaIteracao - inicioUltimaIteracao;
			inicioUltimaIteracao = System.currentTimeMillis();
			EstadoPartida estadoPartida = partida.iteraBolinha(tempoIteracao);
			interfaceMiniGolf.exibirEstado(estadoPartida);
			novoEstadoTurno = estadoPartida.obterTurnoJogadorLocal();
			fimUltimaIteracao = System.currentTimeMillis();
		}
	}
	
	public void avaliarEncerramentoDePartida() {
		EstadoPartida estadoPartida =  partida.avaliarEncerramentoDePartida();
		interfaceMiniGolf.exibirEstado(estadoPartida);
	}
	
	public void iniciarNovaPartida(int ordem) {
		boolean conectado = ngames.informaConectado();
		if(!conectado) {
			interfaceMiniGolf.notificar("Não conectado");
		} else {
			boolean atualizarInterface = partida.encerrarPartida();
			if(partida.informarPartidaAndamento())
			{
				partida.encerrarPartidaLocalmente();
			}
			
			if(atualizarInterface) {
				ngames.encerrarPartida();
			}
			
			ngames.iniciarPartida();
		}
	}
}
