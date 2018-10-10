import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Inicializa as cores possíveis
		ArrayList<Character> colors = new ArrayList<Character>();
		colors.add('U'); //Blue
		colors.add('R'); //Red
		colors.add('G'); //Green
		colors.add('Y'); //Yellow
		colors.add('P'); //Purple
		colors.add('O'); //Orange
		
		//Inicializa a string de entrada
		String resposta = new String();
		boolean win = false;
		int i = 0;
		
		// Define a resposta
		resposta = randomAnswer(colors);
		System.out.println(resposta);
		
		
		// Permite 8 tentativas
		System.out.printf("Cores possíveis: [ ");
		for (Character c : colors) {
			System.out.printf("%c ", c);
		}
		System.out.println("]");
		for (i = 0; i < 3; i++) {
			int cont = i+1;
			System.out.printf("Insira a "+cont+"ª sequência : \n> ");
			String tentativa = new String();
			tentativa = leEntrada(tentativa, colors);
		    if (checkWin(pontuaSequencia(tentativa, resposta))) {
		    	win = true;
		    	break;
		    }
		} 
		
		
		// Dá o resultado
		System.out.printf("A resposta era: ");
		System.out.println(resposta);
		System.out.printf("Você tentou %d vezes\n", i+1);
		if (win) {
	    	System.out.println("Você Ganhou :3");
		} else {
			System.out.println("Você Perdeu D=");			
		}
		
		
		// Encerra tudo
	}
	
	
	private static String leEntrada(String entrada, ArrayList<Character> colors) {
		int cont = 0;
		while (!(checkColors(entrada, colors)) || entrada.length() != 4) {
			if(cont > 0) {
				System.out.printf("Insira uma sequência válida\n>> ");
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
	
	private static boolean checkColors(String entrada, ArrayList<Character> colors) {
		for (int i = 0; i<entrada.length(); i++) {
			if (!(colors.contains(entrada.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	private static ArrayList<Character> pontuaSequencia(String tentativa, String resposta) {
		int i;
		ArrayList<Boolean> checagemTentativa = new ArrayList<Boolean>();
		ArrayList<Boolean> checagemSaida = new ArrayList<Boolean>();
		
		for(i = 0; i < 4; i++) {
			checagemTentativa.add(i, false);
			checagemSaida.add(i, false);
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
			if (!checagemTentativa.get(i)) {
				for (int j = 0; j < 4; j++) {
					if (!checagemSaida.get(j) && tentativa.charAt(i) == resposta.charAt(j)) {
						saida.add('W');
						checagemSaida.set(i, true);
						checagemSaida.set(j, true);
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
	
	private static String randomAnswer(ArrayList<Character> colors) {
		String saida = new String();
		for(int i = 0; i < 4; i++) {
			int rand = (int) ((Math.random()*100)%6);
			saida += colors.get(rand);
		}
		
		return saida;
	}
	
	
}