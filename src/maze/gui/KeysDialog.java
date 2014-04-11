package maze.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
/**
 * Classe que trata das teclas que estao a ser utilizadas por um JDialog
 * @author Tiago Mota ei09068
 */
public class KeysDialog {

	private JDialog Keys_Dialog = null;  //  @jve:decl-index=0:visual-constraint="250,49"
	private JPanel jContentPane = null;
	private JPanel Keys_Panel = null;
	private JButton UPButton = null;
	private JButton DOWNButton = null;
	private JButton RIGHTButton = null;
	private JButton LEFTButton = null;
	private JButton KEEPButton = null;
	private JogoGraphic parent;
	private int up, down, left, right,keep;
	private JButton OKButton = null;
	private JButton RESETButton = null;
	private WaitKeyDialog wait_key = null;
	private KeysDialog me;

	/**
	 * Construtor da classe KeysDialog
	 * @param parent
	 */
	public KeysDialog (JogoGraphic parent)
	{
		up =KeyEvent.VK_UP;
		down =KeyEvent.VK_DOWN;
		left = KeyEvent.VK_LEFT;
		right =KeyEvent.VK_RIGHT;
		keep = KeyEvent.VK_ENTER;
		this.parent = parent;
		parent.getTabuleiro().setFocusable(false);
		//parent.setVisible(false);
		Keys_Dialog = getKeys_Dialog();
		me= this;
	}


	/**
	 * This method initializes Keys_Dialog	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getKeys_Dialog() {
		if (Keys_Dialog == null) {
			Keys_Dialog = new JDialog();
			Keys_Dialog.setAlwaysOnTop(true);
			Keys_Dialog.setSize(new Dimension(249, 197));
			Keys_Dialog.setContentPane(getJContentPane());
			Keys_Dialog.setVisible(true);
		}
		return Keys_Dialog;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getKeys_Panel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes Keys_Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getKeys_Panel() {
		if (Keys_Panel == null) {
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 3;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			Keys_Panel = new JPanel();
			Keys_Panel.setLayout(new GridBagLayout());
			Keys_Panel.add(getUPButton(), gridBagConstraints);
			Keys_Panel.add(getDOWNButton(), gridBagConstraints1);
			Keys_Panel.add(getRIGHTButton(), gridBagConstraints2);
			Keys_Panel.add(getLEFTButton(), gridBagConstraints3);
			Keys_Panel.add(getKEEPButton(), gridBagConstraints4);
			Keys_Panel.add(getOKButton(), gridBagConstraints11);
			Keys_Panel.add(getRESETButton(), gridBagConstraints21);
		}
		return Keys_Panel;
	}

	/**
	 * This method initializes UPButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUPButton() {
		if (UPButton == null) {
			UPButton = new JButton();
			UPButton.setText("UP");
			UPButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(wait_key == null)
						wait_key=new WaitKeyDialog(me);
					else
						wait_key.setVisible(true);
					wait_key.setDireccao('u');
				}
			});
		}
		return UPButton;
	}

	/**
	 * This method initializes DOWNButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDOWNButton() {
		if (DOWNButton == null) {
			DOWNButton = new JButton();
			DOWNButton.setText("DOWN");
			DOWNButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(wait_key == null)
						wait_key=new WaitKeyDialog(me);
					else
						wait_key.setVisible(true);
					wait_key.setDireccao('d');
				}
			});
		}
		return DOWNButton;
	}

	/**
	 * This method initializes RIGHTButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRIGHTButton() {
		if (RIGHTButton == null) {
			RIGHTButton = new JButton();
			RIGHTButton.setText("RIGHT");
			RIGHTButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(wait_key == null)
						wait_key=new WaitKeyDialog(me);
					else
						wait_key.setVisible(true);
					wait_key.setDireccao('r');
				}
			});
		}
		return RIGHTButton;
	}

	/**
	 * This method initializes LEFTButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLEFTButton() {
		if (LEFTButton == null) {
			LEFTButton = new JButton();
			LEFTButton.setText("LEFT");
			LEFTButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(wait_key == null)
						wait_key=new WaitKeyDialog(me);
					else
						wait_key.setVisible(true);
					wait_key.setDireccao('l');
				}
			});
		}
		return LEFTButton;
	}

	/**
	 * This method initializes KEEPButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getKEEPButton() {
		if (KEEPButton == null) {
			KEEPButton = new JButton();
			KEEPButton.setText("KEEP");
			KEEPButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(wait_key == null)
						wait_key=new WaitKeyDialog(me);
					else
						wait_key.setVisible(true);
					wait_key.setDireccao('k');
				}
			});
		}
		return KEEPButton;
	}


	/**
	 * This method initializes OKButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOKButton() {
		if (OKButton == null) {
			OKButton = new JButton();
			OKButton.setText("OK");
			OKButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					parent.getTabuleiro().setKeys(up,down,left,right,keep);

					Keys_Dialog.setVisible(false);
					parent.getTabuleiro().setFocusable(true);
					parent.getTabuleiro().requestFocus();
					//					parent.setFocusable(true);
					//					parent.requestFocus();
				}
			});
		}
		return OKButton;
	}


	/**
	 * This method initializes RESETButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRESETButton() {
		if (RESETButton == null) {			RESETButton = new JButton();
		RESETButton.setText("RESET");
		RESETButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				resetKeys();
			}
			/**
			 * Função que repoe as teclas default
			 */
			private void resetKeys() {
				up =KeyEvent.VK_UP;
				down =KeyEvent.VK_DOWN;
				left = KeyEvent.VK_LEFT;
				right =KeyEvent.VK_RIGHT;
				keep = KeyEvent.VK_ENTER;
			}
		});
		}
		return RESETButton;
	}

	/**
	 * Funçao responsavel por por visivel a Keys Dialog de acordo com o valor passado no parametro
	 * @param b
	 */
	public void setVisible(boolean b) {
		Keys_Dialog.setVisible(b);
	}

	/**
	 * Função que de acordo com a direccao dada, define a tecla escolhida para a direccao escolhida
	 * @param keyCode
	 * @param direccao
	 */
	public void setKey(int keyCode, char direccao) {
		switch(direccao){
		case 'u':
			up = keyCode;
			break;
		case 'd':
			down = keyCode;
			break;
		case 'l':
			left = keyCode;
			break;
		case 'r':
			right = keyCode;
			break;
		case 'k':
			keep = keyCode;
			break;
		}

	}

}
