package dominio_do_problema;

public class EstadoDeBola {
	
	protected Vetor2 velocidade;
	protected Vetor2 posicao;
	protected boolean noBuraco;
	
	public EstadoDeBola(Vetor2 velocidade, Vetor2 posicao) {
		this.velocidade = velocidade;
		this.posicao = posicao;
		this.noBuraco = false;
	}

	public EstadoDeBola(Vetor2 velocidade, Vetor2 posicao, boolean noBuraco) {
		this.velocidade = velocidade;
		this.posicao = posicao;
		this.noBuraco = noBuraco;
	}
	
	public Vetor2 obterVelocidade() {
		return velocidade;
	}
	
	public Vetor2 obterPosicao() {
		return posicao;
	}
	
	public boolean obterEmMovimento() {
		return velocidade.obterMagnitude() > 0;
	}
	
	public boolean obterNoBuraco() {
		return noBuraco;
	}
	
	public void definirVelocidade(Vetor2 novaVelocidade) {
		velocidade = novaVelocidade;
	}
	
	public void definirNoBuraco(boolean noBuraco) {
		this.noBuraco = noBuraco;
	}
}
