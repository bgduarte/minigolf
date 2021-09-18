package dominio_do_problema;

public class Parede {
	
	protected Vetor2[] posicoes;
	protected float espessura;

	//Construtores
	public Parede() {

	} 

	public Parede(Vetor2[] posicoes, float espessura) {
		this.posicoes = posicoes;
		this.espessura =espessura;
	}
	
	//Outros metodos
	public EstadoDeBola corrigePosicao(EstadoDeBola estadoDeBola, EstadoDeBola novoEstadoDeBola) {

		boolean colisao = this.verificaColisao(estadoDeBola, novoEstadoDeBola);

		if (colisao) {
			Vetor2 pontoColisao = this.pontoIntersecao(estadoDeBola, novoEstadoDeBola);
			boolean vertical = this.paredeNaVertical();

			if(vertical) {
				float deslocamento = (pontoColisao.x-estadoDeBola.posicao.x)-(pontoColisao.x-novoEstadoDeBola.posicao.x);
				float novaPosicao = estadoDeBola.posicao.x + deslocamento;
				novoEstadoDeBola.posicao.definirX(novaPosicao);
			}else{
				float deslocamento = (pontoColisao.y-estadoDeBola.posicao.y)-(pontoColisao.y-novoEstadoDeBola.posicao.y);
				float novaPosicao = estadoDeBola.posicao.y + deslocamento;
				novoEstadoDeBola.posicao.definirY(novaPosicao);
			}
		}

		return novoEstadoDeBola;
	}
	
	protected boolean verificaColisao(EstadoDeBola estadoDeBola, EstadoDeBola novoEstadoDeBola) {
		float det = (this.posicoes[1].x - this.posicoes[0].x) * (novoEstadoDeBola.posicao.y - estadoDeBola.posicao.y)  -  
				(this.posicoes[1].y - this.posicoes[0].y) * (novoEstadoDeBola.posicao.x - estadoDeBola.posicao.x);

		return det != 0;
	}
	
	protected Vetor2 pontoIntersecao(EstadoDeBola estadoDeBola, EstadoDeBola novoEstadoDeBola) {
		
		float det = (this.posicoes[1].x - this.posicoes[0].x) * (novoEstadoDeBola.posicao.y - estadoDeBola.posicao.y)  -  
				(this.posicoes[1].y - this.posicoes[0].y) * (novoEstadoDeBola.posicao.x - estadoDeBola.posicao.x);
		
		float s = ((this.posicoes[1].x - this.posicoes[0].x) * (this.posicoes[0].y - estadoDeBola.posicao.y) - 
			(this.posicoes[1].y - this.posicoes[0].y) * (this.posicoes[0].x - estadoDeBola.posicao.x))/ det ;
	
		float xColisao = estadoDeBola.posicao.x + (novoEstadoDeBola.posicao.x-estadoDeBola.posicao.x)*s;
		float yColisao = estadoDeBola.posicao.y + (novoEstadoDeBola.posicao.y-estadoDeBola.posicao.y)*s;

		Vetor2 pontoColisao = new Vetor2(xColisao, yColisao);

		return pontoColisao;
	}
	
	public boolean paredeNaVertical() {
		return this.posicoes[0].x == this.posicoes[1].x;
	}
}
