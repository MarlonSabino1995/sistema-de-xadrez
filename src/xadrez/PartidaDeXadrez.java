package xadrez;

import jogoDeTabuleiro.Peca;
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
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.posicionar();
		validarPosicaoDeOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaXadrez executarMovimentoDeXadrez(PosicaoXadrez posOrigem, PosicaoXadrez posDestino) {
		Posicao source = posOrigem.posicionar();
		Posicao target = posDestino.posicionar();
		validarPosicaoDeOrigem(source);
		validarPosicaoDeDestino(source,target);
		Peca capturaDePeca = facaMovimento(source, target);
		return (PecaXadrez )capturaDePeca;
	}
	
	private void validarPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.existePosicao(posicao)) {
			throw new XadrezException("Não existe peça na posicao de origem !");
		}
		if(!tabuleiro.peca(posicao).existeAlgumaMovimentacaoPossivel()) {
			throw new XadrezException("Não existe movimentos possivel para a peça escolhida !");
		}
	}
	
	private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
			throw new XadrezException("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	
	private Peca facaMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino); 
		tabuleiro.lugarDaPeca(p, destino);
		return pecaCapturada;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
	}
	
	
	
	private void configuracaoInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
}
