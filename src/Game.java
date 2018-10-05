import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Inicializa as cores possíveis
		HashSet<Character> colors = new HashSet<Character>();
		colors.add('B'); //Blue
		colors.add('R'); //Red
		colors.add('G'); //Green
		colors.add('Y'); //Yellow
		colors.add('P'); //Purple
		colors.add('O'); //Orange
		
		//Inicializa a string de entrada
		String resposta = new String();
		
		// Define a resposta
		System.out.printf("Insira a sequência de resposta\n> ");
		resposta = leEntrada(resposta, colors);
		
		System.out.println(resposta);
		
		
		
		// Permite 8 tentativas
		
		for (int i = 0; i < 3; i++) {
			System.out.println("Insira a "+i+"ª sequência : \n> ");
			String tentativa = new String();
			tentativa = leEntrada(tentativa, colors);
		    if (checkWin(pontuaSequencia(tentativa, resposta))) {
		    	System.out.println("Você Ganhou =D");
		    	break;
		    }
		} 
		
		
		// Dá o resultado
		System.out.println("Você Perdeu D=");
		
		
		// Encerra tudo
	}
	
	
	private static String leEntrada(String entrada, HashSet<Character> colors) {
		int cont = 0;
		while (!(checkColors(entrada, colors)) || entrada.length() != 4) {
			if(cont > 0) {
				System.out.printf("Insira uma sequência válida\n> ");
			}
			cont++;
			try{
		        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		        entrada = bufferRead.readLine();

		    }
		    catch(IOException e)
		    {
		        e.printStackTrace();
		    }
		}
		
		
		
		
		return entrada;
	}
	
	private static boolean checkColors(String entrada, HashSet<Character> colors) {
		for (int i = 0; i<entrada.length(); i++) {
			if (!(colors.contains(entrada.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	private static ArrayList<Character> pontuaSequencia(String tentativa, String resposta) {
		int i;
		ArrayList<Boolean> checagemTentativa = new ArrayList<Boolean>(4);
		ArrayList<Boolean> checagemSaida = new ArrayList<Boolean>(4);
		
		for(i = 0; i < 4; i++) {
			checagemTentativa.set(i, false);
			checagemSaida.set(i, false);
		}
		
		ArrayList<Character> saida = new ArrayList<Character>();
		
		for(i = 0; i < 4; i++) {
			if(tentativa.charAt(i) == resposta.charAt(i)) {
				saida.add('B');
				checagemTentativa.set(i, true);
				checagemSaida.set(i, true);
			}
		}
		if (checkWin(saida)) {
			return saida;
		}
		for(i = 0; i < 4; i++) {
			if(checagemTentativa.get(i) != true) {
				for(int j = 0; j < 4; j++) {
					if(tentativa.charAt(i) == resposta.charAt(j) && checagemSaida.get(j) != true) {
						saida.add('W');
						checagemTentativa.set(i, true);
						checagemSaida.set(i, true);
					}
				}
			}
		}
		System.out.println(saida);
		return saida;
	}


	private static boolean checkWin(ArrayList<Character> pontos) {
		int cont = 0;
		for (Character c : pontos) {
			if (c.equals('B')) {
				cont++;
			}
		}
		if (cont == 4) {
			return true;
		}
		return false;
	}
	
}