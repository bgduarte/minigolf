package dominio_do_problema;

public class EstadoDeBola {
	
	protected Vetor2 velocidade;
	protected Vetor2 posicao;
	protected boolean noBuraco;
	
	public Vetor2 obterVelocidade() {
		return new Vetor2();
	}
	
	public Vetor2 obterPosicao() {
		return new Vetor2();
	}
	
	public boolean obterEmMovimento() {
		return false;
	}
	
	public boolean obterNoBuraco() {
		return false;
	}
	
	public void definirVelocidade(Vetor2 novaVelocidade) {
	}
	
	public void definirNoBuraco(boolean noBuraco) {
	}
}
