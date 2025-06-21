package xadrez;

import jogoDeTabuleiro.Tabuleiro;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	
	// O tabuleiro tem uma matriz de peças
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
	}
	 
	/*
	 Irá retornar uma matriz de PecaXadrez, porque estou na camada de xadrez
	 para o programa eu não quero liberar as peças do tipo Peca mas sim, PecaXadrez
	 porque estou desenvolvendo em camadas
	 */
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] peca = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i =0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				peca[i][j] = (PecaXadrez)tabuleiro.peca(i, j);
			}
		}
		return peca;
	}
}
