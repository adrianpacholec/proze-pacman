import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Klasa reprezentuj�ca przeciwnika, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Enemy extends Rectangle {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private int random = 0, smart = 1, find_path = 2;
	private int anim2 = 0;
	/**
	 * zmienna pomocnicza
	 */
	private int state = smart;

	/**
	 * zmienne wyliczeniowe okre�laj�ce ruch przeciwnika
	 */
	private int right = 0, left = 1, up = 2, down = 3;
	private int animation = 0;
	/**
	 * zmienna okreslajaca kierunek ruchu przeciwnika
	 */
	private int dir = -1;

	public Random random2;

	/**
	 * zmienna odliczajaca czas
	 */
	private int time = 0;
	/**
	 * zmienna odpowiadajaca za przelaczanie stanu przeciwnikow z random na
	 * smart
	 */
	private int TargetTime = 30 * 4;

	private int lastDir = -1;

	/**
	 * zmienna potrzebna do przekazania predkosci wrogow
	 */
	public int speedlevel;

	/**
	 * Konstruktor obiektu przeciwnika
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * 
	 */
	public Enemy(int x, int y, int speedlevel) {
		this.speedlevel = speedlevel;
		random2 = new Random();
		setBounds(x, y, 32, 32);
		dir = random2.nextInt(4);
	}

	/**
	 * metoda odpowiadaj�ca za ruch przeciwnik�w i ich sztuczn� inteligencj� by
	 * porusza�y si� za pacmanem
	 */
	public void tick() {
		
		boolean move = false;
		if (Game.gwiazdka)
			state = random;

		if (state == random) {

			if (dir == right) {

				animation = 2;
				if (canMove(x + speedlevel, y)) {
					x += speedlevel;
				} else {
					dir = random2.nextInt(4);
				}
			}

			else if (dir == left) {
				animation = 1;
				if (canMove(x - speedlevel, y)) {
					x -= speedlevel;
				} else {
					dir = random2.nextInt(4);
				}
			}

			else if (dir == up) {
				animation = 3;
				if (canMove(x, y - speedlevel)) {
					y -= speedlevel;
				} else {
					dir = random2.nextInt(4);
				}
			}

			else if (dir == down) {
				animation = 4;
				if (canMove(x, y + speedlevel)) {
					y += speedlevel;
				} else {
					dir = random2.nextInt(4);
				}
			}

			time++;

			if (time >= TargetTime) {
				state = smart;
				time = 0;
			}

			/**
			 * poruszanie si� przeciwnika za pacmanem
			 */
		} else if (state == smart) {

			

			if (x < Game.player.x) {
				if (canMove(x + speedlevel, y)) {
					x += speedlevel;
					move = true;
					lastDir = right;
					animation = 2;
				}
			}
			if (x > Game.player.x) {
				if (canMove(x - speedlevel, y)) {
					x -= speedlevel;
					move = true;
					lastDir = left;
					animation = 1;
				}
			}
			if (y < Game.player.y) {
				if (canMove(x, y + speedlevel)) {
					y += speedlevel;
					move = true;
					lastDir = down;
					animation = 4;
				}
			}
			if (y > Game.player.y) {
				if (canMove(x, y - speedlevel)) {
					y -= speedlevel;
					move = true;
					lastDir = up;
					animation = 3;
				}
			}

			else if (x == Game.player.x && y == Game.player.y)
				move = true;

			if (!move) {
				state = find_path;
			}

			
			time++;

			if (time >= 2*TargetTime) {
				state = random;
				time = 0;
			}

			
		} else if (state == find_path) {
			
			if (lastDir == right) {

				if (y < Game.player.y) {
					if (canMove(x, y + speedlevel)) {
						y += speedlevel;
						move = true;
						animation = 3;
						state = smart;
					}
				} else {
					if (canMove(x, y - speedlevel)) {
						y -= speedlevel;
						move = true;
						animation = 4;
						state = smart;
					}
				}
				
				if (canMove(x + speedlevel, y)) {
					x += speedlevel;
					move = true;
					animation = 2;
				}
				
				
				
			} else if (lastDir == left) {
				if (y < Game.player.y) {
					if (canMove(x, y + speedlevel)) {
						y += speedlevel;
						move = true;
						state = smart;
						animation = 3;
					}
				} else {
					if (canMove(x, y - speedlevel)) {
						y -= speedlevel;
						move = true;
						state = smart;
						animation = 4;
					}
				}
				if (canMove(x - speedlevel, y)) {
					x -= speedlevel;
					move = true;
					animation = 1;
				}
				
			} else if (lastDir == up) {
				
				
				if (x < Game.player.x) {
					if (canMove(x + speedlevel, y)) {
						x += speedlevel;
						move = true;
						state = smart;
						animation = 2;
					}
				} else {
					if (canMove(x - speedlevel, y)) {
						x -= speedlevel;
						move = true;
						state = smart;
						animation = 1;
					}
				}
				if (canMove(x, y-speedlevel)) {
					y -= speedlevel;
					move = true;
					animation = 3;
				}
			}

			else if (lastDir == down) {
				if (x < Game.player.x) {
					if (canMove(x + speedlevel, y)) {
						x += speedlevel;
						move = true;
						state = smart;
						animation = 2;
					}
				} else {
					if (canMove(x - speedlevel, y)) {
						x -= speedlevel;
						move = true;
						state = smart;
						animation = 1;
					}
				}
				if (canMove(x, y+speedlevel)) {
					y += speedlevel;
					move = true;
					animation = 4;
				}
			}
			time++;
			
			//if (!move) state = random;

			if (time >= 2*TargetTime) {
				state = random;
				time = 0;
			}
		}
	}

	public boolean canMove(int nextx, int nexty) {
		Rectangle bounds = new Rectangle(nextx, nexty, 32, 32);
		Mapa mapa = Game.mapa;

		for (int xx = 0; xx < mapa.tiles.length; xx++)
			for (int yy = 0; yy < mapa.tiles[0].length; yy++) {
				if (mapa.tiles[xx][yy] != null) {
					if (bounds.intersects(mapa.tiles[xx][yy])) {
						return false;
					}
				}
			}

		return true;
	}

	/**
	 * Metoda rysuj�ca reprezentacj� przeciwnika
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		Spritesheet sheet = Control.spritesheet;
		if (animation == 1)
			g.drawImage(sheet.getSprite(0 + anim2, 8), x, y, null);
		else if (animation == 2)
			g.drawImage(sheet.getSprite(0 + anim2, 6), x, y, null);
		else if (animation == 3)
			g.drawImage(sheet.getSprite(0 + anim2, 7), x, y, null);
		else if (animation == 4)
			g.drawImage(sheet.getSprite(0 + anim2, 5), x, y, null);
		if (anim2 == 3)
			anim2 = 0;
		else
			anim2++;
	}

}
