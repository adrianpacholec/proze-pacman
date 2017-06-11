import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa reprezentuj�ca punkt, dziedziczy po klasie Rectangle
 *
 * @author Pawe� Kowalik
 * @author Adrian Pacholec
 * @version 1.0
 */
public class Punkt extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor obiektu punktu
	 * 
	 * @param x
	 *            Pozycja x licz�c od lewego g�renego rogu panelu
	 * @param y
	 *            Pozycja y licz�c od lewego g�renego rogu panelu
	 * 
	 */
	public Punkt(int x, int y) {
		setBounds(x + 10, y + 10, 8, 8);
	}

	/**
	 * Metoda rysuj�ca reprezentacj� punktu
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