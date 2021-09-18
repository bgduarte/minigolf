package dominio_do_problema;

public class EstadoPartida {
	
	protected String mensagem;
	protected boolean turnoJogadorLocal;
	protected EstadoDeJogador jogadorRemoto;
	protected EstadoDeJogador jogadorLocal;
	
	EstadoPartida() {
		mensagem = "";
		
	}
	
	public void definirEstado(EstadoDeJogador jogadorRemoto, EstadoDeJogador jogadorLocal, boolean turnoJogadorLocal) {
		this.jogadorRemoto = jogadorRemoto;
		this.jogadorLocal = jogadorLocal;
		this.turnoJogadorLocal = turnoJogadorLocal;
	}
	
	public boolean obterTurnoJogadorLocal() {
		return turnoJogadorLocal;
	}
	
	public void definirMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String obterMensagem() {
		return mensagem;
	}
	
	public EstadoDeJogador obterEstadoDeJogador(boolean doLocal) {
		return doLocal ? jogadorLocal : jogadorRemoto;
	}
}
