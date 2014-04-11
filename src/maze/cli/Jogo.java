package maze.cli;

import java.io.Serializable;
import java.util.Scanner;

import maze.logic.*;


/**
* Classe responsavel pela user interface na consola
* @author Tiago Mota ei09068
*/
public class Jogo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static int nrDrags = 0;
	private static int cmd = 0;
	/**
	 * Funçao que nos diz se esta a ser usada linha de comandos ou nao
	 * @return 1 ou 0
	 */
	public int getCMD(){
		return cmd;
	}
	/**
	 * Construtor vazio da classe de user interface
	 */
	public Jogo() {

	}
	/**
	 * Funcao usada para imprimir texto na consola de acordo com o que for passado em parametro
	 * @param s
	 */
	public void printText(String s){
		System.out.println(s + "\n");
	}
       /**
        * 
        * @return o input dado pelo user quando ele selecciona uma das opcoes disponiveis para mover o heroi
        */
        
	public int getDirHero(){
		//Opcoes disponiveis para mover o heroi
		System.out.println("Menu de movimentos: ");
		System.out.println("\t1-Cima \n\t2-Baixo \n\t3-Esquerda \n\t4-Direita \n\t5-Manter \n\t0-Sair");
		Scanner s = new Scanner(System.in);
		int movimento = s.nextInt();
		while(movimento < 0 && movimento > 5){
			System.out.println("Menu de movimentos: ");
			System.out.println("\t1-Cima \n\t2-Baixo \n\t3-Esquerda \n\t4-Direita \n\t5-Manter \n\t0-Sair");

			movimento = s.nextInt();
		}
		return movimento;
	}
       /**
        * Mensagem de aviso que o Dragao adormeceu
        */
	public void printSleepMessage(){
		System.out.println("\nO dragao acabou de adormecer!\n");
	}
       /** 
        * Mensagem de aviso que Perdeste o jogo e posterior saida automatica do jogo
        */
	public void prinLostGame(){
		System.out.println("\nPerdes-te o jogo!\n");
		System.exit(1);
	}
       /** 
        * Mensagem de aviso que o Heroi tem que estar armado para poder sair do labirinto
        */
	public void printErrorWining(){
		System.out.println("\nTens de ter o Heroi armado para poderes sair do Labirinto!\n");
	}
	/** 
	 * Mensagem de aviso de que o user ganhou o jogo e posterior saida autoimatica
	 */
	public void printWiningMessage(){
		System.out.println("\nParabens! Ganhas-te o jogo!\n");
		System.exit(1);
	}
	/**
	 * Mensagem de despedida quando o jogador seleciona sair do jogo.
	 */
	public void printGoodByeMessage(){
		System.out.println("\nAdeus! Ate breve!\n");
		System.exit(1);
	}
       /**
        * Metodo responsavel pela apresentacao do tabuleiro na consola de acordo com o tabuleiro passado em parametro
        * @param board
        */
	public void printBoard(char[][] board){
		char tab[][] = board;
		for(int i=0; i < tab.length;i++){
			for(int j=0; j < tab.length; j++){
				System.out.print(tab[i][j]);
				System.out.print(' ');
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

	
	/**
	 * Faz o print do menu de opcoes relativas ao movimento dos dragoes
	 */
	public void printMenuDragoes(){
		System.out.println("\nEscolha uma das seguintes opcoes relativas ao Dragao: ");
		System.out.println("\n\t1-Dragao parado\n\t2-Dragao aleatorio\n\t3-Dragao aleatorio que pode adormecer\n");
	}
	/**
	 * Faz o pçrint da mensagem de quantos dragoes o utilizador deseja
	 */
	public void printMenuNrDrags(){
		System.out.println("Quantos dragoes deseja? ");
	}
       /**
        * Metodo main usado como ciclo principal e motor de jogo na consola
        * @param args
        */
	public static void main(String[] args){
		
		Jogo jogo = new Jogo();
		Scanner in = new Scanner(System.in);
		System.out.println("Utilizar exemplo pre-definido[1] ou gerar labirinto aleatorio[2]? ");
		cmd = 1;
		int opt = in.nextInt();

		if(opt == 1){
			BoardFactory fabricaTabuleiro;
			fabricaTabuleiro = new BoardDefault();
			Board tabuleiro = fabricaTabuleiro.create(0, 0, 0);
			jogo.printMenuDragoes();
			int escDrag = in.nextInt(); // variavel de escolha das opcoes do dragao
			int dir = 10;
	
			while(dir != 0){
				jogo.printBoard(tabuleiro.getBoard());
				if(escDrag == 3)
					tabuleiro.moveDragonSleeper();
				else
					tabuleiro.moveDragon(escDrag);
				dir = jogo.getDirHero();
				tabuleiro.moveHero(dir);
								
			}

		}
		else if(opt == 2){
			
			int tam = 2;
			
			BoardFactory fabricaTabuleiro;
			fabricaTabuleiro = new BoardRandom();
			jogo.printMenuNrDrags();
			nrDrags = in.nextInt();
			
			jogo.printMenuDragoes();
			int escDrag = in.nextInt(); // variavel de escolha das opcoes do dragao
			while(tam <6){
				
				System.out.println("Tamanho labirinto [min - 6]: ");
				tam = in.nextInt();
			}
			Board tabuleiro = fabricaTabuleiro.create(tam, nrDrags, escDrag);

			
			int dir = 10;
			while(dir != 0){


				jogo.printBoard(tabuleiro.getBoard());
				

				if(escDrag == 3){
					if(tabuleiro.moveDragonSleeper()){} 
						//jogo.prinLostGame();
				//else
					if(tabuleiro.moveDragon(escDrag)) {}
						//jogo.prinLostGame();
			}

				dir = jogo.getDirHero();
				
				
				
				switch(tabuleiro.moveHero(dir))
				{
				case 1:
					jogo.printWiningMessage();
				case 2:
					jogo.printErrorWining();
					break;
				}

			}

		}
	}
	/**
	 * 
	 * @return o numero de dragoes em jogo
	 */
	public int getNrDragoes(){
		return nrDrags;
	}
}
