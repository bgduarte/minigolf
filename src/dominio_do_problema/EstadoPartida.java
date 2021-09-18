package dominio_do_problema;

public class EstadoPartida {
	
	protected String mensagem;
	protected boolean turnoJogadorLocal;
	protected EstadoDeJogador jogadorRemoto;
	protected EstadoDeJogador jogadorLocal;
	protected int jogadorVencedor;
	
	public void definirEstado(EstadoDeJogador jogadorRemoto, EstadoDeJogador jogadorLocal, boolean turnoJogadorLocal) {
	}
	
	public boolean obterTurnoJogadorLocal() {
		return false;
	}
	
	public void definirMensagem(String mensagem) {
	}
	
	public String obterMensagem() {
		return "";
	}
	
	public void obterEstadoDeJogador() {
	}
}
