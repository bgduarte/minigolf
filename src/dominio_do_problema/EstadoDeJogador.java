package dominio_do_problema;

public class EstadoDeJogador {

	protected int tacadas;
	protected Vetor2 posicaoBola;
	protected boolean acertou;
	
	public EstadoDeJogador(int tacadas, Vetor2 posicaoBola, boolean acertou) {
		this.tacadas = tacadas;
		this.posicaoBola = posicaoBola;
		this.acertou = acertou;
	}
	
	public int obterTacadas() {
		return tacadas;
	}
	
	public Vetor2 obterPosicaoBola() {
		return posicaoBola;
	}
	
	//TODO : Obter tacadas?
}
