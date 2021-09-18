package dominio_do_problema;

public class Partida {
	
	protected Mapa mapa;
	protected Jogador[] jogadores;
	protected boolean turnoJogadorLocal;
	
	Partida(boolean jogadorLocalIniciou) {
		//TODO: ler mapa de um arquivo
		//Passar o valor inicial do mapa
		mapa = new Mapa();
		Vetor2 posicaoInicial = mapa.obterPosicaoDeInicio();
		
		jogadores[0] = new Jogador(posicaoInicial);
		jogadores[1] = new Jogador(posicaoInicial);
		turnoJogadorLocal = jogadorLocalIniciou;
	}
	
	public EstadoPartida iteraBolinha(float tempoIteracao) {
		Jogador jogadorDaVez = obterJogadorDaVez();
		jogadorDaVez.iteraBolinha(tempoIteracao);
		EstadoDeBola estadoDeBola = jogadorDaVez.obterEstadoBola();
		
		boolean bolinhaEmMomvimento = estadoDeBola.obterEmMovimento();
		if(!bolinhaEmMomvimento)
				trocarTurno();
		
		boolean acertou = estadoDeBola.obterNoBuraco();
		jogadorDaVez.definirAcerto(acertou);
		return atualizarEstado();
	}
	
	public void trocarTurno() {
		turnoJogadorLocal = !turnoJogadorLocal;
	}
	
	public void registrarTacada(Vetor2 velocidade) {
		Jogador jogadorDaVez = obterJogadorDaVez();
		jogadorDaVez.aplicarTacada(velocidade);
	}
	
	public Jogador obterJogadorDaVez() {
		return turnoJogadorLocal ? jogadores[0] : jogadores[1];
	}
	
	public EstadoPartida atualizarEstado() {
		EstadoPartida estadoPartida = new EstadoPartida();
		EstadoDeJogador estadoJogadorLocal = jogadores[0].obterEstadoJogador();
		EstadoDeJogador estadoJogadorRemoto = jogadores[1].obterEstadoJogador();
		boolean turnoDoJogadorLocal = obterTurnoJogadorLocal();
		
		estadoPartida.definirEstado(estadoJogadorRemoto, estadoJogadorLocal, turnoDoJogadorLocal);
		
		return estadoPartida;
	}
	
	public boolean obterTurnoJogadorLocal() {
		return turnoJogadorLocal;
	}
	
	
	public boolean receberTacada(Tacada tacada) {
		Vetor2 velocidade = tacada.obterVelocidade();
		registrarTacada(velocidade);
		return obterTurnoJogadorLocal();
	}
	public EstadoPartida avaliarEncerramentoDePartida() {
		boolean localAcertou = jogadores[0].obterAcertou();
		boolean remotoAcertou = jogadores[1].obterAcertou();
		
		EstadoPartida estadoPartida = atualizarEstado();
		if(localAcertou && remotoAcertou) {
			String mensagem;
			int tacadasLocal = jogadores[0].obterTacadas();
			int tacadasRemoto = jogadores[1].obterTacadas();
			if(tacadasLocal < tacadasRemoto) {
				mensagem = "Você perdeu!";
			} else {
				mensagem = "Você ganhou!";
			}
			estadoPartida.definirMensagem(mensagem);
		}
		return estadoPartida;
	}
	
	public void encerrarPartidaLocalmente() {
		Vetor2 posicaoInicial = mapa.obterPosicaoDeInicio();
		jogadores[0] = new Jogador(posicaoInicial);
		jogadores[1] = new Jogador(posicaoInicial);
	}
}
