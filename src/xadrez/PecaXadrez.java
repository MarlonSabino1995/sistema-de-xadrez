package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	
	private int contagemDeMovimento;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getContagemDeMovimento() {
		return contagemDeMovimento;
	}
	
	public void incrementContagem() {
		contagemDeMovimento++;
	}
	
	public void decrementContagem() {
		contagemDeMovimento--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.daPosicao(posicao);
	}
	
	protected boolean existePecaOponente (Posicao pos) {
		 PecaXadrez p = (PecaXadrez)getTabuleiro().peca(pos);
		 return p != null && p.getCor() != cor;
	}
	
}
