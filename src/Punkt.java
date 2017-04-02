import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentująca punkt, dziedziczy po klasie Rectangle
 *
 * @author Paweł Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Punkt extends Rectangle {

	/**
	 * Konstruktor obiektu punktu
	 * 
	 * @param x
	 *            Pozycja x licząc od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licząc od lewego górenego rogu panelu
	 * 
	 */
	public Punkt(int x, int y) {
		setBounds(x + 10, y + 10, 8, 8);
	}

	/**
	 * Metoda rysująca reprezentację punktu
	 * 
	 * @param g
	 *            Kontekst graficzny
	 * 
	 */
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}
}
