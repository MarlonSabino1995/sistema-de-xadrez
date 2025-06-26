package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
    
		while(true) {
			try {
				InterfaceDoUsuario.clearScreen();
				InterfaceDoUsuario.printTabuleiro(partida.getPecas());
				System.out.println();
				System.out.print("Digite a posicao de origem: ");
				PosicaoXadrez source = InterfaceDoUsuario.leiaPosicaoDoXadrez(sc);
				
				System.out.println();
				System.out.print("Digite a posicao de destino");
				PosicaoXadrez target = InterfaceDoUsuario.leiaPosicaoDoXadrez(sc);
				
				PecaXadrez capturarPeca = partida.executarMovimentoDeXadrez(source, target);
			}catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
