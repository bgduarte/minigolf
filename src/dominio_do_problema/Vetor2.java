package dominio_do_problema;
import java.lang.Math;

public class Vetor2 {

	protected float x;
	protected float y;
	
	public Vetor2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vetor2 obterVetorNormalizado() {
		double magnitude = 1/Math.sqrt(x*x + y*y);
		return multiplicarPorEscalar((float)magnitude);
	}
	
	public Vetor2 multiplicarPorEscalar(float escalar) {
		Vetor2 vetor = new Vetor2(this.x, this.y);
		vetor.definirX(x*escalar);	
		vetor.definirY(y*escalar);
		return vetor;
	}
	
	public Vetor2 somarComVetor(Vetor2 vetor) {
		Vetor2 vetorCopia = new Vetor2(this.x, this.y);
		vetorCopia.definirX(vetorCopia.x + vetor.x);	
		vetorCopia.definirY(vetorCopia.y + vetor.y);
		return vetorCopia;
	}
	
	public void definirX(float x) {
		this.x = x;
	}
	
	public void definirY(float y) {
		this.y = y;
	}
	
	public float obterMagnitude() {
		return (float)Math.sqrt(x*x + y*y);
	}
	
}
