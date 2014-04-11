package maze.gui;

import java.awt.BorderLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.IOException;

import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
//import javax.swing.SwingUtilities;

import maze.logic.Board;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
/**
* Classe que extende uma JFrame onde irá aparecer o jogo em modo grafico
* @author Tiago Mota ei09068
*/
public class JogoGraphic extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private Board board;
	private Maze tabuleiro=null;
	private MazeView parent;
	private JPanel Button_Panel = null;
	private JButton NewGameButton = null;
	private JButton Keys_Button = null;
	private KeysDialog keys_dialog =null;
	private JogoGraphic me = null;
	private JButton SaveButton = null;
	private JButton LoadButton = null;
	
	
	/**
	 * This is the default constructor
	 * @param mazeView 
	 * @throws IOException 
	 */
	public JogoGraphic(Board board, MazeView mazeView) throws IOException {
		super();
		setFocusable(false);
		me = this;
		parent =mazeView;
		this.setBoard(board);
		initialize();
		
	}
	
	/**
	 * Função que coloca o menu principal visivel ou nao.
	 * @param b true - menu fica visivel, false - menu invisivel
	 */
	public void setParentVisible(boolean b)
	{
		parent.setVisible(b);
	}

	/**
	 * This method initializes this
	 * 
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		this.setSize(500,550);
		this.setContentPane(getJContentPane());
		//this.setSize(getJContentPane().getHeight(),getJContentPane().getWidth());
		this.setTitle("Labirinto");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 * @throws IOException 
	 */
	private JPanel getJContentPane() throws IOException {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
			jContentPane.add(getTabuleiro(board), BorderLayout.CENTER);
			jContentPane.add(getButton_Panel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
	/**
	 * Função que retorna um tabuleiro (objecto do tipo Maze) e que se não tiver nenhum cria um novo apartir de um determinado Board board.
	 * @param board
	 * @throws IOException
	 * @return objecto do tipo Maze
	 */
	public Maze getTabuleiro(Board board) throws IOException {
		if (tabuleiro == null) {
				tabuleiro = new Maze(getBoard().getBoard(), this);
		}
		return tabuleiro;
	}
	/**
	 * 
	 * @return tabuleiro que tiver guardado
	 */
	public Maze getTabuleiro(){
		return tabuleiro;
	}
	/**
	 * Define a variavel board com o parametro board passado na função
	 * @param board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * 
	 * @return o objecto do tipo Board que se encontra na variavel board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * This method initializes Button_Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButton_Panel() {
		if (Button_Panel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			Button_Panel = new JPanel();
			Button_Panel.setLayout(new GridBagLayout());
			Button_Panel.add(getNewGameButton(), new GridBagConstraints());
			Button_Panel.add(getKeys_Button(), gridBagConstraints);
			Button_Panel.add(getSaveButton(), gridBagConstraints2);
			Button_Panel.add(getLoadButton(), gridBagConstraints3);
		}
		return Button_Panel;
	}

	/**
	 * This method initializes NewGameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNewGameButton() {
		if (NewGameButton == null) {
			NewGameButton = new JButton();
			NewGameButton.setText("New Game");
			NewGameButton.setFocusable(false);
			NewGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, "Deseja começar um jogo novo?", "Novo Jogo",JOptionPane.YES_NO_OPTION)== 0)
					{
						setParentVisible(true);
						setVisible(false);
					}
				}
			});
		}
		return NewGameButton;
	}

	/**
	 * This method initializes Keys_Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getKeys_Button() {
		if (Keys_Button == null) {
			Keys_Button = new JButton();
			Keys_Button.setText("Change Keys");
			Keys_Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(keys_dialog ==null)
						keys_dialog = new KeysDialog(me);
					else
						keys_dialog.setVisible(true);
				}
			});
		}
		return Keys_Button;
	}

	

	/**
	 * This method initializes SaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (SaveButton == null) {
			SaveButton = new JButton();
			SaveButton.setText("Save");
			SaveButton.setFocusable(false);
			SaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					try {
						guardarJogo();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}

				private void guardarJogo() throws FileNotFoundException, IOException {
					ObjectOutputStream ss = null;
				       try {
				            ss = new ObjectOutputStream(new FileOutputStream(new File("SaveGame.game")));
				            ss.writeObject(getBoard());
				       }
				       finally{
				    	   
				    	   if(ss != null)
				    		   ss.close();
				       }
					
				}
			});
		}
		return SaveButton;
	}

	/**
	 * This method initializes LoadButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLoadButton() {
		if (LoadButton == null) {
			LoadButton = new JButton();
			LoadButton.setText("Load");
			LoadButton.setFocusable(false);
			LoadButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						carregaJogo();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tabuleiro.setBackgroundNull();
					tabuleiro.buildBackground();
					tabuleiro.repaint();
				}

				private void carregaJogo() throws IOException, ClassNotFoundException {
					ObjectInputStream is = null;
				       try {
				            is = new ObjectInputStream(new FileInputStream(new File("SaveGame.game")));
				            
				            setBoard((Board) is.readObject());
				           
				       }
				       finally{
				    	   if(is != null)
				    		   is.close();
				       }
					
				}
				
				
			});
		}
		return LoadButton;
	}

	
}
