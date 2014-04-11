package maze.logic;
/**
* Classe responsavel por celulas do tipo Dragao
* @author Tiago Mota ei09068
*/
public class Dragon extends Cell {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean asleep;
	private boolean died;
	private boolean os; // em cima da espada
	private int sleepturns;
	/**
	 * Construtor da classe
	 * @param x coordenada x do dragao
	 * @param y coordenada y do dragao
	 * @param asleep boolean que diz se o dragao esta a dormir ou nao
	 * @param died boolean que diz se o dragao esta morto ou nao
	 * @param os boolean que diz se o dragao esta em cima da espada ou nao
	 */
	public Dragon(int x, int y, boolean asleep, boolean died, boolean os) {
		super(x, y);
		
		this.asleep = asleep;
		this.died = died;
		this.os = os;
		sleepturns = 0;
	}
	/**
	 * 
	 * @return numero de turnos em que o dragao está a dormir
	 */
	public int getSleepturns() {
		return sleepturns;
	}
	/**
	 * 
	 * @param sleepturns define os turnos que um dragao ira dormir com o valor sleepturns
	 */
	public void setSleepturns(int sleepturns) {
		this.sleepturns = sleepturns;
	}
	/**
	 * 
	 * @return boolean se o dragao se encontra em cima da espada
	 */
	public boolean isOs() {
		return os;
	}
	/**
	 * 
	 * @param os define se o dragao se encontra em cima da espada ou nao
	 */
	public void setOs(boolean os) {
		this.os = os;
	}
	/**
	 * 
	 * @return boolean sobre se o dragao esta a dormir ou nao
	 */
	public boolean isAsleep() {
		return asleep;
	}
	/**
	 * 
	 * @param asleep define se o dragao esta a dormir ou nao de acordo com o parametro asleep
	 */
	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}
	/**
	 * 
	 * @return boolean sobre se o dragao esta vivo ou morto
	 */
	public boolean isDead() {
		return died;
	}
	/**
	 * 
	 * @param died define se o dragao esta vivo ou morto de acordo com a variavel died
	 */
	public void setDied(boolean died) {
		this.died = died;
	}
	
	
	
}
