package maze.logic;
/**
* Classe pertencente ao MethodFactory que cria tabuleiros tais como os da primeira aula pratica
* @author Tiago Mota ei09068
*/
public class BoardDefault extends BoardFactory{
	

	
	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * override a função que implementa o Factory Method mas nesta classe cria tabuleiros default(primeira aula pratica)
	 */
	public Board create(int tamanho, int dragoes, int tipo_dragao) {
		Board board = new Board();
		board.setTipo_dragao(tipo_dragao);
		board.generateStaticBoard();
		return board;
	}
}
