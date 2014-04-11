package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.*;

public class Testes {


	@Test
	public void testMovHero() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardDefault();
		Board maze = fabricaTabuleiro.create(0, 0, 0);
		maze.setSize(6);
		maze.moveHero(4);
		assertEquals(2, maze.getPosH().getX());
		assertEquals(1, maze.getPosH().getY());
		maze.moveHero(4);
		maze.moveHero(4);
		maze.moveHero(2);
		assertEquals(4, maze.getPosH().getX());
		assertEquals(2, maze.getPosH().getY());
		maze.moveHero(1);
		assertEquals(4, maze.getPosH().getX());
		assertEquals(1, maze.getPosH().getY());
		maze.moveHero(3);
		assertEquals(3, maze.getPosH().getX());
		assertEquals(1, maze.getPosH().getY());
		maze.moveHero(5);
		assertEquals(3, maze.getPosH().getX());
		assertEquals(1, maze.getPosH().getY());
	}

	@Test
	public void testSword() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardDefault();
		Board maze = fabricaTabuleiro.create(0, 0, 0);
		assertEquals(false, maze.getPosH().isArmed());
		maze.moveHero(4);
		maze.moveHero(4);
		maze.moveHero(4);
		maze.moveHero(2);
		maze.moveHero(2);
		maze.moveHero(2);
		maze.moveHero(2);
		maze.moveHero(3);
		maze.moveHero(3);
		maze.moveHero(3);
		maze.moveHero(2);
		maze.moveHero(2);
		maze.moveHero(2);
		assertEquals(true, maze.getPosH().isArmed());
		assertEquals('A', maze.getBoard()[8][1]);
		maze.moveHero(1);
		assertEquals(' ', maze.getBoard()[8][1]);
		assertEquals('A', maze.getBoard()[7][1]);
	}

	@Test
	public void testMovDrag() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardDefault();
		Board maze = fabricaTabuleiro.create(0, 0, 0);

		int yD = maze.getDragoes().get(0).getY();
		maze.moveDragon(2);
		int xA = maze.getDragoes().get(0).getX();
		int yA = maze.getDragoes().get(0).getY();
		if(yD-1 == yA){
			assertEquals(1, xA);
			assertEquals(2, yA);
		}
		if(yD+1 == yA){
			assertEquals(1, xA);
			assertEquals(4, yA);
		}

	}

	@Test
	public void testKillPlayer() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardDefault();
		Board maze = fabricaTabuleiro.create(0, 0, 0);
		maze.moveHero(4);
		assertEquals(false, maze.moveDragon(1));
		maze.moveHero(3);
		maze.moveHero(2);
		assertEquals(true, maze.moveDragon(1));
	}

	@Test
	public void testKillDrag() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardDefault();
		Board maze = fabricaTabuleiro.create(0, 0, 0);
		maze.getPosH().setArmed(true);
		maze.moveHero(4);
		assertEquals(false, maze.getDragoes().get(0).isDead());
		maze.moveHero(3);
		maze.moveHero(2);
		assertEquals(true, maze.getDragoes().get(0).isDead());

		assertEquals(' ', maze.getBoard()[3][1]);
		assertEquals('A', maze.getBoard()[2][1]);
		assertEquals(maze.getDragoes().isEmpty(), false);
	}

	@Test
	public void WinnerOrError() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardDefault();
		Board maze = fabricaTabuleiro.create(0, 0, 0);
		maze.getPosH().setX(8);
		maze.getPosH().setY(5);
		assertEquals(2, maze.moveHero(4));
		maze.getPosH().setArmed(true);
		assertEquals(1, maze.moveHero(4));

	}


	@Test
	public void MoveMultipleDragons() {
		BoardFactory fabricaTabuleiro;
		fabricaTabuleiro = new BoardRandom();
		Board maze = fabricaTabuleiro.create(10, 2, 2);
		maze.setCellE(maze.getPosH());
		int x1 = maze.getDragoes().get(0).getX();
		int y1 = maze.getDragoes().get(0).getY();
		int mov = DragonsMov(maze,maze.getDragoes().get(0));
		if(mov == 5){
			assertEquals(x1,maze.getDragoes().get(0).getX());
			assertEquals(y1,maze.getDragoes().get(0).getY());
		}
		else{
			assertEquals(' ', maze.getBoard()[y1][x1]);
			assertEquals('D', maze.getBoard()[maze.getDragoes().get(0).getY()][maze.getDragoes().get(0).getX()]);
		}
		int x2 = maze.getDragoes().get(1).getX();
		int y2 = maze.getDragoes().get(1).getY();
		int mov2 = DragonsMov(maze,maze.getDragoes().get(1));
		if(mov2 == 5){
			assertEquals(x2,maze.getDragoes().get(1).getX());
			assertEquals(y2,maze.getDragoes().get(1).getY());
		}
		else{
			assertEquals(' ', maze.getBoard()[y2][x2]);
			assertEquals('D', maze.getBoard()[maze.getDragoes().get(1).getY()][maze.getDragoes().get(1).getX()]);
		}


	}

	


	public int DragonsMov(Board maze, Dragon drag) {
		int x1 = drag.getX();
		int y1 = drag.getY();
		maze.moveDragon(2);
		if(drag.getX() == x1-1)
			return 3;
		if(drag.getX() == x1+1)
			return 4;
		if(drag.getY() == y1-1)
			return 1;
		if(drag.getY() == y1+1)
			return 2;

		return 5;
	}

}