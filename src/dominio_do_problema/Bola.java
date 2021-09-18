package dominio_do_problema;

public class Bola {
	
	protected Vetor2 posicao;
	protected float raio;
	protected Vetor2 velocidade;
	protected Mapa mapa;
	protected boolean emMovimento; 
	protected boolean noBuraco;
	
	public void iteraBolinha(float tempoIteracao) {
	}
	
	public void aplicarTacada(Vetor2 velocidade) {
	}
	
	public void definirVelocidade(Vetor2 novaVelocidade) {
	}
	
	public void definirPoisicao(Vetor2 novaPosicao) {
	}
	
	public void atualizarBolinha(float tempoIteracao) {
	}
	
	public EstadoDeBola obterEstadoBola() {
		return new EstadoDeBola();
	}
	
	public Vetor2 obterPosicao() {
		return new Vetor2();
	}
	
	public boolean obterNoBuraco() {
		return false;
	}
	
	public void definirNoBuraco(boolean noBuraco) {
	}
	
	public void iniciar() {
	}
	
}
