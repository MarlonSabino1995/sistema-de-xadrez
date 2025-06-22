package xadrez;

import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	
	/* O tabuleiro tem uma matriz de peças, quem tem que saber a dimensão do 
	do xandrez é a classe partidaDeXadres*/
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configuracaoInicial();
	}
	 
	/*
	 Irá retornar uma matriz de PecaXadrez correspondente a essa partida,
	 porque estou na camada de xadrez 	 para o programa eu não quero 
	 liberar as peças do tipo Peca mas sim, PecaXadrez porque estou 
	 desenvolvendo em camadas
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
	
	private void configuracaoInicial() {
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.Branco), new Posicao(7, 0));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.Branco), new Posicao(0, 3));
	}
}
