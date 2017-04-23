import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Klasa reprezentująca gracza, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Player extends Rectangle {
	/**
	 * punkty gromadzone przez gracza po zebraniu punktów, ich liczba na
	 * początku wynosi 0
	 */
	public static int points = 0;
	public static int life = 3;
	/**
	 * zmienna przechowująca liczbę żyć gracza
	 */
	public static boolean win;

	/**
	 * obiekt gry
	 */
	public JFrame frame;

	public int newx;
	public int newy;
	/**
	 * 
	 */
	public static Game game;

	/**
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */

	public int speedlevel;

	public String nick;

	public String mapaPath;

	private static final long serialVersionUID = 1L;

	/**
	 * zmienne służące do obsługi ruchu pacmana
	 */
	public boolean right, left, down, up;

	/**
	 * Konstruktor obiektu gracza
	 * 
	 * @param x
	 *            Pozycja x licząc od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licząc od lewego górenego rogu panelu
	 * 
	 */
	public Player(int x, int y, int speedlevel, Game game, JFrame frame, String nick, String mapaPath) {
		this.game = game;
		this.mapaPath = mapaPath;
		this.frame = frame;
		this.nick = nick;
		this.speedlevel = speedlevel;
		setBounds(x, y, 32, 32);
	}

	/**
	 * Metoda rysująca reprezentację gracza
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		if (!Game.gwiazdka)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.green);
		g.fillRect(x, y, width, height);

	}

	/**
	 * metoda na gloda
	 */
	public void update(String nick) {
		if (right && canMove(x + Config.PacmanSpeed, y))
			x += Config.PacmanSpeed;
		if (left && canMove(x - Config.PacmanSpeed, y))
			x -= Config.PacmanSpeed;
		if (up && canMove(x, y - Config.PacmanSpeed))
			y -= Config.PacmanSpeed;
		if (down && canMove(x, y + Config.PacmanSpeed))
			y += Config.PacmanSpeed;

		Mapa mapa = Game.mapa;

		for (int i = 0; i < Game.mapa.stars.size(); i++) {
			if (this.intersects(Game.mapa.stars.get(i))) {
				Game.mapa.stars.remove(i);
				System.out.println("Gwiazdka!");
				Game.start = (int) System.currentTimeMillis();
				Game.gwiazdka = true;
				break;

			}

		}

		for (int i = 0; i < Game.mapa.punkty.size(); i++) {
			if (this.intersects(Game.mapa.punkty.get(i))) {
				Game.mapa.punkty.remove(i);
				points += 1;
				break;
			}
		}
		if (Game.mapa.punkty.size() == 0 && Game.mapa.stars.size() == 0) {

			/*
			 * game.setVisible(false); Game game2 = new Game(nick, speedlevel,
			 * frame, Config.FileMap2, points); frame.add(game2); frame.pack();
			 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 * frame.setLocationRelativeTo(null); frame.setVisible(true);
			 * game2.run();//
			 * 
			 */
			game.stop();
			win = true;

		}

		for (int i = 0; i < Game.mapa.enemies.size(); i++) {
			if (this.intersects(Game.mapa.enemies.get(i))) {
				if (!Game.gwiazdka) {
					// lifes = new ArrayList<Int>;
					life--;
					Game.player.left = false;
					Game.player.right = false;
					Game.player.up = false;
					Game.player.down = false;
					Game.player.moveTo(Game.player.newx, Game.player.newy);
					if (life == 0) {
						game.stop();
						win = false;
					}
				} else
					Game.mapa.enemies.remove(i);

			}

		}

		// odliczaj

		if ((int) System.currentTimeMillis() - Game.start >= 5000)
			Game.gwiazdka = false;

	}

	/**
	 * metoda sprawdzająca czy pacman nie wchodzi w ścianę, odpowiadająca za
	 * ruch pacmana
	 * 
	 * @param nextx
	 * @param nexty
	 * @return
	 */

	public boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, 29, 29);
		Mapa mapa = Game.mapa;
		for (int xxx = 0; xxx < Game.mapa.tiles.length; xxx++)
			for (int yyy = 0; yyy < Game.mapa.tiles[0].length; yyy++) {
				if (mapa.tiles[xxx][yyy] != null) {
					if (bounds.intersects(Game.mapa.tiles[xxx][yyy])) {
						return false;

					}
				}
			}

		return true;

	}

	public int getScore() {
		return points;
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;

	}

}
