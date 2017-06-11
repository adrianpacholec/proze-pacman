import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj¹ca punkt, dziedziczy po klasie Rectangle
 *
 * @author Pawe³ Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Punkt extends Rectangle {

	/**
	 * Konstruktor obiektu punktu
	 * 
	 * @param x
	 *            Pozycja x licz¹c od lewego górenego rogu panelu
	 * @param y
	 *            Pozycja y licz¹c od lewego górenego rogu panelu
	 * 
	 */
	public Punkt(int x, int y) {
		setBounds(x + 10, y + 10, 8, 8);
	}

	/**
	 * Metoda rysuj¹ca reprezentacjê punktu
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