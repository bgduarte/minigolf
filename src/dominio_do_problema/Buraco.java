package dominio_do_problema;

public class Buraco extends Terreno {

	protected Vetor2 posicao;
	protected float raio;
	
	public Buraco(float atrito, Vetor2 posicao, float raio) {
		this.atrito = atrito;
		this.posicao = posicao;
		this.raio = raio;
	}
	
	public boolean estaNoBuraco(EstadoDeBola estadoBola) {
		Vetor2 posicaoDaBola = estadoBola.obterPosicao();
		Vetor2 vetor = posicaoDaBola.somarComVetor(new Vetor2(-posicao.x, -posicao.y));
		float distancia = vetor.obterMagnitude();
		return distancia < raio;
	}
}
