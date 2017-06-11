import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Klasa reprezentuj¹ca gracza, dziedziczy po klasie Rectangle
 *
 * @author Pawe³ Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Player extends Rectangle {
	/**
	 * punkty gromadzone przez gracza po zebraniu punktów, ich liczba na
	 * pocz¹tku wynosi 0
	 */

	/**
	 * zmienna przechowuj¹ca liczbê ¿yæ gracza
	 */
    public static boolean win;

	/**
	 * obiekt gry
	 */
    public JFrame frame;
	

	public int newx;
	public int newy; 
	private int start_star;
	/**
	 * 
	 */
	public Game game;
	
	/**
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */

	public int speedlevel;

	public String nick;


	
	private static final long serialVersionUID = 1L;

	/**
	 * zmienne s³u¿¹ce do obs³ugi ruchu pacmana
	 */
	public boolean right, left, down, up;

	
	
	/**
	 * Konstruktor obiektu gracza
	 * 
	 * @param x
	 *            Pozycja x licz¹c od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licz¹c od lewego górenego rogu panelu
	 * 
	 */
	public Player(int x, int y, int speedlevel, Game game, JFrame frame) {
		this.game = game;

		this.frame = frame;
		this.speedlevel = speedlevel;
		setBounds(x, y, 32, 32);
	}

	/**
	 * Metoda rysuj¹ca reprezentacjê gracza
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		/*if (!Game.gwiazdka)
			g.setColor(Color.yellow);
		else
			g.setColor(Color.green);
			*/
		g.fillRect(x,y,32,32);

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
				start_star = (int) System.currentTimeMillis();
				Game.gwiazdka = true;
				break;

			}

		}

		for (int i = 0; i < Game.mapa.punkty.size(); i++) {
			if (this.intersects(Game.mapa.punkty.get(i))) {
				Game.mapa.punkty.remove(i);
				Game.points += 1;
				break;
			}
		}
			if (Game.mapa.punkty.size() == 0 && Game.mapa.stars.size() == 0) {
				win = true;
				game.stop();
			}
		
		for (int i = 0; i < Game.mapa.enemies.size(); i++) {
			if (this.intersects(Game.mapa.enemies.get(i))) {
				if (!Game.gwiazdka) {
					//lifes = new ArrayList<Int>;
					Game.life--;
					Game.player.left = false;
					Game.player.right = false;
					Game.player.up = false;
					Game.player.down = false;
					Game.player.moveTo(Game.player.newx, Game.player.newy);
					
					if (Game.life == 0) 
					{
						win = false;
						game.stop();
						
					}
				} else
					Game.mapa.enemies.remove(i);

			}

		}

		// odliczaj

		if ((int) System.currentTimeMillis() - start_star >= 5000)
			Game.gwiazdka = false;

	}

	/**
	 * metoda sprawdzaj¹ca czy pacman nie wchodzi w œcianê, odpowiadaj¹ca za
	 * ruch pacmana
	 * 
	 * @param nextx
	 * @param nexty
	 * @return
	 */

	public boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, 30, 30);
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
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;

	}

}