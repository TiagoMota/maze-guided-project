package maze.logic;
/**
* Classe responsavel por celulas do tipo Heroi
* @author Tiago Mota ei09068
*/
public class Hero extends Cell {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean armed;
	/**
	 * 
	 * @param x coordenada x do heroi
	 * @param y coordenada y do heroi
	 * @param armed se esta armado ou nao
	 */
	public Hero(int x, int y, boolean armed) {
		super(x, y);
		this.armed = armed;
	}
	/**
	 * 
	 * @return boolean para se o heroi estiver armado ou nao
	 */
	public boolean isArmed() {
		return armed;
	}
	/**
	 * 
	 * @param armed do tipo boolean que define se o heroi está armado ou nao
	 */
	public void setArmed(boolean armed) {
		this.armed = armed;
	}

}
