package aplicacao;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
    
		while(true) {
			InterfaceDoUsuario.printTabuleiro(partida.getPecas());
			System.out.println();
			System.out.print("Digite a posicao de origem: ");
			PosicaoXadrez source = InterfaceDoUsuario.leiaPosicaoDoXadrez(sc);
			
			System.out.println();
			System.out.print("Digite a posicao de destino");
			PosicaoXadrez target = InterfaceDoUsuario.leiaPosicaoDoXadrez(sc);
			
			PecaXadrez capturarPeca = partida.executarMovimentoDeXadrez(source, target);	
		}
	}
}
