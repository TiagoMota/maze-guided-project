package maze.logic;
/**
* Classe pertencente ao MethodFactory que cria tabuleiros aleatorios
* @author Tiago Mota ei09068
*/
public class BoardRandom extends BoardFactory {

	private static final long serialVersionUID = 1L;

	@Override
	/**
	 * override a função que implementa o Factory Method mas que para este caso apenas irá gerar Board's do tipo aleatorio
	 */
	public Board create(int tamanho, int dragoes, int tipo_dragao) {
		Board tabuleiro = new Board();
		tabuleiro.setSize(tamanho);
		tabuleiro.setBoard(new char[tabuleiro.getSize()][tabuleiro.getSize()]);
		tabuleiro.setNrDrags(dragoes);
		tabuleiro.generateBoard();
		tabuleiro.setTipo_dragao(tipo_dragao);
		return tabuleiro;
	}

}
