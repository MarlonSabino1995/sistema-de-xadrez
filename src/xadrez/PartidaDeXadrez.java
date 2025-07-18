package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	private int jogadorDaVez;
	private Cor jogadorAtual;
	
	private boolean check;
	
	private boolean checkMate;
	
	private List<Peca> pecaNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	

	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		jogadorDaVez = 1;
		jogadorAtual = Cor.BRANCO;
		configuracaoInicial();
	}
	
	public int getJogadorDaVez() {
		return jogadorDaVez;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
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
		
		if(testCheck(jogadorAtual)) {
			desfazerMovimento(source, target, capturaDePeca);
			throw new XadrezException("Você não pode se colocar em check !");
		}
		
		check = (testCheck(oponente(jogadorAtual)))?true : false;
		
		if(testCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}else {
			nextJogadorDaVez();
		}
		
		return (PecaXadrez )capturaDePeca;
	}
	
	private void validarPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.existePosicao(posicao)) {
			throw new XadrezException("Não existe peça na posicao de origem !");
		}
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezException("A peça escolhida não é sua !");
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
	
	private void nextJogadorDaVez() {
		jogadorDaVez++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Peca facaMovimento(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
		p.incrementContagem();
		
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarDaPeca(p, destino);
		
		if(pecaCapturada != null) {
			pecaNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	public void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
		p.decrementContagem();
		tabuleiro.lugarDaPeca(p, origem);
		
		if(pecaCapturada != null) {
			tabuleiro.lugarDaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecaNoTabuleiro.add(pecaCapturada);
		}
		
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez rei(Cor cor) {
		
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list) {
			if(p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não há rei "+cor+" no tabuleiro. ");
	}
	
	private boolean testCheckMate(Cor cor) {
		if(! testCheck(cor)) {
			return false;
		}
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list) {
			boolean [][] mat = p.movimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++) {
				for(int j = 0; j<tabuleiro.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().posicionar();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = facaMovimento(origem, destino);
						boolean testCheck = testCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private boolean testCheck (Cor cor) {
		Posicao posicaoDoRei = rei(cor).getPosicaoXadrez().posicionar();
		List<Peca> pecasOponente = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peca p:pecasOponente) { 
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
		pecaNoTabuleiro.add(peca);
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
