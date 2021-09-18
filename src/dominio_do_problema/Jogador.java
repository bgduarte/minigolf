package dominio_do_problema;

public class Jogador {
	
	protected Bola bola;
	protected boolean acertou;
	protected int jogadas;
	
	public Jogador(Vetor2 posInicial, Mapa mapa) {
		bola = new Bola(posInicial, mapa);
		acertou = false;
		jogadas = 0;
	}
	
	public void iteraBolinha(float tempoIteracao) {
		bola.iteraBolinha(tempoIteracao);
	}
	
	public void aplicarTacada(Vetor2 velocidade) {
		bola.aplicarTacada(velocidade);
	}
	
	public EstadoDeBola obterEstadoBola() {
		return bola.obterEstadoBola();
	}
	
	public EstadoDeJogador obterEstadoJogador() {
		return new EstadoDeJogador(jogadas, bola.obterPosicao(), acertou);
	}
	
	public void definirAcerto(boolean acertou) {
		this.acertou = acertou;
	}

	public boolean obterAcertou() {
		return acertou;
	}
	
	public int obterTacadas() {
		return jogadas;
	}
}
