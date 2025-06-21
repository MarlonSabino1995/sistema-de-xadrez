package jogoDeTabuleiro;

public class Peca {

	/*
	 Criei a posição como protected, porque ele ainda
	 não é a posição do xadrez, é apenas uma posição simples
	 de matriz e não quero que essa posição seja visivel na camada do xadrez
	 */
	protected Posicao posicao;
	
	private Tabuleiro tabuleiro;

	/*
	 Passo apenas o tabuleiro na hora de criar a peça, porque a posição de uma
	 peça recem criada ela vai ser inicialmente nula dizendo que essa peça não foi
	 colocada no tabuleiro ainda
	 */
	public Peca(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	
	}

	/*
	 somente classes do mesmo pacote e sub classes vão poder acessar o tabuleiro
	 de uma peça, não vou querer que o tabuleiro seja acessivel pela camada de xadrez,
	 esse tabuleiro é de uso interno da camada de tabuleiro
	 */
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	
}
