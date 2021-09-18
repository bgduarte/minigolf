package dominio_do_problema;

public class Jogador {
	
	protected String nome;
	protected Bola bola;
	protected boolean acertou;
	protected int jogadas;
	
	public void iteraBolinha(float tempoIteracao) {
	}
	
	public void aplicarTacada(Vetor2 velocidade) {
	}
	
	public EstadoDeBola obterEstadoBola() {
		return new EstadoDeBola();
	}
	
	public EstadoDeJogador obterEstadoJogador() {
		return new EstadoDeJogador();
	}
	
	public void definirAcerto(boolean acertou) {
	}
	
	public void iniciar() {
	}
	
	public void definirNome(String adversario) {
	}
	
	public void definirComoPrimeiro() {
	}
	
	public boolean obterAcertou() {
		return false;
	}
	
	public int obterTacadas() {
		return 0;
	}
}
