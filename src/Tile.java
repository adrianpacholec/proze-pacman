import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentująca ścianę, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Tile extends Rectangle {

	/**
	 * Konstruktor obiektu ściany
	 * 
	 * @param x
	 *            Pozycja x licząc od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licząc od lewego górenego rogu panelu
	 * 
	 */
	public Tile(int x, int y) {
		setBounds(x, y, 32, 32);
	}

	/**
	 * Metoda rysująca reprezentację ściany
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}
