package maze.logic;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;
import java.util.Stack;
import maze.cli.Jogo;
/**
 * Classe principal da logica onde sao criados tabuleiro e sao feitos os movimentos do heroi e dragoes
 * @author Tiago Mota ei09068
 */

public class Board implements Serializable{

	private static final long serialVersionUID = 1L;
	Random rand = new Random();
	private char board[][];
	private int size;
	private Cell Pos = new Cell(0,0);
	private Cell checkPos = new Cell(0,0);
	private Cell S = new Cell(0,0);
	private int choice;
	private char norte = 'N';
	private char sul = 'S';
	private char este = 'E';
	private char oeste = 'O';
	private Hero PosH = new Hero(0,0,false);
	
	public Hero getPosH() {
		return PosH;
	}

	private int nrDrags = 1;
	private Sword espada = new Sword(0,0);
	private int tipo_dragao;

	private int accu = 0;
	private int ok = 10; // variavel que ira realizar a verificacao se o dragao se encontra em cima da espada.
	Vector<Dragon> dragoes = new Vector<Dragon>();
	Jogo jogo = new Jogo();
	/**
	 * 
	 * @return o tipo de movimento do dragao
	 */
	public int getTipo_dragao() {
		return tipo_dragao;
	}
	/**
	 * 
	 * @param tipo_dragao parametro que define o movimento que o dragao ira ter
	 */
	public void setTipo_dragao(int tipo_dragao) {
		this.tipo_dragao = tipo_dragao;
	}

	/**
	 * 
	 * @return o valor do acumulador dos turnos que o dragao tem de dormir
	 */
	public int getAccu() {
		return accu;
	}


	/**
	 * 
	 * @return do tamanho do Board
	 */
	public int getSize() {
		return size;
	}
	/**
	 * 
	 * @param size define o tamanho do Board com o valor que esta neste parametro
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * Construtor vazio para a classe Board
	 */
	public Board(){

	}
	/**
	 * Construtor da classe Board que define logo o tamanho assim como a matriz de tabuleiro(board) 
	 * @param size
	 */
	public Board(int size){
		this.size=size;

		board = new char[size][size];

	}

	/**
	 * Função que cria um labirinto exemplo(primeira aula pratica)
	 */
	public void generateStaticBoard(){

		char tab[][] = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', ' '},
				{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
				{'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

		setBoard(tab);

		S = new Cell(9,5);
		PosH = new Hero(1,1, false);
		dragoes.add(new Dragon(1,3, false, false, false));
		espada = new Sword(1,8);
		setCellH(PosH);
		setCellSword(espada);
		setCellS(S);
		for(int i=0; i< dragoes.size(); i++)
			setCellD(dragoes.get(i));
		setSize(10);

	}

	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) a saida
	 * @param celula
	 */
	public void setCellS(Cell celula){
		board[celula.getY()][celula.getX()] = 'S';
	}
	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) uma parede
	 * @param celula
	 */
	public void setCellX(Cell celula){
		board[celula.getY()][celula.getX()] = 'X';
	}
	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) um espaço em branco
	 * @param celula
	 */
	public void setCellE(Cell celula){
		board[celula.getY()][celula.getX()] = ' ';
	}
	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) um heroi
	 * @param celula
	 */
	public void setCellH(Hero celula){
		if(celula.isArmed())
			board[celula.getY()][celula.getX()] = 'A';
		else
			board[celula.getY()][celula.getX()] = 'H';
	}
	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) um dragão(pode estar a dormir ou nao)
	 * @param celula
	 */
	public void setCellD(Dragon celula){

		for(int i=0; i < dragoes.size(); i++){
			if(!dragoes.get(i).isDead()){

				if(dragoes.get(i).isAsleep() == true)
					board[celula.getY()][celula.getX()] = 'd';
				else
					board[celula.getY()][celula.getX()] = 'D';


			}
		}
	}
	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) um F que significa que o dragao esta em cima da espada
	 * @param celula
	 */
	public void setCellF(Dragon celula){
		for(int i=0; i < dragoes.size(); i++){
			if(!dragoes.get(i).isDead()){
				board[celula.getY()][celula.getX()] = 'F';
			}
		}
	}
	/**
	 * Função que coloca na matriz tabuleiro na posição dada pela celula(parametro) uma espada
	 * @param celula
	 */
	public void setCellSword(Sword celula){
		if(!PosH.isArmed())
			board[celula.getY()][celula.getX()] = 'E';

	}
	/**
	 * função responsavel por criar uma saida num dos lados do tabuleiro, aleatoriamente 
	 * @return a direccao que a saida esta a apontar
	 */
	public char createExit() {
		Random rand = new Random();
		choice = rand.nextInt(4) ;
		char dir = ' ';

		if(choice == 0){
			S.setY(rand.nextInt(size-3)+1);
			S.setX(0);
			setCellS(S);
			dir = oeste;

		}

		if(choice == 1){
			S.setY(rand.nextInt(size-3)+1);
			S.setX(size-1);
			setCellS(S);
			dir = este;

		}

		if(choice == 2){
			S.setX(rand.nextInt(size-3)+1);
			S.setY(0);
			setCellS(S);
			dir = norte;

		}

		if(choice == 3){
			S.setX(rand.nextInt(size-3)+1);
			S.setY(size-1);
			setCellS(S);
			dir = sul;

		}
		Pos.setX(S.getX());
		Pos.setY(S.getY());
		return dir;


	}


	/**
	 * funçao responsavel por criar labirintos aleatorios.
	 */
	public void generateBoard(){


		char dir = ' ';
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = 'X';
			}
		}


		dir = createExit();

		switch(dir){
		case 'N':
			checkPos.setY(1);
			checkPos.setX(Pos.getX());
			break;
		case 'S':
			checkPos.setY(size-2);
			checkPos.setX(Pos.getX());
			break;
		case 'O':
			checkPos.setY(Pos.getY());
			checkPos.setX(1);
			break;
		case 'E':
			checkPos.setY(Pos.getY());
			checkPos.setX(size-2);
			break;
		}

		Stack<Cell> Visitados = new Stack<Cell>();





		board[checkPos.getY()][checkPos.getX()] = ' ';
		Cell StackPos = new Cell(checkPos.getX(), checkPos.getY());
		Visitados.push(StackPos);


		char[] moves = { ' ', ' ', ' ', ' ' };
		char orie = randomSc();

		while (!Visitados.empty()) {

			checkPos.setX(Visitados.peek().getX());
			checkPos.setY(Visitados.peek().getY());

			if (moves[3] != ' ') {
				moves[0] = ' ';
				moves[1] = ' ';
				moves[2] = ' ';
				moves[3] = ' ';
				Visitados.pop();
				continue;
			}

			for (int i = 0; i < 4; i++) {
				if (orie == moves[i]) {

					orie = randomSc();
					break;
				} else if (moves[i] == ' ') {
					moves[i] = orie;
					break;
				}
			}

			switch (orie) {
			case 'N':
				if (checkPos.getY() - 2 >= 0 && checkPos.getX() + 1 < size && checkPos.getX() - 1 >= 0) {
					if ((board[checkPos.getY() - 2][checkPos.getX()] == 'X')
							&& (board[checkPos.getY() - 2][checkPos.getX() + 1] == 'X')
							&& (board[checkPos.getY() - 2][checkPos.getX() - 1] == 'X')
							&& (board[checkPos.getY() - 1][checkPos.getX() - 1] == 'X')
							&& (board[checkPos.getY() - 1][checkPos.getX() + 1] == 'X')
							&& board[checkPos.getY() - 1][checkPos.getX()] == 'X') {
						board[checkPos.getY() - 1][checkPos.getX()] = ' ';
						checkPos.setY(checkPos.getY() - 1);
						StackPos = new Cell(checkPos.getX(), checkPos.getY());
						Visitados.push(StackPos);

						moves[0] = ' ';
						moves[1] = ' ';
						moves[2] = ' ';
						moves[3] = ' ';
					}
				}
				break;
			case 'S':
				if (checkPos.getX()- 1 >= 0 && checkPos.getX() + 1 < size && checkPos.getY() + 2 < size) {
					if (board[checkPos.getY() + 2][checkPos.getX()] == 'X'
						&& board[checkPos.getY() + 2][checkPos.getX() + 1] == 'X'
							&& board[checkPos.getY() + 2][checkPos.getX() - 1] == 'X'
								&& board[checkPos.getY() + 1][checkPos.getX() - 1] == 'X'
									&& board[checkPos.getY() + 1][checkPos.getX() + 1] == 'X'
										&& board[checkPos.getY() + 1][checkPos.getX()] == 'X') {
						board[checkPos.getY() + 1][checkPos.getX()] = ' ';
						checkPos.setY(checkPos.getY() + 1);
						StackPos = new Cell(checkPos.getX(), checkPos.getY());
						Visitados.push(StackPos);

						moves[0] = ' ';
						moves[1] = ' ';
						moves[2] = ' ';
						moves[3] = ' ';
					}
				}
				break;
			case 'O':
				if (checkPos.getX() - 2 >= 0 && checkPos.getY() - 1 >= 0 && checkPos.getY() + 1 < size) {
					if (board[checkPos.getY()][checkPos.getX() - 2] == 'X'
						&& board[checkPos.getY()][checkPos.getX() - 2] == 'X'
							&& board[checkPos.getY() + 1][checkPos.getX() - 2] == 'X'
								&& board[checkPos.getY() - 1][checkPos.getX() - 1] == 'X'
									&& board[checkPos.getY() + 1][checkPos.getX() - 1] == 'X'
										&& board[checkPos.getY()][checkPos.getX() - 1] == 'X') {
						board[checkPos.getY()][checkPos.getX() - 1] = ' ';
						checkPos.setX(checkPos.getX() - 1);
						StackPos = new Cell(checkPos.getX(), checkPos.getY());
						Visitados.push(StackPos);

						moves[0] = ' ';
						moves[1] = ' ';
						moves[2] = ' ';
						moves[3] = ' ';
					}
				}
				break;
			case 'E':
				if (checkPos.getX() + 2 < size && checkPos.getY() + 1 < size && checkPos.getY() - 1 >= 0) {
					if (board[checkPos.getY()][checkPos.getX() + 2] == 'X'
						&& board[checkPos.getY() - 1][checkPos.getX() + 2] == 'X'
							&& board[checkPos.getY() + 1][checkPos.getX() + 2] == 'X'
								&& board[checkPos.getY() - 1][checkPos.getX() + 1] == 'X'
									&& board[checkPos.getY() + 1][checkPos.getX() + 1] == 'X'
										&& board[checkPos.getY()][checkPos.getX() + 1] == 'X') {
						board[checkPos.getY()][checkPos.getX() + 1] = ' ';
						checkPos.setX(checkPos.getX() + 1);
						StackPos = new Cell(checkPos.getX(), checkPos.getY());
						Visitados.push(StackPos);

						moves[0] = ' ';
						moves[1] = ' ';
						moves[2] = ' ';
						moves[3] = ' ';
					}
				}
				break;
			}
			orie = randomSc();
		}
		setBoard(board);




		setHeroPos();
		setSwordPos();
		if(jogo.getCMD() == 1)
			setNrDrags(jogo.getNrDragoes());

		createDragonsVec(nrDrags);
		int sebuxo = 0;
		while(sebuxo != getDragoes().size()){
			setDragonPos(sebuxo);
			sebuxo++;
		}



	}
	/**
	 * Função usada para escolher uma direcçao a seguir
	 * @return de uma direccao
	 */
	public char randomSc() {
		int pa = rand.nextInt(4);
		char res = ' ';
		if( pa == 0)
			res = norte;
		if( pa == 1)
			res = sul;
		if( pa == 2)
			res = oeste;
		if( pa == 3)
			res = este;

		return res;
	}

	/**
	 * Função usada para obter o numero de dragoes
	 * @return o valor da variavel nrDrags
	 */
	public int getNrDrags() {
		return nrDrags;
	}
	/**
	 * Define a variavel nrDrags com o valor passado em parametro
	 * @param nrDrags
	 */
	public void setNrDrags(int nrDrags) {
		this.nrDrags = nrDrags;
	}
	/**
	 * 
	 * @return matriz de tabuleiro char[][]
	 */
	public char[][] getBoard() {
		return board;
	}
	/**
	 * coloca o tabuleiro char[][] com o char passado em parametro
	 * @param board
	 */
	public void setBoard(char[][] board) {
		this.board = board;
	}

	/**
	 * Função que coloca aleatoriamente o heroi na matriz tabuleiro
	 */
	public void setHeroPos(){
		Hero novo;
		int sebuxo=0;
		while(sebuxo != 1){
			PosH.setX(rand.nextInt(size-3)+1);
			PosH.setY(rand.nextInt(size-3)+1);
			if(board[PosH.getY()][PosH.getX()] == ' ')
				sebuxo = 1;
		}
		novo = new Hero(PosH.getX(),PosH.getY(), false);

		setCellH(novo);


	}


	/**
	 * Função que coloca aleatoriamente os dragoes na matriz tabuleiro
	 */
	public void setDragonPos(int i){
		Dragon novo;
		int sebuxo = 0;
		while(sebuxo != 1){
			dragoes.get(i).setX(rand.nextInt(size-3)+1);
			dragoes.get(i).setY(rand.nextInt(size-3)+1);
			if(board[dragoes.get(i).getY()][dragoes.get(i).getX()] == ' ')
				sebuxo = 1;
		}
		novo = new Dragon(dragoes.get(i).getX(),dragoes.get(i).getY(),false, false, false);
		setCellD(novo);
	}
	/**
	 * Função que coloca aleatoriamente a espada na matriz tabuleiro
	 */
	public void setSwordPos(){
		Sword novo;
		int sebuxo = 0;
		while(sebuxo != 1){
			espada.setX(rand.nextInt(size-3)+1);
			espada.setY(rand.nextInt(size-3)+1);
			if(board[espada.getY()][espada.getX()] == ' ')
				sebuxo = 1;
		}
		novo = new Sword(espada.getX(),espada.getY());
		setCellSword(novo);
	}

	/**
	 * Função usada para fazer o dragao se mexer ou nao, dependendo do valor que é passado em parametro
	 * @param escDrag
	 * @return um boolean que define se o dragao matou o heroi ou nao
	 */
	public boolean moveDragon(int escDrag){

		for(int i=0; i< dragoes.size(); i++){



			if(escDrag == 2){
				Vector<Integer> opcoes = new Vector<Integer>();
				if(!dragoes.get(i).isDead()){
					if(board[dragoes.get(i).getY()-1][dragoes.get(i).getX()] == ' ' || board[dragoes.get(i).getY()-1][dragoes.get(i).getX()] == 'E')
						opcoes.add(1);
					if(board[dragoes.get(i).getY()+1][dragoes.get(i).getX()] == ' ' || board[dragoes.get(i).getY()+1][dragoes.get(i).getX()] == 'E')
						opcoes.add(2);	
					if(board[dragoes.get(i).getY()][dragoes.get(i).getX()-1] == ' ' || board[dragoes.get(i).getY()][dragoes.get(i).getX()-1] == 'E')
						opcoes.add(3);	
					if(board[dragoes.get(i).getY()][dragoes.get(i).getX()+1] == ' ' || board[dragoes.get(i).getY()][dragoes.get(i).getX()+1] == 'E')
						opcoes.add(4);

					int go = opcoes.get(rand.nextInt(opcoes.size()));
					opcoes.clear();
					switch(go){
					case 1:
						setCellSword(espada);

						if( board[dragoes.get(i).getY()-1][dragoes.get(i).getX()] == 'E'){
							dragoes.get(i).setOs(true);
						}

						setCellE(dragoes.get(i));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(i).setY(dragoes.get(i).getY()-1);
						if(dragoes.get(i).getY() == espada.getY() && dragoes.get(i).getX() == espada.getX()){
							setCellF(dragoes.get(i));
							ok = 1;
						}
						else
							setCellD(dragoes.get(i));
						dragoes.get(i).setOs(false);
						break;

					case 2:
						setCellSword(espada);

						if( board[dragoes.get(i).getY()+1][dragoes.get(i).getX()] == 'E'){
							dragoes.get(i).setOs(true);
						}


						setCellE(dragoes.get(i));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(i).setY(dragoes.get(i).getY()+1);
						if(dragoes.get(i).getY() == espada.getY() && dragoes.get(i).getX() == espada.getX()){
							setCellF(dragoes.get(i));
							ok = 1;
						}
						else
							setCellD(dragoes.get(i));

						dragoes.get(i).setOs(false);
						break;

					case 3:
						setCellSword(espada);
						if( board[dragoes.get(i).getY()][dragoes.get(i).getX()-1] == 'E'){
							dragoes.get(i).setOs(true);
						}

						setCellE(dragoes.get(i));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(i).setX(dragoes.get(i).getX()-1);
						if(dragoes.get(i).getY() == espada.getY() && dragoes.get(i).getX() == espada.getX()){
							setCellF(dragoes.get(i));
							ok = 1;
						}
						else
							setCellD(dragoes.get(i));
						dragoes.get(i).setOs(false);
						break;

					case 4:
						setCellSword(espada);
						if( board[dragoes.get(i).getY()][dragoes.get(i).getX()+1] == 'E'){
							dragoes.get(i).setOs(true);
						}

						setCellE(dragoes.get(i));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(i).setX(dragoes.get(i).getX()+1);
						if(dragoes.get(i).getY() == espada.getY() && dragoes.get(i).getX() == espada.getX()){
							setCellF(dragoes.get(i));
							ok = 1;
						}
						else
							setCellD(dragoes.get(i));
						dragoes.get(i).setOs(false);

						break;

					case 0:
						break;



					}
					for(int j=0; j<dragoes.size();j++){
						if(dragoes.get(j).getX() == espada.getX() && dragoes.get(j).getY() == espada.getY())
							setCellF(dragoes.get(j));
					}
				}
				opcoes.clear();
			}

//			if(dragoes.get(i).getY() == PosH.getY()){
//				if(dragoes.get(i).getX() - PosH.getX() == 1 ||  dragoes.get(i).getX() - PosH.getX() == -1){
//					if(!PosH.isArmed()){
//						if(jogo.getCMD() == 1)
//							jogo.prinLostGame();
//						return true;
//
//					}
//				}
//			}
//			if(dragoes.get(i).getX() == PosH.getX()){
//				if(dragoes.get(i).getY() - PosH.getY() == 1 ||  dragoes.get(i).getY() - PosH.getY() == -1){
//					if(!PosH.isArmed()){
//
//						if(jogo.getCMD() == 1)
//							jogo.prinLostGame();
//						return true;
//					}
//				}
//			}
			
			if(dragoes.get(i).isAsleep() == false){
				double distQuad=(dragoes.get(i).getX() - PosH.getX())*(dragoes.get(i).getX() - PosH.getX())+(dragoes.get(i).getY() - PosH.getY())*(dragoes.get(i).getY() - PosH.getY());
				if(distQuad<=1){
					if(!PosH.isArmed()){
						if(jogo.getCMD() == 1 )
							jogo.prinLostGame();
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Função que realiza o movimento do heroi e tambem verifica se pode matar o dragao ou nao
	 * @param mov
	 * @return 0 para prosseguir o jogo
	 * @return 1 quando o utilizador ganha
	 * @return 2 quando o utilizador nao ganha por erro
	 */

	public int moveHero(int mov){
		switch(mov){
		case 1:
			if(board[PosH.getY()-1][PosH.getX()] == 'S'){
				if(PosH.isArmed()){

					if(jogo.getCMD()==1)
						jogo.printWiningMessage();
					return 1;
				}
				else{

					if(jogo.getCMD() == 1)
						jogo.printErrorWining();
					return 2;
				}
			}

			if(board[PosH.getY()-1][PosH.getX()] == ' ' ||  board[PosH.getY()-1][PosH.getX()] == 'E'){
				setCellE(PosH);
				PosH.setY(PosH.getY()-1);
				if(PosH.getX() == espada.getX() && PosH.getY() == espada.getY())
					PosH.setArmed(true);
				setCellH(PosH);
			}

			break;
		case 2:
			if(board[PosH.getY()+1][PosH.getX()] == 'S'){
				if(PosH.isArmed()){
					if(jogo.getCMD()==1)
						jogo.printWiningMessage();
					return 1;
				}
				else{
					if(jogo.getCMD() == 1)
						jogo.printErrorWining();
					return 2;
				}
			}

			if(board[PosH.getY()+1][PosH.getX()] == ' ' ||  board[PosH.getY()+1][PosH.getX()] == 'E'){
				setCellE(PosH);
				PosH.setY(PosH.getY()+1);
				if(PosH.getX() == espada.getX() && PosH.getY() == espada.getY())
					PosH.setArmed(true);
				setCellH(PosH);
			}

			break;
		case 3:
			if(board[PosH.getY()][PosH.getX()-1] == 'S'){
				if(PosH.isArmed()){
					if(jogo.getCMD()==1)
						jogo.printWiningMessage();
					return 1;
				}
				else{
					if(jogo.getCMD() == 1)
						jogo.printErrorWining();
					return 2;
				}
			}

			if(board[PosH.getY()][PosH.getX()-1] == ' ' ||  board[PosH.getY()][PosH.getX()-1] == 'E'){
				setCellE(PosH);
				PosH.setX(PosH.getX()-1);
				if(PosH.getX() == espada.getX() && PosH.getY() == espada.getY())
					PosH.setArmed(true);
				setCellH(PosH);
			}

			break;

		case 4:
			if(board[PosH.getY()][PosH.getX()+1] == 'S'){
				if(PosH.isArmed()){
					if(jogo.getCMD()==1)
						jogo.printWiningMessage();
					return 1;
				}
				else{
					if(jogo.getCMD() == 1)
						jogo.printErrorWining();
					return 2;
				}
			}

			if(board[PosH.getY()][PosH.getX()+1] == ' ' || board[PosH.getY()][PosH.getX()+1] == 'E'){

				setCellE(PosH);
				PosH.setX(PosH.getX()+1);
				if(PosH.getX() == espada.getX() && PosH.getY() == espada.getY())
					PosH.setArmed(true);
				setCellH(PosH);
			}

			break;

		case 5:
			break;

		case 0:
			jogo.printGoodByeMessage();


		}
		for(int i = 0; i < dragoes.size(); i++){

			if(dragoes.get(i).getY() == PosH.getY()){
				if(dragoes.get(i).getX() - PosH.getX() == 1 ||  dragoes.get(i).getX() - PosH.getX() == -1){
					if(PosH.isArmed()){

						setCellE(dragoes.get(i));
						dragoes.get(i).setDied(true);
					}
				}
			}
			if(dragoes.get(i).getX() == PosH.getX()){
				if(dragoes.get(i).getY() - PosH.getY() == 1 ||  dragoes.get(i).getY() - PosH.getY() == -1){
					if(PosH.isArmed()){
						setCellE(dragoes.get(i));
						dragoes.get(i).setDied(true);
					}
				}
			}
		}

		return 0;

	}
	/**
	 * Função que cria o vector onde irao estao todos os dragoes que o utilizador quiser utilizar no jogo
	 * @param nr
	 */
	public void createDragonsVec(int nr){

		for(int i=0; i < nr; i++){
			Dragon novo = new Dragon(0,0,false,false,false);
			dragoes.add(novo);
		}

	}
	/**
	 * Função que devolve o vector onde estao os dragoes que estao a ser utilizados em jogo
	 * @return Vector<Dragon>
	 */
	public Vector<Dragon> getDragoes() {
		return dragoes;
	}

	/**
	 * Função usada para o movimento aleatorio de um dragao que pode adormecer durante accu turnos
	 * @return true quando o dragao come o heroi
	 */

	public boolean moveDragonSleeper() {
		int moveDragoes=0;
		while(moveDragoes < dragoes.size()){

			Vector<Integer> opcoes = new Vector<Integer>();
			if(!dragoes.get(moveDragoes).isDead()){
				if(board[dragoes.get(moveDragoes).getY()-1][dragoes.get(moveDragoes).getX()] == 'H' || board[dragoes.get(moveDragoes).getY()-1][dragoes.get(moveDragoes).getX()] == ' ' || board[dragoes.get(moveDragoes).getY()-1][dragoes.get(moveDragoes).getX()] == 'E')
					opcoes.add(1);
				if(board[dragoes.get(moveDragoes).getY()+1][dragoes.get(moveDragoes).getX()] == 'H' || board[dragoes.get(moveDragoes).getY()+1][dragoes.get(moveDragoes).getX()] == ' ' || board[dragoes.get(moveDragoes).getY()+1][dragoes.get(moveDragoes).getX()] == 'E')
					opcoes.add(2);	
				if(board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()-1] == 'H' || board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()-1] == ' ' || board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()-1] == 'E')
					opcoes.add(3);	
				if(board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()+1] == 'H' || board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()+1] == ' ' || board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()+1] == 'E')
					opcoes.add(4);

				opcoes.add(5);


				int go = 10;

				if(accu == 0){
					dragoes.get(moveDragoes).setAsleep(false);
					go = opcoes.get(rand.nextInt(opcoes.size()));

					switch(go){
					case 1:
						setCellSword(espada);
						if( board[dragoes.get(moveDragoes).getY()-1][dragoes.get(moveDragoes).getX()] == 'E')
							dragoes.get(moveDragoes).setOs(true);


						setCellE(dragoes.get(moveDragoes));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(moveDragoes).setY(dragoes.get(moveDragoes).getY()-1);
						if(dragoes.get(moveDragoes).getY() == espada.getY() && dragoes.get(moveDragoes).getX() == espada.getX()){
							setCellF(dragoes.get(moveDragoes));
							ok = 1;
						}
						else
							setCellD(dragoes.get(moveDragoes));

						dragoes.get(moveDragoes).setOs(false);
						break;

					case 2:
						setCellSword(espada);
						if( board[dragoes.get(moveDragoes).getY()+1][dragoes.get(moveDragoes).getX()] == 'E')
							dragoes.get(moveDragoes).setOs(true);


						setCellE(dragoes.get(moveDragoes));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(moveDragoes).setY(dragoes.get(moveDragoes).getY()+1);
						if(dragoes.get(moveDragoes).getY() == espada.getY() && dragoes.get(moveDragoes).getX() == espada.getX()){
							setCellF(dragoes.get(moveDragoes));
							ok = 1;
						}
						else
							setCellD(dragoes.get(moveDragoes));

						dragoes.get(moveDragoes).setOs(false);
						break;

					case 3:
						setCellSword(espada);
						if( board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()-1] == 'E')
							dragoes.get(moveDragoes).setOs(true);


						setCellE(dragoes.get(moveDragoes));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(moveDragoes).setX(dragoes.get(moveDragoes).getX()-1);
						if(dragoes.get(moveDragoes).getY() == espada.getY() && dragoes.get(moveDragoes).getX() == espada.getX()){
							setCellF(dragoes.get(moveDragoes));
							ok = 1;
						}
						else
							setCellD(dragoes.get(moveDragoes));
						dragoes.get(moveDragoes).setOs(false);
						break;

					case 4:
						setCellSword(espada);
						if( board[dragoes.get(moveDragoes).getY()][dragoes.get(moveDragoes).getX()+1] == 'E')
							dragoes.get(moveDragoes).setOs(true);

						setCellE(dragoes.get(moveDragoes));
						if(ok == 1){
							if(!PosH.isArmed()){
								setCellSword(espada);
							}
						}
						dragoes.get(moveDragoes).setX(dragoes.get(moveDragoes).getX()+1);
						if(dragoes.get(moveDragoes).getY() == espada.getY() && dragoes.get(moveDragoes).getX() == espada.getX()){
							setCellF(dragoes.get(moveDragoes));
							ok = 1;
						}
						else
							setCellD(dragoes.get(moveDragoes));
						dragoes.get(moveDragoes).setOs(false);
						break;

					case 5:
						accu=5+rand.nextInt(3);
						if(jogo.getCMD() == 1)
							jogo.printSleepMessage();
						dragoes.get(moveDragoes).setAsleep(true);
						setCellD(dragoes.get(moveDragoes));
						break;

					case 0:
						break;

					}
				}

				else{
					dragoes.get(moveDragoes).setAsleep(true);				
					accu--;

				}
			}
			opcoes.clear();


			if(dragoes.get(moveDragoes).isAsleep() == false){
				double distQuad=(dragoes.get(moveDragoes).getX() - PosH.getX())*(dragoes.get(moveDragoes).getX() - PosH.getX())+(dragoes.get(moveDragoes).getY() - PosH.getY())*(dragoes.get(moveDragoes).getY() - PosH.getY());
				if(distQuad<=1){
					if(!PosH.isArmed()){
						if(jogo.getCMD() == 1 )
							jogo.prinLostGame();
						return true;
					}
				}

			}
			moveDragoes++;
		}

		return false;
	}

}
