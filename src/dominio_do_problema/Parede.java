package dominio_do_problema;

public class Parede {
	
	protected Vetor2 posicoes;
	protected float espessura;
	
	public EstadoDeBola corrigePosicao(EstadoDeBola estadoDeBola, EstadoDeBola novoEstadoDeBola) {
		return new EstadoDeBola();
	}
	
	protected boolean verificaColisao(EstadoDeBola estadoDeBola, EstadoDeBola novoEstadoDeBola) {
		return false;
	}
	
	protected Vetor2 pontoIntersecao(EstadoDeBola estadoDeBola, EstadoDeBola novoEstadoDeBola) {
		return new Vetor2();
	}
	
	public boolean paredeNaVertical() {
		return false;
	}
}
