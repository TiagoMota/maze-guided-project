/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maze.gui;

/**
* Classe que guarda alguns dados pre-definidos que irao ser usados sempre que aparece o menu gerado pela classe Options
* @author Tiago Mota ei09068
*/
public class OpcaoDoJogo {
        private int tamanhoLabirinto = 10;
        private int numeroDragao = 1;
        private int tipo_dragao;
       
        private int tipoLab = 2;
        

    /**
     * @return o tamanho do Labirinto
     */
    public int getTamanhoLabirinto() {
        return tamanhoLabirinto;
    }

    /**
     * @param tamanhoLabirinto que será usado para definir o tamanho do labirinto
     */
    public void setTamanhoLabirinto(int tamanhoLabirinto) {
        this.tamanhoLabirinto = tamanhoLabirinto;
    }

    /**
     * @return o numero de dragoes
     */
    public int getNumeroDragao() {
        return numeroDragao;
    }

    /**
     * @param numeroDragao define o numero de dragoes de acordo com o parametro
     */
    public void setNumeroDragao(int numeroDragao) {
        this.numeroDragao = numeroDragao;
    }

    /**
     * @return o tipo de movimento do dragao
     */
    public int getTipoDragao() {
        return tipo_dragao;
    }

    /**
     * @param Modo define o movimento do dragao de acordo com o parametro
     */
    public void setTipoDragao(int Modo) {
        this.tipo_dragao = Modo;
    }

   
    /**
     * 
     * @param tipoLab define o tipo de labirinto de acordo com o parametro
     */
	public void setTipoLab(int tipoLab) {
		this.tipoLab = tipoLab;
	}
	/**
	 * 
	 * @return o tipo de labirinto
	 */
	public int getTipoLab() {
		return tipoLab;
	}
}
