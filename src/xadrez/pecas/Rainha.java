package xadrez.pecas;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez{

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		p.setValues(posicao.getLinha() - 1, posicao.getColuna());

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValues(posicao.getLinha() + 1, posicao.getColuna());

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		p.setValues(posicao.getLinha() - 1, posicao.getColuna() -1);

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha()-1, p.getColuna()-1);;
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Nordeste
		p.setValues(posicao.getLinha() -1, posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() -1, p.getColuna() +1);;
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// Sudeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() +1, p.getColuna() +1);;
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);

		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeçaNaPoiscao(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() +1, p.getColuna() - 1);;
		}

		if (getTabuleiro().existePosicao(p) && existePecaOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}
	
	@Override
	public String toString() {
		return "D";
	}
	
}
