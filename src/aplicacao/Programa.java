package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Programa {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partida = new PartidaDeXadrez();
		List<PecaXadrez> capturadas = new ArrayList<>();
    
		while(!partida.getCheckMate()) {
			try {
				InterfaceDoUsuario.clearScreen();
				InterfaceDoUsuario.printPartida(partida, capturadas);;
				System.out.println();
				System.out.print("Digite a posicao de origem: ");
				PosicaoXadrez source = InterfaceDoUsuario.leiaPosicaoDoXadrez(sc);
				
				boolean[][] possivelMovimentos = partida.possiveisMovimentos(source);
				InterfaceDoUsuario.clearScreen();
				InterfaceDoUsuario.printTabuleiro(partida.getPecas(),possivelMovimentos);
				
				System.out.println();
				System.out.print("Digite a posicao de destino: ");
				PosicaoXadrez target = InterfaceDoUsuario.leiaPosicaoDoXadrez(sc);
				
				PecaXadrez capturarPeca = partida.executarMovimentoDeXadrez(source, target);
				
				if(capturarPeca != null) {
					capturadas.add(capturarPeca);
				}
				
			}catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		InterfaceDoUsuario.clearScreen();
		InterfaceDoUsuario.printPartida(partida, capturadas);
	}
}
