package xadrez.pecas;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);
		
		if(getCor() == Cor.BRANCO) {
			p.setValues(posicao.getLinha()-1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() -2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha()-1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temPeçaNaPoiscao(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() -1, posicao.getColuna() -1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() -1, posicao.getColuna() +1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
		}else {
			p.setValues(posicao.getLinha()+1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() +2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha()+1, posicao.getColuna());
			if(getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p) && getTabuleiro().existePosicao(p2) && !getTabuleiro().temPeçaNaPoiscao(p2) && getContagemDeMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() +1, posicao.getColuna() -1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() +1, posicao.getColuna() +1);
			if(getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		
		
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}
