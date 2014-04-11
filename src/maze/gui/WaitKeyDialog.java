package maze.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
* Classe que extende um JDialog e KeyListener a fim de esperar pela tecla primida a fim de definir novas teclas de jogo
* @author Tiago Mota ei09068
*/
public class WaitKeyDialog extends JDialog implements KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel Label = null;
	private JPanel Panel = null;
	private char direccao;
	private KeysDialog parent;
	
	public char getDireccao() {
		return direccao;
	}

	public void setDireccao(char direccao) {
		this.direccao = direccao;
		requestFocus();
	}
	/**
	 * Construtor da classe WaitKeyDialog
	 * @param parent
	 */
	public WaitKeyDialog(KeysDialog parent){
		super();
		this.parent=parent;
		direccao = 'k';
		setFocusable(true);
		setAlwaysOnTop(true);
		setSize(new Dimension(196, 107));
		setContentPane(getJContentPane());
		setVisible(true);
		addKeyListener(this);
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Label = new JLabel();
			Label.setText("Waiting Key..");
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanel() {
		if (Panel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			Panel = new JPanel();
			Panel.setLayout(new GridBagLayout());
			Panel.add(Label, gridBagConstraints);
		}
		return Panel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		parent.setKey(e.getKeyCode(),direccao);
		setVisible(false);
	}

	@Override
	/**
	 * Função não utilizada neste codigo
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Função nao utilizada neste codigo
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
