package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	
	private int contaemDeMovimento;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}
	 
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.daPosicao(posicao);
	}
	
	protected boolean existePecaOponente (Posicao pos) {
		 PecaXadrez p = (PecaXadrez)getTabuleiro().peca(pos);
		 return p != null && p.getCor() != cor;
	}
	
}
