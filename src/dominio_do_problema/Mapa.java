package dominio_do_problema;

public class Mapa {

	protected Parede[] paredes;
	protected Terreno[] terrenos;
	protected Buraco buraco;
	protected Vetor2 posicaoDeInicio;
	
	public Mapa(Parede[] paredes, Buraco buraco, Vetor2 posicaoDeInicio) {
		this.paredes = paredes;
		this.buraco = buraco;
		this.posicaoDeInicio = posicaoDeInicio;
		Grama grama = new Grama(0.5f);
		terrenos = new Terreno[1];
		terrenos[0] = grama;
	}
	
	public Vetor2 obterPosicaoDeInicio() {
		return posicaoDeInicio;
	}
	
	public EstadoDeBola efetuarMovimentacao(EstadoDeBola estadoBola, float tempoIteracao) {
		boolean noBuraco = buraco.estaNoBuraco(estadoBola);
		EstadoDeBola novoEstadoDeBola = estadoBola;
		if(!noBuraco) {
			Vetor2 posicao = estadoBola.obterPosicao();
			Terreno terreno = obterTerreno(posicao);
			float atrito = terreno.obterAtrito();
			
			novoEstadoDeBola = calcularNovaPosicao(estadoBola, atrito);
			
			for(Parede parede : paredes) {
				novoEstadoDeBola = parede.corrigePosicao(estadoBola, novoEstadoDeBola);
			}
			
			noBuraco = buraco.estaNoBuraco(estadoBola);
			if(noBuraco) {
				novoEstadoDeBola.definirVelocidade(new Vetor2(0,0));
				novoEstadoDeBola.definirNoBuraco(true);
			}
		}
		return novoEstadoDeBola;
		
	}
	
	public Terreno obterTerreno(Vetor2 posicao) {
		return terrenos[0];
	}
	
	public EstadoDeBola calcularNovaPosicao(EstadoDeBola estadoBola, float atrito) {
		Vetor2 posicao = estadoBola.obterPosicao();
		Vetor2 velocidade = estadoBola.obterVelocidade();
		Vetor2 velocidadeComAtrito = velocidade.multiplicarPorEscalar(atrito);
		Vetor2 velocidadeEfetiva = velocidadeComAtrito.somarComVetor(velocidade);
		Vetor2 deslocamento = velocidadeEfetiva.multiplicarPorEscalar(0.5f);
		Vetor2 novaPosição = posicao.somarComVetor(deslocamento);
		return new EstadoDeBola(novaPosição, novaPosição);
	}
}
