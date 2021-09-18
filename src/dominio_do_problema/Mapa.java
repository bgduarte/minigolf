package dominio_do_problema;

public class Mapa {

	protected Parede paredes;
	protected Terreno terrenos;
	protected Buraco buraco;
	protected Vetor2 posicaoDeInicio;
	
	public void efetuarMovimentacao(EstadoDeBola estadoBola, float tempoIteracao) {
	}
	
	public Terreno obterTerreno(Vetor2 posicao) {
		return new Terreno();
	}
	
	public EstadoDeBola calcularNovaPosicao(EstadoDeBola estadoBola, float atrito) {
		return new EstadoDeBola();
	}
}
