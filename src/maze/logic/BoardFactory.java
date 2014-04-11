package maze.logic;
/**
* Classe que implementa o MethodFactory para cria��o de tabuleiros
* @author Tiago Mota ei09068
*/
import java.io.Serializable;

public abstract class BoardFactory implements Serializable {
	/**
	 * Implementa��o do Factory Method para cria��o de tabuleiros
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param tamanho tamanho do labirinto a gerar
	 * @param dragoes numero de dragoes a criar
	 * @param tipo_dragao tipo de movimento que os dragoes irao ter
	 * @return Board um objecto do tipo Board
	 */
	public abstract Board create(int tamanho,int dragoes, int tipo_dragao );
}
