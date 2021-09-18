package dominio_do_problema;

public class Partida {
	
	protected Mapa mapa;
	protected Jogador jogadores;
	protected boolean turnoJogadorLocal;
	
	public EstadoPartida iteraBolinha(float tempoIteracao) {
		return new EstadoPartida();
	}
	
	public void trocarTurno() {
	}
	
	public void registrarTacada(Vetor2 velocidade) {
	}
	
	public Jogador obterJogadorDaVez() {
		return new Jogador();
	}
	
	public void atualizarEstado() {
	}
	
	public boolean obterTurnoJogadorLocal() {
		return false;
	}
	
	public boolean receberTacada(Tacada tacada) {
		return false;
	}
	
	public EstadoPartida avaliarEncerramentoDePartida() {
		return new EstadoPartida();
	}
	
	public void encerrarHavendoPartida() {
	}
	
	public void IniviarNovaPartida(int ordem, String adversario) {
	}
	
	public void encerrarPartidaLocalmente() {
	}
}
