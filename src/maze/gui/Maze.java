package maze.gui;



import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe responsável pelo desenho do labirinto e motor de jogo num JPanel
 * @author Tiago Mota ei09068
 */
public class Maze extends JPanel implements KeyListener{
	/**
	 * inicialização de variaveis da classe Maze
	 */
	private static final long serialVersionUID = 1L;
	private int oldheight=0;
	private int oldwidht=0;
	private JogoGraphic jGraphic;
	private Graphics buffer = null;
	private Graphics buffer2 = null;
	private Image offscreen = null;
	private Image background = null;
	private int cellsize;
	private BufferedImage heroi_img = null;
	private BufferedImage heroi_espada_img = null;
	private BufferedImage espada_img = null;
	private BufferedImage dragao_img = null;
	private BufferedImage dragao_dormir_img = null;
	private BufferedImage chao_img = null;
	private BufferedImage parede_img = null;
	private BufferedImage drag_espada_img = null;
	private int up, down, left, right,keep;




	/**
	 * 
	 * @return cellsize tamnho que a celula tem
	 */

	public int getCellSize() {
		return cellsize;
	}
	
	public void setBackgroundNull(){
		background = null;
	}
	/**
	 * 
	 * @param cellsize inteiro que será o tamnho da celula
	 */
	public void setCellSize(int cellsize) {
		this.cellsize = cellsize;
	}

	/**
	 * This is the default constructor
	 * @throws IOException 
	 */
	public Maze(char[][] board, JogoGraphic jGraphic) throws IOException{
		super();
		initialize();
		//this.board=board;
		this.jGraphic=jGraphic;
		heroi_img = ImageIO.read(new File("images\\hero.png"));
		heroi_espada_img = ImageIO.read(new File("images\\heroEspada.png"));
		espada_img = ImageIO.read(new File("images\\espada.png"));
		dragao_img = ImageIO.read(new File("images\\dragao.png"));
		dragao_dormir_img = ImageIO.read(new File("images\\dragao_dormir.png"));
		chao_img = ImageIO.read(new File("images\\chao.png"));
		parede_img = ImageIO.read(new File("images\\parede.png"));
		drag_espada_img = ImageIO.read(new File("images\\drag_espada.png"));
		up =KeyEvent.VK_UP;
		down =KeyEvent.VK_DOWN;
		left = KeyEvent.VK_LEFT;
		right =KeyEvent.VK_RIGHT;
		keep = KeyEvent.VK_ENTER;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(600, 400);
		this.oldheight=this.getHeight();
		this.oldwidht=this.getWidth();
		this.setLayout(new GridBagLayout());
		this.setFocusable(true);
		this.addKeyListener(this);
	}


	
	@Override
	/**
	 * função que faz override a função já existente paintComponent. Aqui é utilizado o double buffering.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(this.getHeight()< this.getWidth())
			setCellSize(this.getHeight()/jGraphic.getBoard().getSize());
		else
			setCellSize(this.getWidth()/jGraphic.getBoard().getSize());
		
		if(this.oldheight!=this.getHeight() || this.oldwidht!=this.getWidth()){
			this.oldheight=this.getHeight();
			this.oldwidht=this.getWidth();
			background=null;
			
		}
		
		if (background == null)
		{
			buildBackground();
		}
			
		offscreen = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB); 
		buffer = offscreen.getGraphics();
		buffer.setColor(g.getColor());

		

		for(int i=0; i < jGraphic.getBoard().getBoard().length; i++)
		{
			for(int j=0; j < jGraphic.getBoard().getBoard().length; j++)
			{
				switch(jGraphic.getBoard().getBoard()[j][i])
				{
				
				case 'H':
					buffer.drawImage(heroi_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					
					break;
				case 'D':
					buffer.drawImage(dragao_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				case 'S':
					buffer.drawImage(chao_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				case 'd':
					buffer.drawImage(dragao_dormir_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				case 'A':
					buffer.drawImage(heroi_espada_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				case 'F':
					buffer.drawImage(drag_espada_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				case 'E': 
					buffer.drawImage(espada_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
				}
			}
		}
		g.drawImage(background,0,0,this);
		g.drawImage(offscreen,0,0,this);
	}
	/**
	 * Função relativa ao double buffering, responsável pelo desenho da imagem de background
	 */
	public void buildBackground() {
		background = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		buffer2 = background.getGraphics();
		if(this.getHeight()< this.getWidth())
			setCellSize(this.getHeight()/jGraphic.getBoard().getSize());
		else
			setCellSize(this.getWidth()/jGraphic.getBoard().getSize());
		for(int i=0; i < jGraphic.getBoard().getSize(); i++)
		{
			for(int j=0; j < jGraphic.getBoard().getSize(); j++)
			{
				switch(jGraphic.getBoard().getBoard()[j][i])
				{
				case 'X':		
					buffer2.drawImage(parede_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				default:
					buffer2.drawImage(chao_img, i * getCellSize() ,j * getCellSize(), getCellSize(), getCellSize(), this);
					break;
				}
			}
		}
		
	}

	public void keyTyped(KeyEvent e) {

	}
	/**
	 * função que irá mover tanto o dragao(dependendo de factores externos) e o heroi(de acordo com a direcção que a tecla seleccionada tem definida)
	 */
	public void keyPressed(KeyEvent e) {
		int dir=99;
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			Object[] options = {"Sim","Nao"};
			int n=JOptionPane.showOptionDialog(this, "Deseja mesmo sair?","Sair do Jogo",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,null,options, options[1]);
			if(n==0)
			{
				jGraphic.setVisible(false);
			}
		}
		if(e.getKeyCode()==up)
			dir=1;
		else
			if(e.getKeyCode()==down)
				dir=2;
			else
				if(e.getKeyCode()==left)
					dir=3;
				else
					if(e.getKeyCode()==right)
						dir=4;
					else
						if(e.getKeyCode()==keep)
							dir=5;

		this.repaint();

		switch(move_heroi(dir))
		{
		case 1:
			JOptionPane.showMessageDialog(this, "Ganhou o Jogo!");
			jGraphic.setParentVisible(true);
			jGraphic.setVisible(false);
			break;
		case 2:
			JOptionPane.showMessageDialog(this, "Tens de ter o Heroi armado!");
			break;
		}
		if(avanca_dragao(jGraphic.getBoard().getTipo_dragao()))
		{
			JOptionPane.showMessageDialog(this, "Perdeu o Jogo!");
			jGraphic.setParentVisible(true);
			jGraphic.setVisible(false);
		}

	}
	/**
	 * 
	 * @param mov - direccao que o heroi vai tomar
	 * @return int que irá servir para verificações de ganho/erro
	 */
	private int move_heroi(int mov) {
		return jGraphic.getBoard().moveHero(mov);

	}
	
	/**
	 * 
	 * @param opt opcao escolhida pelo utilizador para escolher qual o tipo de movimento do dragao
	 * @return boolean que irá servir para verificações de perda/erro
	 */
	private boolean avanca_dragao(int opt) {
		if(opt == 3)
			return jGraphic.getBoard().moveDragonSleeper();
		else if(opt == 2)
			return jGraphic.getBoard().moveDragon(2);
		
		else
			return jGraphic.getBoard().moveDragon(1);
			
	}
	
	public void keyReleased(KeyEvent e) {
	}
	/**
	 * Define novas teclas
	 * @param up tecla direccao norte
	 * @param down tecla direccao sul
	 * @param left tecla direccao oeste
	 * @param right tecla direccao este
	 * @param keep tecla manter posicao
	 */
	public void setKeys(int up, int down, int left, int right, int keep) {
		this.up = up;
		this.down=down;
		this.left=left;
		this.right=right;
		this.keep=keep;
	}



}
