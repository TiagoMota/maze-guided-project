package maze.logic;
/**
* Classe que trata das funcoes mais basicas dos objectos que irao ser postos no tabuleiro de jogo
* @author Tiago Mota ei09068
*/
import java.io.Serializable;

public class Cell implements Serializable{

	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	/**
	 * Construtor da classe
	 * @param x coordenada x de uma celula generica
	 * @param y coordenada y de uma celula generica
	 */
	public Cell(int x, int y){
		this.x=x;
		this.y=y;
	}
	/**
	 * 
	 * @return coordenada x da celula
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x serve para definir a coordenada x da celula com o valor da variavel x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return coordenada y da celula 
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y serve para definir a coordenada y da celula com o valor da variavel y
	 */
	public void setY(int y) {
		this.y = y;
	}


}
