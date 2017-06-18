import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj�ca gracza, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Player extends Rectangle {
	/**
	 * punkty gromadzone przez gracza po zebraniu punkt�w, ich liczba na
	 * pocz�tku wynosi 0
	 */

	public int newx;
	public int newy;
	public int anim = 0;
	/**
	 * 
	 */
	public Control game;

	/**
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */

	public int speedlevel;

	public String nick;

	private static final long serialVersionUID = 1L;

	/**
	 * zmienne s�u��ce do obs�ugi ruchu pacmana
	 */
	public boolean right, left, down, up;

	/**
	 * Konstruktor obiektu gracza
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * 
	 */
	public Player(int x, int y, int speedlevel) {

		this.speedlevel = speedlevel;
		setBounds(x, y, 32, 32);
	}

	/**
	 * Metoda rysuj�ca reprezentacj� gracza
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		Spritesheet sheet = Control.spritesheet;

		if (!Game.gwiazdka) {
			if (right)
				g.drawImage(sheet.getSprite(7 + anim, 7), x, y, null);
			else if (up)
				g.drawImage(sheet.getSprite(7 + anim, 8), x, y, null);
			else if (left)
				g.drawImage(sheet.getSprite(7 + anim, 6), x, y, null);
			else if (down)
				g.drawImage(sheet.getSprite(7 + anim, 5), x, y, null);
			else
				g.drawImage(sheet.getSprite(8, 5), x, y, null);

		} else {
			if (left)
				g.drawImage(sheet.getSprite(4 + anim, 8), x, y, null);
			else if (right)
				g.drawImage(sheet.getSprite(4 + anim, 6), x, y, null);
			else if (up)
				g.drawImage(sheet.getSprite(4 + anim, 7), x, y, null);
			else if (down)
				g.drawImage(sheet.getSprite(4 + anim, 5), x, y, null);
			else
				g.drawImage(sheet.getSprite(4, 5), x, y, null);

		}

	}

	/**
	 * metoda na gloda
	 */
	public void move() {
		if (right && canMove(x + Config.PacmanSpeed, y)) {
			x += Config.PacmanSpeed;
			if (anim == 2)
				anim = 0;
			else
				anim++;
		}
		if (left && canMove(x - Config.PacmanSpeed, y)) {
			x -= Config.PacmanSpeed;
			if (anim == 2)
				anim = 0;
			else
				anim++;
		}
		if (up && canMove(x, y - Config.PacmanSpeed)) {
			y -= Config.PacmanSpeed;
			if (anim == 2)
				anim = 0;
			else
				anim++;
		}

		if (down && canMove(x, y + Config.PacmanSpeed)) {
			y += Config.PacmanSpeed;
			if (anim == 2)
				anim = 0;
			else
				anim++;
		}

	}

	/**
	 * metoda sprawdzaj�ca czy pacman nie wchodzi w �cian�, odpowiadaj�ca za
	 * ruch pacmana
	 * 
	 * @param nextx
	 * @param nexty
	 * @return
	 */

	public boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, 30, 30);
		Mapa mapa = Control.mapa;
		for (int xxx = 0; xxx < Control.mapa.tiles.length; xxx++)
			for (int yyy = 0; yyy < Control.mapa.tiles[0].length; yyy++) {
				if (mapa.tiles[xxx][yyy] != null) {
					if (bounds.intersects(Control.mapa.tiles[xxx][yyy])) {
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