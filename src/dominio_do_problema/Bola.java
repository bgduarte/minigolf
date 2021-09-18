package dominio_do_problema;

public class Bola {
	
	protected Vetor2 posicao;
	protected Vetor2 velocidade;
	protected Mapa mapa;
	protected boolean emMovimento; 
	protected boolean noBuraco;

	//construtores
	public Bola(){
		
	}

	public Bola(Vetor2 posicao, Vetor2 velocidade, Mapa mapa){
		this.posicao = posicao;
		this.velocidade = velocidade;
		this.mapa = mapa;
		this.emMovimento = false;
		this.noBuraco = false;
	}
	
	public Bola(Vetor2 posicao, Vetor2 velocidade, Mapa mapa, boolean emMovimento, boolean noBuraco){
		this.posicao = posicao;
		this.velocidade = velocidade;
		this.mapa = mapa;
		this.emMovimento = emMovimento;
		this.noBuraco = noBuraco;
	}


	//set
	public void definirVelocidade(Vetor2 novaVelocidade) {
		this.velocidade = novaVelocidade;
	}

	public void definirPoisicao(Vetor2 novaPosicao) {
		this.posicao = novaPosicao;
	}

	public void definirNoBuraco(boolean noBuraco) {
		this.noBuraco = noBuraco;
	}


	//get
	public EstadoDeBola obterEstadoBola() {
		return new EstadoDeBola(this.velocidade, this.posicao);
	}
	
	public Vetor2 obterPosicao() {
		return new Vetor2(this.posicao.x, this.posicao.y);
	}
	
	public boolean obterNoBuraco() {
		return this.noBuraco;
	}

	//Outros metodos
	public void iteraBolinha(float tempoIteracao) {
		atualizarBolinha(tempoIteracao);
	}
	
	public void aplicarTacada(Vetor2 velocidade) {
		definirVelocidade(velocidade);
	}

	public void atualizarBolinha(float tempoIteracao) {
		EstadoDeBola estadoBola = new EstadoDeBola(this.velocidade, this.posicao, this.noBuraco);
		estadoBola = this.mapa.efetuarMovimentacao(estadoBola, tempoIteracao);

		Vetor2 novaVelocidade = estadoBola.obterVelocidade();
		Vetor2 novaPosicao = estadoBola.obterPosicao();
		boolean noBuraco = obterNoBuraco();

		if (noBuraco) {
			this.definirNoBuraco(noBuraco);
		}

		definirPoisicao(novaPosicao);
		definirVelocidade(novaVelocidade);
	}
	
	
}
