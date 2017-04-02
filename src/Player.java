import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentująca gracza, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Player extends Rectangle {
	/**
	 * Konstruktor obiektu gracza
	 * 
	 * @param x
	 *            Pozycja x licząc od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licząc od lewego górenego rogu panelu
	 * 
	 */
	public Player(int x, int y) {
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
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

}
